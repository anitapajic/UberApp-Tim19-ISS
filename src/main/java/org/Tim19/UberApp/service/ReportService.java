package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RideService rideService;


    public Double getTotalIncome(){
        Double income = 0.0;
        for (Ride r : rideRepository.findAll()) {
            income += r.getTotalCost();
        }
        return income;
    }

    public Double getTodaysIncome(){
        Double income = 0.0;
        List<Ride> rides = rideRepository.findAll();
        for (Ride r : rides){
            if(r.getStartTime().getDayOfYear() == LocalDateTime.now().getDayOfYear()){
                income += r.getTotalCost();
            }
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
            from2 = from2.plusDays(1);
        }

        if( from != null && to != null){
            rides = rideRepository.findAllInDateRange(from, to);
            for(Ride r: rides){
                String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                Double value = r.getTotalCost();
                incomeByDays.put(key, incomeByDays.get(key) + value);
                System.out.println(key + " " + (incomeByDays.get(key) + value));
            }
        }


        return incomeByDays;
    }
    public HashMap<String, Double> getDriverIncomeFromDates(LocalDateTime from, LocalDateTime to, Integer driverId){
        List<Ride> rides;
        LocalDateTime from2 = from;
        HashMap<String, Double> incomeByDays = new HashMap<>();
        for(int i = 0; i <7; i++){
            incomeByDays.put(String.valueOf(LocalDate.of(from2.getYear(), from2.getMonthValue(), from2.getDayOfMonth())), 0.0);
            from2 = from2.plusDays(1);
        }

        if( from != null && to != null){
            rides = rideRepository.findAllInDateRange(from, to);

            for(Ride r: rides){
                if(r.getDriver().getId() == driverId){
                    String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                    Double value = r.getTotalCost();
                    incomeByDays.put(key, incomeByDays.get(key) + value);
                    System.out.println(key + " " + (incomeByDays.get(key) + value));
                }
            }
        }

        return incomeByDays;
    }
    public HashMap<String, Double> getPassengerOutcomeFromDates(LocalDateTime from, LocalDateTime to, Integer passengerId){

        List<Ride> rides;
        LocalDateTime from2 = from;
        HashMap<String, Double> incomeByDays = new HashMap<>();
        for(int i = 0; i <7; i++){
            incomeByDays.put(String.valueOf(LocalDate.of(from2.getYear(), from2.getMonthValue(), from2.getDayOfMonth())), 0.0);
            from2 = from2.plusDays(1);
        }

        if( from != null && to != null){
            rides = rideRepository.findAllInDateRange(from, to);

            for(Ride r: rides){
                for(Passenger p : r.getPassengers()){
                    if(p.getId().equals(passengerId)){
                        String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                        Double value = r.getTotalCost();
                        incomeByDays.put(key, incomeByDays.get(key) + value);
                        System.out.println(key + " " + (incomeByDays.get(key) + value));
                    }
                }
            }
        }

        return incomeByDays;
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


    public HashMap<String, Integer> getRidesFromDates(LocalDateTime from, LocalDateTime to){
        List<Ride> rides;
        LocalDateTime from2 = from;
        HashMap<String, Integer> ridesByDays = new HashMap<>();
        for(int i = 0; i <7; i++){
            ridesByDays.put(String.valueOf(LocalDate.of(from2.getYear(), from2.getMonthValue(), from2.getDayOfMonth())), 0);
            from2 = from2.plusDays(1);
        }
        Integer value = 0;

        if( from != null && to != null){
            rides = rideRepository.findAllInDateRange(from, to);
            for(Ride r: rides){
                String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                value = 1;
                ridesByDays.put(key, ridesByDays.get(key) + value);
            }
        }

        return ridesByDays;
    }

    public HashMap<String, Integer> getNumOfDriverRidesFromDate(Integer driverId){
        List<LocalDateTime> dates = new ArrayList<>();
        for(Ride r : rideRepository.findAllByDriverId(driverId)){
            dates.add(r.getStartTime());
        }
        HashMap<String, Integer> ridesByDays = new HashMap<>();
        for(LocalDateTime date:dates){
            ridesByDays.put(String.valueOf(LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth())), 0);
            date = date.plusDays(1);
        }
        Integer value = 0;
        for(Ride r: rideRepository.findAllByDriverId(driverId)){
                if(r.getDriver().getId().equals(driverId)){
                    String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                    value = 1;
                    ridesByDays.put(key, ridesByDays.get(key) + value);
                }

        }

        return ridesByDays;
    }

    public HashMap<String, Integer> getNumOfPassengerRidesFromDate(Integer passengerId){
        List<LocalDateTime> dates = new ArrayList<>();
        for(Ride r : rideRepository.findAllByPassengersId(passengerId)){
            dates.add(r.getStartTime());
        }
        HashMap<String, Integer> ridesByDays = new HashMap<>();
        for(LocalDateTime date:dates){
            ridesByDays.put(String.valueOf(LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth())), 0);
            date = date.plusDays(1);
        }
        Integer value = 0;
            for(Ride r: rideRepository.findAllByPassengersId(passengerId)){
                for(Passenger p : r.getPassengers()){
                    if(p.getId().equals(passengerId)){
                        String key = String.valueOf(LocalDate.of(r.getStartTime().getYear(), r.getStartTime().getMonthValue(), r.getStartTime().getDayOfMonth()));
                        value = 1;
                        ridesByDays.put(key, ridesByDays.get(key) + value);
                    }
                }
            }

        return ridesByDays;
    }

    public Double getNumOfKm(){
        Double km = 0.0;
        List<Ride> rides = rideRepository.findAll();
        for(Ride r : rides){
            List<Float> coordinates = rideService.getCoordinates(r.getLocations());
            Float long1 = coordinates.get(0);
            Float long2 = coordinates.get(1);
            Float lat1 = coordinates.get(2);
            Float lat2 = coordinates.get(3);
            km += rideService.calculateKilometres(long1, long2, lat1, lat2);
        }
        return km;
    }

    public Double driverNumOfKm(Integer driverId){
        Double km = 0.0;
        for(Ride r : rideRepository.findAllByDriverId(driverId)){
            List<Float> coordinates = rideService.getCoordinates(r.getLocations());
            Float long1 = coordinates.get(0);
            Float long2 = coordinates.get(1);
            Float lat1 = coordinates.get(2);
            Float lat2 = coordinates.get(3);
            km += rideService.calculateKilometres(long1, long2, lat1, lat2);
        }
        return km;
    }
    public Double passengerNumOfKm(Integer passengerId){
        Double km = 0.0;
        for(Ride r : rideRepository.findAllByPassengersId(passengerId)){
            List<Float> coordinates = rideService.getCoordinates(r.getLocations());
            Float long1 = coordinates.get(0);
            Float long2 = coordinates.get(1);
            Float lat1 = coordinates.get(2);
            Float lat2 = coordinates.get(3);
            km += rideService.calculateKilometres(long1, long2, lat1, lat2);
        }
        return km;
    }
}
