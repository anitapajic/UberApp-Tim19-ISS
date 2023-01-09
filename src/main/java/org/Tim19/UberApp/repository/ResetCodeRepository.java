package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.ResetCode;
import org.Tim19.UberApp.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetCodeRepository extends JpaRepository<ResetCode,Integer> {

    public ResetCode findOneById(Integer id);

    public ResetCode findOneByUsername(String username);

    public ResetCode findOneByCode(Integer code);


}
