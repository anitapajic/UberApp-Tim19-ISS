package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;


    public Double getTotalIncome(){
        Double income = 0.0;
        for (Ride r : rideRepository.findAll()) {
            income += r.getTotalCost();
        }
        return income;
    }

    public Double getTotalIncomeFromOneDriver(Integer driverId){
        Double income = 0.0;
        for(Ride r : rideRepository.findAllByDriverId(driverId)){
            income += r.getTotalCost();
        }
        return income;
    }

    public Double getTotalOutcomeFromOnePassenger(Integer passengerId){
        Double outcome = 0.0;
        for(Ride r : rideRepository.findAllByPassengersId(passengerId)){
            outcome += r.getTotalCost();
        }
        return outcome;
    }

    public void getIncomeFromDates(LocalDate from, LocalDate to){

    }
    public void getDriverIncomeFromDates(LocalDate from, LocalDate to, Integer driverId){

    }
    public void getPassengerOutcomeFromDates(LocalDate from, LocalDate to, Integer passengerId){

    }
}
