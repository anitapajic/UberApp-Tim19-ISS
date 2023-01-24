package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.model.UpdateDriver;
import org.Tim19.UberApp.repository.DriverRepository;
import org.Tim19.UberApp.repository.UpdateDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private UpdateDriverRepository updateDriverRepository;

    public Driver findOne(Integer id){return driverRepository.findById(id).orElse(null);}

    public List<Driver> findAll(){return driverRepository.findAll();}

    public Page<Driver> findAll(Pageable page){return driverRepository.findAll(page);}

    public Driver save(Driver driver){return driverRepository.save(driver);}

    public void remove(Integer id){
        driverRepository.deleteById(id);}

    public UpdateDriver requestUpdateDriver(UpdateDriver driver){
        return updateDriverRepository.save(driver);
    }
    public List<UpdateDriver> getAllRequests(){
        return updateDriverRepository.findAll();
    }

    public UpdateDriver getRequestById(Integer id){
        return updateDriverRepository.findById(id).orElse(null);
    }

    public void delete(UpdateDriver updateDriver){
        updateDriverRepository.delete(updateDriver);
    }



    
}
