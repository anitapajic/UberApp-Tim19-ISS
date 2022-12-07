package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.WorkingHours;
import org.Tim19.UberApp.repository.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class WorkingHoursService {
    @Autowired
    private WorkingHoursRepository workingHours;

    public WorkingHours findOne(Integer id){return workingHours.findById(id).orElseGet(null);}

    public List<WorkingHours> findAll(){return workingHours.findAll();}

    public Page<WorkingHours> findAll(Pageable page){return workingHours.findAll(page);}

    public WorkingHours save(WorkingHours hours){return workingHours.save(hours);}

    public void remove(Integer id){
        workingHours.deleteById(id);}

}
