package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findOneByEmail(String email);

    public User findOneByEmailAndPassword(String email, String password);

    public Page<User> findAll(Pageable pageable);

    public List<User> findByFirstnameAndLastnameAllIgnoringCase(String firstname, String lastname);


}
