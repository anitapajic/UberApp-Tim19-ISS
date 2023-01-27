package org.Tim19.UberApp.controller;

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

    //TOTAL PASSENGER OUTCOME  api/statistics/passengerOutcome/{passengerId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PASSENGER')")
    @GetMapping(value = "/passengerOutcome/{passengerId}")
    public ResponseEntity<Double> getTotalPassengerOutcome(@PathVariable Integer passengerId) {

        Double outcome = reportService.getTotalOutcomeFromOnePassenger(passengerId);
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

    //TOTAL NUMBER OF RIDES FROM ONE PASSENGER api/statistics/driverRides/{driverId}
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PASSENGER')")
    @GetMapping(value = "/driverRides/{passengerId}")
    public ResponseEntity<Integer> getTotalNumberOfRidesFromOnePassenger(@PathVariable Integer passengerId) {

        Integer total = reportService.getTotalNumberOfRidesFromOnePassenger(passengerId);

        return new ResponseEntity<>(total, HttpStatus.OK);
    }


}
