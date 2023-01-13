package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.VehicleType;
import org.Tim19.UberApp.repository.RejectionRepository;
import org.Tim19.UberApp.repository.ReviewRepository;
import org.Tim19.UberApp.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RejectionRepository rejectionRepository;
    @Autowired
    private DriverService driverService;


    public Optional<Ride> findOneById(Integer id){return rideRepository.findById(id);}

    public Ride findOneRideById(Integer id){
        return rideRepository.findOneRideById(id);
    }

    public List<Ride> findAll(){return rideRepository.findAll();}

    public Page<Ride> findAll(Pageable page){return rideRepository.findAll(page);}

    public Ride save(Ride ride){return rideRepository.save(ride);}

    public void remove(Integer id){rideRepository.deleteById(id);}

    public Page<Ride>findByUserId(Integer id, Pageable pageable){
        Page<Ride> rides = rideRepository.findAllByDriverId(id, pageable);
        if (rides.isEmpty()){
            rides = rideRepository.findAllByPassengersId(id, pageable);
        }

        return rides;
    }

    public Page<Ride> findByPassengerId(Integer id, Pageable pageable){
        Page<Ride> rides = rideRepository.findAllByPassengersId(id, pageable);

        return rides;
    }
    public Set<Ride> findAllByPassengerId(Integer id){
        Set<Ride> rides = rideRepository.findAllByPassengersId(id);

        return rides;
    }
    public Page<Ride> findByDriverId(Integer id, Pageable pageable){
        Page<Ride> rides = rideRepository.findAllByDriverId(id, pageable);

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
        return distance;
    }

    public Integer calculateTravelTime(Double distance){
        Integer time = (int) ((distance)*2);
        return time;
    }

    public Double calculatePrice(VehicleType vehicleType, Double kilometres){

        Double price = 0.0;
        if(vehicleType.equals(VehicleType.STANDARDNO)){
            price = 170 + 70*kilometres;
        }
        else if(vehicleType.equals(VehicleType.KOMBI)){
            price = 170 + 50*kilometres;
        }
        else if(vehicleType.equals(VehicleType.LUKSUZNO)){
            price = 170 + 100*kilometres;
        }
        return price;
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

}
