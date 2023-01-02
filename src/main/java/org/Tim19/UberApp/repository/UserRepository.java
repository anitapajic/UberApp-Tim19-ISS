package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findOneById(Integer id);

    public User findOneByEmailAndPassword(String email, String password);

    Optional<User> findOneByEmail(String email);

    public Page<User> findAll(Pageable pageable);

    public List<User> findByNameAndSurnameAllIgnoringCase(String firstname, String lastname);

    @Query( value = "select * from \"USER\" d where d.vehicle_id = ?1", nativeQuery = true)
    public  User findOneByVehicleId(Integer id);


//ovo je za sve voznje putnika sa zadatim id
//    select p.id, p.email, r.*, l1.*, l2.*
//    from "USER" p, user_rides pr, ride r, ride_paths rp, path path, location l1, location l2
//    where p.id = ?1
//    and p.id = pr.passenger_id
//    and r.id = pr.ride_id
//    and rp.ride_id= r.id
//    and rp.paths_id=path.id
//    and path.departure_id = l1.id
//    and path.destination_id = l2.id
//    @Query(value = "select r.* from \"USER\" p, passenger_ride pr, ride r, ride_paths rp, path path, location l1, location l2 where p.id = ?1 and p.id = pr.passenger_id and r.id = pr.ride_id and rp.ride_id= r.id and rp.paths_id=path.id and path.departure_id = l1.id and path.destination_id = l2.id", nativeQuery = true)
//    public Set<String> findAllRides(Integer id);


}
