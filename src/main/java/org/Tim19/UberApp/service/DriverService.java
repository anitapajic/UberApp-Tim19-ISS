package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.Driver;
import org.Tim19.UberApp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver findOne(Integer id){return driverRepository.findById(id).orElseGet(null);}

    public List<Driver> findAll(){return driverRepository.findAll();}

    public Page<Driver> findAll(Pageable page){return driverRepository.findAll(page);}

    public Driver save(Driver driver){return driverRepository.save(driver);}

    public void remove(Integer id){
        driverRepository.deleteById(id);}

    public Driver findByEmail(String email){return driverRepository.findOneByEmail(email);}

    public List<Driver> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname){
        return driverRepository.findByNameAndSurnameAllIgnoringCase(firstname, lastname);
    }
    
}
