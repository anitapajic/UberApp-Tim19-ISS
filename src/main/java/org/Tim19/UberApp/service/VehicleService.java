package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Vehicle;
import org.Tim19.UberApp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Optional<Vehicle> findOne(Integer id){return vehicleRepository.findById(id);}

    public List<Vehicle> findAll(){return vehicleRepository.findAll();}

    public Page<Vehicle> findAll(Pageable page){return vehicleRepository.findAll(page);}

    public Vehicle save(Vehicle driverDocument){return vehicleRepository.save(driverDocument);}

    public void remove(Integer id){vehicleRepository.deleteById(id);}

}

