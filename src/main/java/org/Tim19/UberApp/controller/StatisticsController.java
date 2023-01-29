package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.FilterRidesFromDatesDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/api/statistics")
@CrossOrigin(value = "*")
public class StatisticsController {

    @Autowired
    private ReportService reportService;

    //TOTAL INCOME  api/statistics/totalIncome
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "/totalIncome")
    public ResponseEntity<Double> getTotalIncome() {

            Double income = reportService.getTotalIncome();
            return new ResponseEntity<>(income, HttpStatus.OK);
    }
    //TOTAL TODAYS INCOME  api/statistics/todaysIncome
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "/todaysIncome")
    public ResponseEntity<Double> getTodaysIncome() {

        Double income = reportService.getTodaysIncome();
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    //TOTAL INCOME FROM DATES api/statistics/date/totalIncome
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/date/totalIncome")
    public ResponseEntity<HashMap<String,Double>> getIncomeFromDates(@RequestBody RideHistoryFilterDTO filter) {

        HashMap<String, Double> income = reportService.getIncomeFromDates(filter.getStartDate(), filter.getEndDate());
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    //TOTAL DRIVER INCOME  api/statistics/driverIncome/{driverId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value = "/driverIncome/{driverId}")
    public ResponseEntity<Double> getTotalDriverIncome(@PathVariable Integer driverId) {

        Double income = reportService.getTotalIncomeFromOneDriver(driverId);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    //TOTAL DRIVER INCOME DROM DATES api/statistics/date/driverIncome/{driverId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @PostMapping(value = "/date/driverIncome/{driverId}")
    public ResponseEntity<HashMap<String, Double>> getTotalDriverIncomeFromDates(@RequestBody RideHistoryFilterDTO filter,
                                                                            @PathVariable Integer driverId) {

        HashMap<String, Double> income = reportService.getDriverIncomeFromDates(filter.getStartDate(), filter.getEndDate(), driverId);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    //TOTAL PASSENGER OUTCOME  api/statistics/passengerOutcome/{passengerId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PASSENGER')")
    @GetMapping(value = "/passengerOutcome/{passengerId}")
    public ResponseEntity<Double> getTotalPassengerOutcomeFromDates(@PathVariable Integer passengerId) {

        Double outcome = reportService.getTotalOutcomeFromOnePassenger(passengerId);
        return new ResponseEntity<>(outcome, HttpStatus.OK);
    }

    //TOTAL PASSENGER OUTCOME DROM DATES api/statistics/date/passengerOutcome/{passengerId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PASSENGER')")
    @PostMapping(value = "/date/passengerOutcome/{passengerId}")
    public ResponseEntity<HashMap<String, Double>> getTotalPassengerOutcome(@RequestBody RideHistoryFilterDTO filter,
                                                           @PathVariable Integer passengerId) {

        HashMap<String, Double> outcome = reportService.getPassengerOutcomeFromDates(filter.getStartDate(), filter.getEndDate(), passengerId);
        return new ResponseEntity<>(outcome, HttpStatus.OK);
    }

    //TOTAL NUMBER OF RIDES api/statistics/totalRides
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "/totalRides")
    public ResponseEntity<Integer> getTotalNumberOfRides() {

        Integer total = reportService.getTotalNumberOfRides();

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    //TOTAL NUMBER OF RIDES FROM ONE DRIVER api/statistics/driverRides/{driverId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value = "/driverRides/{driverId}")
    public ResponseEntity<Integer> getTotalNumberOfRidesFromOneDriver(@PathVariable Integer driverId) {

        Integer total = reportService.getTotalNumberOfRidesFromOneDriver(driverId);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    //TOTAL DRIVER RIDES FROM DATES api/statistics/date/rides/{passengerId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @PostMapping(value = "/date/rides/{driverId}")
    public ResponseEntity<HashMap<String,Integer>> getDriverRidesFromDates(@PathVariable Integer driverId,
                                                                              @RequestBody FilterRidesFromDatesDTO filter) {

        HashMap<String, Integer> rides = reportService.getNumOfDriverRidesFromDate(filter.getStartDateRides(), filter.getEndDateRides(), driverId);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //TOTAL NUMBER OF RIDES FROM ONE PASSENGER api/statistics/driverRides/{driverId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PASSENGER')")
    @GetMapping(value = "/driverRides/{passengerId}")
    public ResponseEntity<Integer> getTotalNumberOfRidesFromOnePassenger(@PathVariable Integer passengerId) {

        Integer total = reportService.getTotalNumberOfRidesFromOnePassenger(passengerId);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    //TOTAL PASSENGER RIDES FROM DATES api/statistics/date/rides/{passengerId}
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/date/rides/{passengerId}")
    public ResponseEntity<HashMap<String,Integer>> getPassengerRidesFromDates(@PathVariable Integer passengerId,
            @RequestBody FilterRidesFromDatesDTO filter) {

        HashMap<String, Integer> rides = reportService.getNumOfPassengerRidesFromDate(filter.getStartDateRides(), filter.getEndDateRides(), passengerId);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //TOTAL RIDES FROM DATES api/statistics/date/rides
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/date/rides")
    public ResponseEntity<HashMap<String,Integer>> getRidesFromDates(@RequestBody FilterRidesFromDatesDTO filter) {

        HashMap<String, Integer> rides = reportService.getRidesFromDates(filter.getStartDateRides(), filter.getEndDateRides());
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    //TOTAL NUMBER OFKILOMETRES api/statistics/km
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "/km")
    public ResponseEntity<Double> getNumberOfKilometres() {

        Double total = reportService.getNumOfKm();
        Double total2 = (double) Math.round(total);
        return new ResponseEntity<>(total2, HttpStatus.OK);
    }

}
