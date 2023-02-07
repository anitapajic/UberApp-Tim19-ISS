package org.Tim19.UberApp.unit.repository;

import org.Tim19.UberApp.UberAppApplication;
import org.Tim19.UberApp.model.Ride;
import org.Tim19.UberApp.repository.RideRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UberAppApplication.class)
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
class RideRepositoryTest {

    @Autowired
    private RideRepository rideRepository;

    @Test
    void findById(){
        Ride ride =  rideRepository.findOneById(1);
        assertEquals(ride.getId(), 1);
    }

    @Test
    void findAllInDateRange() {
        LocalDateTime december21 = LocalDateTime.parse("2022-12-21T00:00:00");
        LocalDateTime december26 = LocalDateTime.parse("2022-12-26T00:00:00");
        List<Ride> rides = rideRepository.findAllInDateRange(december21, december26);
        assertTrue(rides.size() > 0);

    }


    @Test
    void findAllByDriverIdFilter() {
    }

    @Test
    void findAllByPassengersIdFilter() {
    }
}