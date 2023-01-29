package org.Tim19.UberApp.service;

import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;
import org.Tim19.UberApp.repository.RejectionRepository;
import org.Tim19.UberApp.repository.ReviewRepository;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;


    public Ride findOneById(Integer id){return rideRepository.findById(id).orElse(null);}

    public Ride findOneRideById(Integer id){
        return rideRepository.findOneRideById(id);
    }

    public List<RideDTO> findAllFilter(RideHistoryFilterDTO filter){
        List<Ride> rides;
        if( filter.getStartDate() != null && filter.getEndDate() != null){
            rides = rideRepository.findAllInDateRange(filter.getStartDate(), filter.getEndDate());
        }
        else {
            rides = rideRepository.findAll();
        }
        System.out.println(rides.get(0));

        if(filter.getDriverId() != null){
            rides.removeIf(ride -> !Objects.equals(ride.getDriver().getId(), filter.getDriverId()));
        }
        if(filter.getKeyword() != null){
            rides.removeIf(ride -> !ride.toString().contains(filter.getKeyword()));
        }

        List<RideDTO> rideDTOS = new ArrayList<>();
        for(Ride r : rides){
            RideDTO rDTO = new RideDTO(r);
            rideDTOS.add(rDTO);
        }


        return rideDTOS;
    }

    public Page<Ride> findAll(Pageable page){return rideRepository.findAll(page);}

    public Ride save(Ride ride){return rideRepository.save(ride);}
    public RideDTO create(RideDTO rideDTO){
        Ride ride = new Ride(rideDTO);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser user = (SecurityUser) auth.getPrincipal();

        Passenger passenger = passengerService.findByEmail(user.getUsername());
        ride.addPassenger(passenger);

        Driver driver = this.findFreeDriver();
        ride.setDriver(driver);
        ride.setVehicleType(driver.getVehicle().getVehicleType());
        ride.setStatus("PENDING");
        ride.setPanic(false);


        List<Float> coordinates = rideDTO.getCoordinates();
        Float long1 = coordinates.get(0);
        Float long2 = coordinates.get(1);
        Float lat1 = coordinates.get(2);
        Float lat2 = coordinates.get(3);

        DecimalFormat df = new DecimalFormat("#.##");
        Double cost = this.calculatePrice(ride.getVehicleType(), this.calculateKilometres(long1, long2, lat1, lat2));
        ride.setTotalCost(Double.valueOf(df.format(cost)));
        ride.setEstimatedTimeInMinutes(this.calculateTravelTime(this.calculateKilometres(long1, long2, lat1, lat2)));


        ride = rideRepository.save(ride);
        return new RideDTO(ride);
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

    public Double calculatePrice(VehicleType vehicleType, Double kilometres){

        Double price = 170.0;
        if(vehicleType.equals(VehicleType.STANDARDNO)){
            price += 70*kilometres;
        }
        else if(vehicleType.equals(VehicleType.KOMBI)){
            price += 50*kilometres;
        }
        else if(vehicleType.equals(VehicleType.LUKSUZNO)){
            price +=100*kilometres;
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
