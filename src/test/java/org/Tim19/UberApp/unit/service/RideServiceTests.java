package org.Tim19.UberApp.unit.service;

import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RideServiceTests {

    @Autowired
    private RideService rideService;

    @MockBean
    private RideRepository rideRepository;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private DriverService driverService;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeAll
    public void setup(){
        rideRepository = Mockito.mock(RideRepository.class);
        passengerService = Mockito.mock(PassengerService.class);
        driverService = Mockito.mock(DriverService.class);
        Set<Path> locations = new HashSet<>();
        Location departure = new Location(1, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(2, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(1, departure, destination);
        Set<Ride> rides = new HashSet<>();
        Driver driver = new Driver(1, "driver@gmail.com", "Driver", "Rider", "hcierhfi", "64584685689", "Adresa",
                "sifra", true, false, rides, new HashSet<DriverDocument>(), new Vehicle(), "DRIVER", false);
        Passenger passenger = new Passenger();
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(passenger);
        locations.add(path);
        RideDTO rideDTO = new RideDTO(1, LocalDateTime.now(), null, 350.0, driver, passengers,
                7, new HashSet<Review>(), new Vehicle(), false, false, false, "PENDING", locations,
                new HashSet<Rejection>(), "", null);
        Ride ride = new Ride(rideDTO);
    }

    @Test
    @DisplayName("Test Should Save New Ride")
    public void shouldSaveRide(){


//        Mockito.when(rideRepository.findById(1)).thenReturn(Optional.of(ride));
//
//
//        Ride newRide = rideService.save(ride);
//        assertEquals(ride, newRide);
    }



}
