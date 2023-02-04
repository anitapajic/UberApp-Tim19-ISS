package org.Tim19.UberApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RestTemplate restTemplate;


    public Ride findOneById(Integer id){return rideRepository.findById(id).orElse(null);}

    public Ride findOneRideById(Integer id){
        return rideRepository.findOneRideById(id);
    }

    public List<Ride> findAllFilter(RideHistoryFilterDTO filter){
        List<Ride> rides;
        if( filter.getStartDate() != null && filter.getEndDate() != null){
            rides = rideRepository.findAllInDateRange(filter.getStartDate(), filter.getEndDate());
        }
        else {
            rides = rideRepository.findAll();
        }

        if(filter.getDriverId() != null){
            rides.removeIf(ride -> !Objects.equals(ride.getDriver().getId(), filter.getDriverId()));
        }
        if(filter.getKeyword() != null){
            rides.removeIf(ride -> !ride.toString().contains(filter.getKeyword()));
        }

        return rides;
    }

    public Page<Ride> findAll(Pageable page){return rideRepository.findAll(page);}

    public Ride save(Ride ride){return rideRepository.save(ride);}
    public void saveStep(Integer rideId){
        Ride ride = this.findOneById(rideId);
        ride.setStep(ride.getStep()+1);
        save(ride);
    }

    public String findJSON(Ride ride){
        String url = "http://router.project-osrm.org/route/v1/driving/" + ride.getDeparture().getLongitude() + "," + ride.getDeparture().getLatitude() + ";" + ride.getDestination().getLongitude() + "," + ride.getDestination().getLatitude() + "?geometries=geojson&overview=false&alternatives=true&steps=true";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        else{
            return "";
        }
    }

    public RideDTO create(RideDTO rideDTO) {
        Ride ride = new Ride(rideDTO);
        ride.setStep(0);

        if(ride.getRouteJSON() == ""){
            ride.setRouteJSON(findJSON(ride));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();

        Passenger passenger = passengerService.findByEmail(user.getUsername());
        ride.addPassenger(passenger);

        HashMap<String, Object> driverTime = this.findAvailableDriver(ride);
        Driver driver = (Driver) driverTime.get("driver");
        ride.setDriver(driver);
        ride.setVehicleType(driver.getVehicle().getVehicleType());


        Integer min = (Integer) driverTime.get("time");
        ride.setStartTime(LocalDateTime.now().plusMinutes(min));

        ride.setStatus("PENDING");
        ride.setPanic(false);

        Double distance = calculateDistanceKm(ride.getDeparture(), ride.getDestination());

        //DecimalFormat df = new DecimalFormat("#.##");
        Double cost = this.calculatePrice(ride.getVehicleType(), distance);
        ride.setTotalCost((double)Math.round(cost));
        ride.setEstimatedTimeInMinutes(this.travelTimeInMinutes(distance));

        ride = rideRepository.save(ride);

        return new RideDTO(ride);
    }

    public Location findNextLocation(Location departure, Location destination) throws JsonProcessingException {
        String url = "http://router.project-osrm.org/route/v1/driving/" + departure.getLongitude() + "," + departure.getLatitude() + ";" + destination.getLongitude() + "," + destination.getLatitude() + "?geometries=geojson&overview=false&alternatives=true&steps=true";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode step = root.path("routes").get(0).path("legs").get(0).path("steps").get(1);
            if(step == null){
                return null;
            }
            Float nextStepLon = step.path("maneuver").path("location").get(0).floatValue();
            Float nextStepLat = step.path("maneuver").path("location").get(1).floatValue();
            Location nextLocation = new Location();
            nextLocation.setLatitude(nextStepLat);
            nextLocation.setLongitude(nextStepLon);
            return nextLocation;
        }
        System.out.println("neka greska");
        return null;
    }

    public void remove(Integer id){rideRepository.deleteById(id);}

    public Page<Ride>findByUserId(Integer id, Pageable pageable){
        Page<Ride> rides = rideRepository.findAllByDriverId(id, pageable);
        if (rides.isEmpty()){
            rides = rideRepository.findAllByPassengersId(id, pageable);
        }

        return rides;
    }

    public Page<Ride> findByPassengerId(Integer id, String from, String to, Pageable pageable){

        Page<Ride> rides;
        if(from != null && to != null)
            rides = rideRepository.findAllByPassengersIdFilter(id, LocalDateTime.parse(from), LocalDateTime.parse(to), pageable);
        else
            rides = rideRepository.findAllByPassengersId(id, pageable);;

        return rides;

    }
    public Set<Ride> findAllByPassengerId(Integer id){
        Set<Ride> rides = rideRepository.findAllByPassengersId(id);

        return rides;
    }
    public Page<Ride> findByDriverId(Integer id, String from, String to, Pageable pageable){
        Page<Ride> rides;
        if(from != null && to != null)
            rides = rideRepository.findAllByDriverIdFilter(id, LocalDateTime.parse(from), LocalDateTime.parse(to), pageable);
        else
            rides = rideRepository.findAllByDriverId(id, pageable);

        return rides;
    }

    public HashMap<String, Object> findAvailableDriver(Ride ride){

        Driver choosenDriver = new Driver();
        Integer minTime = Integer.MAX_VALUE;
        //TODO : findAllWithVehicleType
        List<Driver> drivers = driverService.findAll();
        for(Driver d : drivers){
            if(isWorking(d) && !isWorkingToMuch(d)){
                Integer whenIsAvailable = whenIsAvailable(d);
                Integer timeToArive = timeToArrive(d, ride.getDeparture());
                if((whenIsAvailable + timeToArive) < minTime){
                    minTime = whenIsAvailable + timeToArive;
                    choosenDriver = d;
                }
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("driver", choosenDriver);
        response.put("time", minTime);
        return response;
    }
    private boolean isWorking(Driver driver){
        return driver.getActive();
    }

    private boolean isWorkingToMuch(Driver driver){
        //TODO: proveri da li radi preko 8 h
        return false;
    }

    private Integer whenIsAvailable(Driver driver){
        if(driver.getHasRide()){
            LocalDateTime now = LocalDateTime.now();
            Ride currentRide = findActiveRideByDriverId(driver.getId());
            LocalDateTime endTime = currentRide.getStartTime().plusMinutes(currentRide.getEstimatedTimeInMinutes());
            return Math.toIntExact(ChronoUnit.MINUTES.between(now, endTime));
        }
        return 0;
    }

    private Integer timeToArrive(Driver driver, Location nextRideDeparture){
        Location startLocation = driver.getVehicle().getLocation();

        if(driver.getHasRide()){
            Ride currentRide = findActiveRideByDriverId(driver.getId());
            startLocation = currentRide.getDestination();
        }

        Location endLocation = nextRideDeparture;

        Double distance = calculateDistanceKm(startLocation, endLocation);
        return travelTimeInMinutes(distance);
    }

    public Double calculateDistanceKm(Location startLocation, Location endLocation){
        Float startLat = startLocation.getLatitude();
        Float startLng = startLocation.getLongitude();
        Float endLat = endLocation.getLatitude();
        Float endLng = endLocation.getLongitude();


        final int R = 6371;
        double latDistance = Math.toRadians(endLat - startLat);
        double lonDistance = Math.toRadians(endLng - startLng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(startLng)) * Math.cos(Math.toRadians(endLng))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public Integer travelTimeInMinutes(Double distance){
        Double averageSpeed = 40.0;
        return (int) (distance/averageSpeed*60) + 4;
    }

    public Driver findFreeDriver(){
        List<Driver> freeDrivers = new ArrayList<>();
        for (Driver driver: driverService.findAll()) {
            Set<Ride> rides = driver.getRides();
            List<String> statuses = new ArrayList<>();
            for (Ride ride : rides){
                statuses.add(ride.getStatus());
            }
            if (statuses.contains("STARTED")){
                continue;
            }
            else{
                freeDrivers.add(driver);
            }

        }
        Random random = new Random();
        int index = random.nextInt(freeDrivers.size());
        Driver freeDriver = freeDrivers.get(index);
        return freeDriver;
    }


    public Double calculateKilometres(Float long1, Float long2, Float lat1, Float lat2){
        Double distance = Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((long1 - long2), 2));
        return distance * 150;
    }

    public Integer calculateTravelTime(Double distance){
        Integer time = (int) ((distance)*4);
        return time;
    }

    public List<Float> getCoordinates(Set<Path> locations){
        List<Float> coordinates = new ArrayList<>();
        for (Path p: locations){
            Float latitude1 = p.getDeparture().getLatitude();
            Float longitude1 = p.getDeparture().getLongitude();
            Float longitude2 = p.getDestination().getLongitude();
            Float latitude2 = p.getDestination().getLatitude();
            coordinates.add(longitude1);
            coordinates.add(longitude2);
            coordinates.add(latitude1);
            coordinates.add(latitude2);
        }
        return coordinates;
    }


    public Double calculatePrice(VehicleType vehicleType, Double kilometres){

        Double price = 120 * kilometres;
        if(vehicleType.equals(VehicleType.STANDARDNO)){
            price += 70;
        }
        else if(vehicleType.equals(VehicleType.KOMBI)){
            price += 50;
        }
        else if(vehicleType.equals(VehicleType.LUKSUZNO)){
            price +=100;
        }

        return price;
    }


    public List<RideDTO> getAllActiveRides() {
        List<Ride> rs = this.rideRepository.findAllByStatus("STARTED");
        List<RideDTO> rides = new ArrayList<>();
        for(Ride r : rs){
            RideDTO ride = new RideDTO(r);
            rides.add(ride);
        }
        return rides;
    }

    public List<RideDTO> getAllAcceptedRides() {
        List<Ride> rs = this.rideRepository.findAllByStatus("ACCEPTED");
        List<RideDTO> rides = new ArrayList<>();
        for(Ride r : rs){
            RideDTO ride = new RideDTO(r);
            rides.add(ride);
        }
        return rides;
    }

    public Ride findActiveRideByDriverId(Integer id){
        return this.rideRepository.findOneByDriverIdAndStatus(id, "STARTED");
    }

//    private void findRideReviewsAndRejections(Set<Ride> rides){
//        for (Ride r: rides) {
//            Set<Review> reviews = reviewRepository.findAllByRideId(r.getId());
//            Set<Rejection> rejections = rejectionRepository.findAllByRideId(r.getId());
//            r.addReviews(reviews);
//            r.addRejections(rejections);
//        }
//
//    }
        public Integer checkAllByUserId(Integer id){
            Set<Ride> rides = rideRepository.findAllByDriverId(id);
            if (rides.isEmpty()){
                rides = rideRepository.findAllByPassengersId(id);
            }
            return rides.size();
}

}
