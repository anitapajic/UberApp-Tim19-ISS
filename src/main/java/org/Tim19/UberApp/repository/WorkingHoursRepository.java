package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.WorkingHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {
    public WorkingHours findOneById(String id);

    public Page<WorkingHours> findAll(Pageable pageable);


    List<WorkingHours> findWorkingHoursByDriver(Integer id);

    public Set<WorkingHours> findByDriverId(Integer id);
}
