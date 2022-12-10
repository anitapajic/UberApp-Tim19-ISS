package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.PaginatedData.WorkingHoursPaginatedDTO;
import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.dto.WorkingHoursDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.model.WorkingHours;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/driver")
public class WorkingHoursController {

    @Autowired
    private WorkingHoursService workingHoursService;
    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/{id}/working-hours")
    public ResponseEntity<Map<String, Object>> getDriversWH(){

        WorkingHoursPaginatedDTO workingHours = new WorkingHoursPaginatedDTO(10,LocalDateTime.now(),LocalDateTime.now());
        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts",243);
        response.put("results",workingHours);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PostMapping(value = "/{id}/working-hours")
    public ResponseEntity<WorkingHoursDTO> postDriversWH(@PathVariable Integer id,@RequestBody WorkingHoursDTO workingHoursDTO){

        //Driver driver = new Driver(16,"tamara@gmail.com","tamara","dzambic","ahhajhsjah","0645554454","Brace Ribnikar 17","tam123",true,false,new HashSet<>(),new HashSet<>(),null);
        Driver driver = driverService.findOne(id);
        WorkingHours workingHours= new WorkingHours();
        workingHours.setDriver(driver);
        workingHours.setStartD(LocalDateTime.now());
        workingHours.setEndD(LocalDateTime.now());
        workingHours= workingHoursService.save(workingHours);

        return new ResponseEntity<>(new WorkingHoursDTO(workingHours), HttpStatus.CREATED);
    }

    @GetMapping(value = "/working-hours/{wh_id}")
    public ResponseEntity<WorkingHoursPaginatedDTO> getWHDetails(@PathVariable Integer wh_id){

        WorkingHoursPaginatedDTO workingHours = new WorkingHoursPaginatedDTO(wh_id,LocalDateTime.now(),LocalDateTime.now());

        return new ResponseEntity<>(workingHours,HttpStatus.OK);

    }
    @PutMapping(value = "/working-hours/{wh_id}")
    public ResponseEntity<Void> chaneWHDetails(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
