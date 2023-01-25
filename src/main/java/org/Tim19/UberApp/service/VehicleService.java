package org.Tim19.UberApp.service;


import org.Tim19.UberApp.dto.LocationDTO;
import org.Tim19.UberApp.dto.VehicleDTO;
import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.repository.LocationRepository;
import org.Tim19.UberApp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverService driverService;
    @Autowired
    private LocationRepository locationRepository;


    public Vehicle findOne(Integer id){return vehicleRepository.findById(id).orElse(null);}


    public List<VehicleDTO> findAll(){
        List<Vehicle> vehicles = vehicleRepository.findAll();

        List<VehicleDTO> vehicleDTOS = new ArrayList<>();

        for (Vehicle vehicle: vehicles) {
            VehicleDTO vDTO = new VehicleDTO(vehicle);
            Driver d = driverService.isDriverActive(vehicle.getId());
            if(d != null){
                vDTO.setDriverId(d.getId());
                vDTO.setDriverActive(d.getActive());
            }
            if(vehicle.getLocation() != null){
                vDTO.setCurrentLocation(new LocationDTO(vehicle.getLocation()));
            }

            vehicleDTOS.add(vDTO);

        }

        return vehicleDTOS;}

    public Page<Vehicle> findAll(Pageable page){return vehicleRepository.findAll(page);}

    public Vehicle save(Vehicle vehicle){
        vehicle.setLocation(locationRepository.findById(1).orElse(null));

        return vehicleRepository.save(vehicle);}

    public void remove(Integer id){vehicleRepository.deleteById(id);}

}

