package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.WorkingHours;
import org.Tim19.UberApp.repository.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WorkingHoursService {
    @Autowired
    private WorkingHoursRepository workingHoursRepository;

    public WorkingHours findOne(Integer id){return workingHoursRepository.findById(id).orElseGet(null);}

    public List<WorkingHours> findAll(){return workingHoursRepository.findAll();}

    public Page<WorkingHours> findAll(Pageable page){return workingHoursRepository.findAll(page);}



    public void remove(Integer id){
        workingHoursRepository.deleteById(id);}

    public Set<WorkingHours> findByDriverId(Integer id) {return workingHoursRepository.findByDriverId(id);}

    public List<WorkingHours> findWorkingHoursByDriver(Integer wh_id) {return workingHoursRepository.findWorkingHoursByDriver(wh_id);}

    public WorkingHours save(WorkingHours workingHours) {return workingHoursRepository.save(workingHours);}


}
