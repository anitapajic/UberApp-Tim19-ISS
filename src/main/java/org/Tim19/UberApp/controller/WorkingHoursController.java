package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.DriverDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.WorkingHours;
import org.Tim19.UberApp.repository.WorkingHoursRepository;
import org.Tim19.UberApp.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/driver/{id}/working-hours")
public class WorkingHoursController {

    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getWorkingHoursFromDriver(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "4") Integer size) {

        Pageable paging = PageRequest.of(page, size);
        Page<WorkingHours> pagedResult = workingHoursRepository.findAll( paging);


        Map<String, Object> response = new HashMap<>();
        response.put("totalcounts", pagedResult.getTotalElements());
        response.put("results", pagedResult.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
