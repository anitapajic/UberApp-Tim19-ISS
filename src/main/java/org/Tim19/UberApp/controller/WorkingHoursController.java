package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDocumentDTO;
import org.Tim19.UberApp.dto.PaginatedData.WorkingHoursPaginatedDTO;
import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.dto.WorkingHoursDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/api/driver")
@CrossOrigin(value = "*")
public class WorkingHoursController {

    @Autowired
    private WorkingHoursService workingHoursService;
    @Autowired
    private DriverService driverService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value = "/{id}/working-hour")
    public ResponseEntity<Map<String, Object>> getDriversWH(@PathVariable Integer id,
                                                            @RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "4") Integer size,
                                                            @RequestParam(required = false) String sort,
                                                            @RequestParam(required = false) String  from,
                                                            @RequestParam(required = false) String  to){


        Set<WorkingHours> allDriverWH = workingHoursService.findByDriverId(id);
        Map<String, Object> response = new HashMap<>();
        response.put("totalCount",allDriverWH.size());
        response.put("results", allDriverWH);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/{id}/working-hour",consumes = "application/json")
    public ResponseEntity postDriversWH(@PathVariable Integer id,
                                                         @RequestBody WorkingHoursDTO workingHoursDTO){

        WorkingHours workingHours = new WorkingHours();
        Driver driver = driverService.findOne(id);
        if (driver == null) {
            return new ResponseEntity<>("Driver does not exist",HttpStatus.NOT_FOUND);
        }

        workingHours.setStartD(workingHoursDTO.getStart());
        workingHours.setEndD(workingHoursDTO.getEnd());
        workingHours.setDriver(driver);

        workingHours = workingHoursService.save(workingHours);
        return new ResponseEntity<>(new WorkingHoursDTO(workingHours), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DRIVER')")
    @GetMapping(value = "/working-hour/{wh_id}")
    public ResponseEntity<WorkingHours> getWHDetails(@PathVariable Integer wh_id){

        WorkingHours workingHours = workingHoursService.findOne(wh_id);
        return new ResponseEntity<>(workingHours,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(value = "/working-hour/{wh_id}",consumes = "application/json")
    public ResponseEntity chaneWHDetails(@PathVariable Integer wh_id,
                                                          @RequestBody WorkingHoursDTO update){

        WorkingHours workingHours = workingHoursService.findOne(wh_id);
        if (workingHours == null) {
            return new ResponseEntity<>("Working hours do not exist",HttpStatus.BAD_REQUEST);
        }

        workingHours.setStartD(update.getStart());
        workingHours.setEndD(update.getEnd());
        workingHours.setDriver(workingHours.getDriver());

        workingHours = workingHoursService.save(workingHours);

        return new ResponseEntity<>(new WorkingHoursDTO(workingHours), HttpStatus.OK);

    }
}
