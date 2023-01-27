package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, Double> getIncomeFromDates(LocalDateTime from, LocalDateTime to){
        List<Ride> rides;
        LocalDateTime from2 = from;
        HashMap<String, Double> incomeByDays = new HashMap<>();
        for(int i = 0; i <7; i++){
            incomeByDays.put(String.valueOf(LocalDate.of(from2.getYear(), from2.getMonthValue(), from2.getDayOfMonth())), 0.0);
            System.out.println(java.sql.Timestamp.valueOf(from2));
            from2 = from2.plusDays(1);
            System.out.println(from2);
        }

        if( from != null && to != null){
            rides = rideRepository.findAllInDateRange(from, to);
            for(Ride r: rides){
                String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                Double value = r.getTotalCost();
                incomeByDays.put(key, incomeByDays.get(key) + value);
            }
        }


        return incomeByDays;
    }
    public void getDriverIncomeFromDates(LocalDate from, LocalDate to, Integer driverId){

    }
    public void getPassengerOutcomeFromDates(LocalDate from, LocalDate to, Integer passengerId){

    }

    public Integer getTotalNumberOfRides(){
        Integer total = 0;
        for(Ride r : rideRepository.findAll()){
            if(r != null){
                total += 1;
            }
        }
        return  total;
    }

    public Integer getTotalNumberOfRidesFromOneDriver(Integer driverId){
        Integer total = 0;
        for (Ride r: rideRepository.findAllByDriverId(driverId)){
            if(r != null){
                total += 1;
            }
        }
        return total;
    }

    public Integer getTotalNumberOfRidesFromOnePassenger(Integer passengerId){
        Integer total = 0;
        for (Ride r: rideRepository.findAllByPassengersId(passengerId)){
            if(r != null){
                total += 1;
            }
        }
        return total;
    }
}
