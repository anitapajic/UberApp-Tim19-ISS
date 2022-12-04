package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findOneByEmail(String email);

    public Page<Users> findAll(Pageable pageable);

    public List<Users> findByFirstnameAndLastnameAllIgnoringCase(String firstname, String lastname);


}
