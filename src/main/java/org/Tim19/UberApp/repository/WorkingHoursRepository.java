package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.WorkingHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Integer> {
    public WorkingHours findOneById(String id);

    public Page<WorkingHours> findAll(Pageable pageable);
}
