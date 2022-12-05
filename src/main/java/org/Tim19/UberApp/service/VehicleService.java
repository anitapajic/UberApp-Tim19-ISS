package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.DriverDocument;
import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle findOne(Integer id){return vehicleRepository.findById(id).orElseGet(null);}

    public List<Vehicle> findAll(){return vehicleRepository.findAll();}

    public Page<Vehicle> findAll(Pageable page){return vehicleRepository.findAll(page);}

    public Vehicle save(Vehicle driverDocument){return vehicleRepository.save(driverDocument);}

    public void remove(Integer id){
        vehicleRepository.deleteById(id);}

    public Vehicle findByEmail(String driverId){return vehicleRepository.findOneById(driverId);}
}
