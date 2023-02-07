package org.Tim19.UberApp.unit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RideServiceTests {

    @Autowired
    private RideService rideService;

    @MockBean
    private RideRepository rideRepository;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private DriverService driverService;

    @Autowired
    private RestTemplate restTemplate;

    private RideDTO rideDTO = new RideDTO();
    private RideDTO rideDTO2;
    private Ride ride;
    private Ride ride2;
    private Driver driver;
    private Vehicle vehicle;
    private DriverDocument document;
    private List documents;
    private Passenger passenger;


    @BeforeAll
    public void setup(){

        rideRepository = mock(RideRepository.class);
        passengerService = mock(PassengerService.class);
        driverService = mock(DriverService.class);

        rideService = new RideService(rideRepository);


//        Set<Path> locations = new HashSet<>();
//        Location departure = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
//        Location destination = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
//        Path path = new Path(null, departure, destination);
//        locations.add(path);
//
//        rideDTO.setLocations(locations);
//        rideDTO.setBabyTransport(false);
//        rideDTO.setPetTransport(false);

//        ride2 = new Ride(rideDTO);
        Set<Path> locations = new HashSet<>();
        Location departure = new Location(5, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(6, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(14, departure, destination);
        vehicle = new Vehicle(1, "audi", VehicleType.STANDARDNO, "lo123td", 4, departure, true, true);
        Set<Ride> rides = new HashSet<>();
        Driver driver = new Driver(1, "driver@gmail.com", "Driver", "Rider", "hcierhfi", "64584685689", "Adresa",
                "sifra", true, false, rides, null, vehicle, "DRIVER", false);
        Passenger passenger = new Passenger();
        passenger.setId(2);
        passenger.setUsername("anita@gmail.com");
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(passenger);
        locations.add(path);

        RideDTO rideDTO = new RideDTO(1, LocalDateTime.now(), null, 350.0, driver, passengers,
                7, null, driver.getVehicle(), false, false, false, "STARTED", locations,
                null, "", 0);
        ride = new Ride(rideDTO, true);


        //Ride2
        Set<Path> locations2 = new HashSet<>();
        Location departure2 = new Location(5, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination2 = new Location(6, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path2 = new Path(null, departure2, destination2);
        vehicle = new Vehicle(2, "audi", VehicleType.STANDARDNO, "lo123td", 4, departure, true, true);
        locations.add(path2);
        RideDTO rideDTO2 = new RideDTO(2, LocalDateTime.now(), null, 350.0, driver, passengers,
                7, null, driver.getVehicle(), false, false, false, "ACCEPTED", locations2,
                null, "", 0);
        ride2 = new Ride(rideDTO2, true);
    }

// ============================================================
// SAVE
// ============================================================

    @Test
    @DisplayName("Test Should Save New Ride")
    public void shouldSaveRide(){
        when(rideRepository.save(ride)).thenReturn(ride);
        Ride savedRide = rideService.save(ride);
        assertEquals(ride, savedRide);

    }

// ============================================================
// CALCULATE PRICE
// ============================================================

    @Test
    public void testCalculatePriceForStandardno() {
        Double kilometres = 10.0;
        Double expectedPrice = 120 * kilometres + 70;
        Double actualPrice = rideService.calculatePrice(VehicleType.STANDARDNO, kilometres);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testCalculatePriceForKombi() {
        Double kilometres = 20.0;
        Double expectedPrice = 120 * kilometres + 50;
        Double actualPrice = rideService.calculatePrice(VehicleType.KOMBI, kilometres);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testCalculatePriceForLuksuzno() {
        Double kilometres = 30.0;
        Double expectedPrice = 120 * kilometres + 100;
        Double actualPrice = rideService.calculatePrice(VehicleType.LUKSUZNO, kilometres);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testCalculatePriceForZeroKilometres() {
        Double kilometres = 0.0;
        Double expectedPrice = 70.0;
        Double actualPrice = rideService.calculatePrice(VehicleType.STANDARDNO, kilometres);
        assertEquals(expectedPrice, actualPrice);
    }

// =====================================================
// CALCULATE TRAVEL TIME
// =====================================================

    @Test
    public void testCalculateTravelTimeWithPositiveDistance() {
        Double distance = 10.0;
        Integer expectedTime = 40;
        Integer actualTime = rideService.calculateTravelTime(distance);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testCalculateTravelTimeWithZeroDistance() {
        Double distance = 0.0;
        Integer expectedTime = 0;
        Integer actualTime = rideService.calculateTravelTime(distance);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testCalculateTravelTimeWithNegativeDistance() {
        Double distance = -10.0;
        Integer actualTime = rideService.calculateTravelTime(distance);
        assertTrue(actualTime < 0);
    }

    @Test
    public void testCalculateTravelTimeWithLargeDistance() {
        Double distance = 1000.0;
        Integer expectedTime = 4000;
        Integer actualTime = rideService.calculateTravelTime(distance);
        assertEquals(expectedTime, actualTime);
    }

// ================================================
// CALCULATE KILOMETRES
// =================================================

    @Test
    public void testCalculateKilometresWithPositiveCoordinates() {
        Float long1 = 10.0f;
        Float lat1 = 20.0f;
        Float long2 = 30.0f;
        Float lat2 = 40.0f;
        Double expectedKilometres = (Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((long1 - long2), 2)))*150;
        Double actualKilometres = rideService.calculateKilometres(long1, long2, lat1, lat2);
        assertEquals(expectedKilometres, actualKilometres, 0.01);
    }

    @Test
    public void testCalculateKilometresWithNegativeCoordinates() {
        Float long1 = -10.0f;
        Float lat1 = -20.0f;
        Float long2 = -30.0f;
        Float lat2 = -40.0f;
        Double expectedKilometres = (Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((long1 - long2), 2)))*150;
        Double actualKilometres = rideService.calculateKilometres(long1, long2, lat1, lat2);
        assertEquals(expectedKilometres, actualKilometres, 0.01);
    }

    @Test
    public void testCalculateKilometresWithIdenticalCoordinates() {
        Float long1 = 10.0f;
        Float lat1 = 20.0f;
        Float long2 = 10.0f;
        Float lat2 = 20.0f;
        Double expectedKilometres = (Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((long1 - long2), 2)))*150;
        Double actualKilometres = rideService.calculateKilometres(long1, long2, lat1, lat2);
        assertEquals(expectedKilometres, actualKilometres, 0.01);
    }

    @Test
    public void testCalculateKilometresWithLargeCoordinates() {
        Float long1 = 1000.0f;
        Float lat1 = 2000.0f;
        Float long2 = 3000.0f;
        Float lat2 = 4000.0f;
        Double expectedKilometres = (Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((long1 - long2), 2)))*150;
        Double actualKilometres = rideService.calculateKilometres(long1, long2, lat1, lat2);
        assertEquals(expectedKilometres, actualKilometres, 0.01);
    }

// ===============================================
// GET COORDINATES
// ===============================================

    @Test
    public void testGetCoordinatesWithEmptySet() {
        Set<Path> locations = new HashSet<>();
        List<Float> result = rideService.getCoordinates(locations);
        assertEquals(result.size(), 0);
    }

    @Test
    public void testGetCoordinatesWithSingleElement() {
        Set<Path> locations = new HashSet<>();
        Path path = new Path();
        Location departure = new Location();
        departure.setLatitude(1.0f);
        departure.setLongitude(1.0f);
        path.setDeparture(departure);
        Location destination = new Location();
        destination.setLatitude(2.0f);
        destination.setLongitude(2.0f);
        path.setDestination(destination);
        locations.add(path);

        List<Float> result = rideService.getCoordinates(locations);

        assertEquals(result.size(), 4);
        assertEquals(result.get(0), (Float) 1.0f);
        assertEquals(result.get(1), (Float) 2.0f);
        assertEquals(result.get(2), (Float) 1.0f);
        assertEquals(result.get(3), (Float) 2.0f);
    }

    @Test
    public void testGetCoordinatesWithMultipleElements() {
        Set<Path> locations = new HashSet<>();
        Path path1 = new Path();
        Location departure1 = new Location();
        departure1.setLatitude(1.0f);
        departure1.setLongitude(1.0f);
        path1.setDeparture(departure1);
        Location destination1 = new Location();
        destination1.setLatitude(2.0f);
        destination1.setLongitude(2.0f);
        path1.setDestination(destination1);
        locations.add(path1);
        Path path2 = new Path();
        Location departure2 = new Location();
        departure2.setLatitude(3.0f);
        departure2.setLongitude(3.0f);
        path2.setDeparture(departure2);
        Location destination2 = new Location();
        destination2.setLatitude(4.0f);
        destination2.setLongitude(4.0f);
        path2.setDestination(destination2);
        locations.add(path2);

        List<Float> result = rideService.getCoordinates(locations);

        assertEquals(result.size(), 8);
        assertEquals(result.get(0), (Float) 1.0f);
        assertEquals(result.get(1), (Float) 2.0f);
    }

    @Test
    public void testGetCoordinatesCount() {
        Set<Path> locations = new HashSet<>();
        locations.add(new Path(1,new Location(1,"",1.0f, 2.0f), new Location(3,"",3.0f, 4.0f)));
        locations.add(new Path(2,new Location(2, "",5.0f, 6.0f), new Location(4, "",7.0f, 8.0f)));

        List<Float> result = rideService.getCoordinates(locations);
        assertEquals(8, result.size());
    }

    @Test
    public void testGetCoordinatesValues() {
        Set<Path> locations = new HashSet<>();
        locations.add(new Path(1,new Location(1,"",1.0f, 2.0f), new Location(3,"",3.0f, 4.0f)));
        locations.add(new Path(2,new Location(2, "",5.0f, 6.0f), new Location(4, "",7.0f, 8.0f)));

        List<Float> result = rideService.getCoordinates(locations);
        List<Float> expected = Arrays.asList(6.0f, 8.0f, 5.0f, 7.0f, 2.0f, 4.0f, 1.0f, 3.0f);
        assertEquals(expected, result);
    }

// ===============================================
// GET NEXT LOCATION
// ===============================================

    ///TODO : FIX THIS
    @Test
    public void testFindNextLocation() throws JsonProcessingException {
        Location departure = new Location();
        departure.setLatitude(45.239877f);
        departure.setLongitude(19.849011f);

        Location destination = new Location();
        destination.setLatitude(45.243911f);
        destination.setLongitude(19.830894f);

        Location nextLocation = rideService.findNextLocation(departure, destination);

        assertNotNull(nextLocation);
        assertNotNull(nextLocation.getLatitude());
        assertNotNull(nextLocation.getLongitude());
    }

    @Test
    public void testFindNextLocationNull() throws JsonProcessingException{
        Location departure = new Location();
        Location destination = new Location();
        Location nextLocation = rideService.findNextLocation(departure, destination);
        assertNull(nextLocation);
    }


// ========================================================
// FIND RIDE BY ID
// ========================================================
    @Test
    public void findOneRideById_whenRideExists_returnsRide() {
        when(rideRepository.findById(1)).thenReturn(Optional.of(ride));

        Ride actualRide = rideService.findOneRideById(1);
        System.out.println(actualRide);

        assertEquals(ride, actualRide);
    }

    @Test
    public void findOneRideById_whenRideDoesNotExist_returnsNull() {
        when(rideRepository.findById(123)).thenReturn(Optional.empty());

        Ride actualRide = rideService.findOneRideById(123);

        assertEquals(null, actualRide);
    }

// ========================================================
// FIND ALL ACTIVE RIDES
// ========================================================

    @Test
    public void testFindAllActiveRides() {
        List<Ride> rides = new ArrayList<>();

        rides.add(ride);
        when(rideRepository.findAllByStatus("STARTED")).thenReturn(rides);

        List<RideDTO> filteredRides = rideService.findAllActiveRides();
        assertEquals(1, filteredRides.size());
        verify(rideRepository, times(1)).findAllByStatus("STARTED");
    }

    @Test
    public void testFindAllActiveRides_NoActiveRides() {
        List<Ride> rides = new ArrayList<>();
        when(rideRepository.findAllByStatus("STARTED")).thenReturn(rides);

        List<RideDTO> filteredRides = rideService.findAllActiveRides();
        assertEquals(0, filteredRides.size());

        verify(rideRepository, times(1)).findAllByStatus("STARTED");
    }


// ========================================================
// FIND ALL ACCEPTED RIDES
// ========================================================

    @Test
    public void testFindAllAcceptedRides() {
        List<Ride> rides = new ArrayList<>();

        rides.add(ride2);
        when(rideRepository.findAllByStatus("ACCEPTED")).thenReturn(rides);

        List<RideDTO> filteredRides = rideService.findAllAcceptedRides();
        assertEquals(1, filteredRides.size());
        verify(rideRepository, times(1)).findAllByStatus("ACCEPTED");
    }


    @Test
    public void testFindAllAcceptedRides_NoAcceptedRides() {
        List<Ride> rides = new ArrayList<>();
        when(rideRepository.findAllByStatus("ACCEPTED")).thenReturn(rides);

        List<RideDTO> filteredRides = rideService.findAllAcceptedRides();
        assertEquals(0, filteredRides.size());

        verify(rideRepository, times(1)).findAllByStatus("ACCEPTED");
    }


    // =========================================================
// FIND RIDES BY USER ID
// =========================================================

    @Test
    public void testFindByUserId_OnlyPassengerId() {
        // Test data
        Integer userId = 2;

        List<Ride> driverRides = new ArrayList<Ride>();
        List<Ride> passengerRides = new ArrayList<Ride>();
        driverRides.add(ride);
        passengerRides.add(ride);

        Pageable pageable = PageRequest.of(0, 10);
        // Mock the ride repository
        Page<Ride> driverRidesPage = new PageImpl<>(driverRides);
        Page<Ride> passengerRidesPage = new PageImpl<>(passengerRides);
        when(rideRepository.findAllByDriverId(userId, pageable))
                .thenReturn(driverRidesPage);
        when(rideRepository.findAllByPassengersId(userId, pageable))
                .thenReturn(passengerRidesPage);

        // Test the findByUserId method

        Page<Ride> rides = rideService.findByUserId(userId, pageable);
        assertEquals(1, rides.getTotalElements());
    }

    @Test
    public void testFindByUserId_OnlyDriverId(){

        Integer driverId = 4;

        List<Ride> driverRides = new ArrayList<Ride>();
        List<Ride> passengerRides = new ArrayList<Ride>();
        driverRides.add(ride);
        passengerRides.add(ride);

        Pageable pageable = PageRequest.of(0, 10);

        // Test case 1: user is only a driver
        Page<Ride> driverRidesPage = new PageImpl<>(driverRides);
        when(rideRepository.findAllByDriverId(driverId, pageable))
                .thenReturn(driverRidesPage);
        when(rideRepository.findAllByPassengersId(driverId, pageable))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<Ride> rides = rideService.findByUserId(driverId, pageable);
        assertEquals(1, rides.getTotalElements());
        assertEquals(driverRides, rides.getContent());
    }


// =========================================================
// FIND RIDES BY PASSENGER ID
// =========================================================


    @Test
    public void testFindByPassengerId_WithFromAndTo() {
        Integer id = 2;

        LocalDateTime startDate = LocalDateTime.of(2022,1,1,15,20);
        LocalDateTime endTime = LocalDateTime.of(2023,12,12,15,20);

        Pageable pageable = PageRequest.of(0, 10);
        List<Ride> ridesPassenger = new ArrayList<>();
        ridesPassenger.add(ride);
        Page<Ride> expectedRides = new PageImpl<>(ridesPassenger);

        when(rideRepository.findAllByPassengersIdFilter(id, startDate, endTime, pageable))
                .thenReturn(expectedRides);

        Page<Ride> rides = rideService.findByPassengerId(id, String.valueOf(startDate), String.valueOf(endTime), pageable);

        assertEquals(expectedRides, rides);
    }

    @Test
    public void testFindByPassengerId_WithoutFromAndTo() {
        Integer id = 2;
        Pageable pageable = PageRequest.of(0, 10);

        List<Ride> ridesPassenger = new ArrayList<>();
        ridesPassenger.add(ride);
        Page<Ride> expectedRides = new PageImpl<>(ridesPassenger);

        when(rideRepository.findAllByPassengersId(id, pageable))
                .thenReturn(expectedRides);

        Page<Ride> rides = rideService.findByPassengerId(id, null, null, pageable);

        assertNotNull(rides);
        assertEquals(expectedRides, rides);
    }

// =========================================================
// FIND ALL RIDES BY PASSENGER ID
// =========================================================

    @Test
    public void testFindAllByPassengerId_Success() {
        Integer id = 2;
        Set<Ride> expectedRides = new HashSet<>(Arrays.asList(ride, ride2));

        when(rideRepository.findAllByPassengersId(2))
                .thenReturn(expectedRides);

        Set<Ride> rides = rideService.findAllByPassengerId(id);

        assertNotNull(rides);
        assertEquals(rides, expectedRides);
    }


    @Test
    public void testFindAllByPassengerId_NoRidesFound() {
        when(rideRepository.findAllByPassengersId(1)).thenReturn(new HashSet<>());

        Set<Ride> rides = rideService.findAllByPassengerId(1);
        assertTrue(rides.isEmpty());
    }


    @Test
    public void testFindAllByPassengerId_DuplicateRides() {
        Set<Ride> expectedRides = new HashSet<>(Arrays.asList(ride, ride, ride2));
        when(rideRepository.findAllByPassengersId(2)).thenReturn(expectedRides);

        Set<Ride> rides = rideService.findAllByPassengerId(2);
    }
// ========================================================
// FIND ALL FILTER
// ========================================================
    @Test
    public void testFindAllFilter() {
        List<Ride> mockRides = Arrays.asList(ride,ride2);
        when(rideRepository.findAll()).thenReturn(mockRides);

        RideHistoryFilterDTO filter = new RideHistoryFilterDTO();
        filter.setKeyword("");

        List<Ride> rides = rideService.findAllFilter(filter);

        assertNotNull(rides);

        assertEquals(2, rides.size());
    }

    @Test

    public void testFindAllByPassengerId_CorrectOrder() {
        List<Ride> expectedRides = Arrays.asList(ride, ride2);
        when(rideRepository.findAllByPassengersId(2)).thenReturn(new HashSet<>(expectedRides));

        Set<Ride> rides = rideService.findAllByPassengerId(2);
        assertEquals(rides, new HashSet<>(expectedRides));
    }

//==========================================================
// FIND RIDES BY DRIVER ID
//==========================================================

    @Test
    public void testFindByDriverId_WithFromAndTo() {
        Integer id = 1;

        LocalDateTime startDate = LocalDateTime.of(2022,1,1,15,20);
        LocalDateTime endTime = LocalDateTime.of(2023,12,12,15,20);
        Pageable pageable = PageRequest.of(0, 10);
        List<Ride> ridesDriver = new ArrayList<>();
        ridesDriver.add(ride);

        Page<Ride> expectedRides = new PageImpl<>(ridesDriver);


        when(rideRepository.findAllByDriverIdFilter(id, startDate, endTime, pageable))
                .thenReturn(expectedRides);

        Page<Ride> rides = rideService.findByDriverId(id, String.valueOf(startDate), String.valueOf(endTime), pageable);

        assertEquals(rides, expectedRides);
    }

    @Test
    public void testFindByDriverId_WithoutFromAndTo() {
        Integer id = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ride> expectedRides = new PageImpl<>(Collections.emptyList());

        when(rideRepository.findAllByDriverId(id, pageable))
                .thenReturn(expectedRides);

        Page<Ride> rides = rideService.findByDriverId(id, null, null, pageable);

        assertNotNull(rides);
        assertEquals(rides, expectedRides);
    }



    @Test
    public void testFindAllFilter_withEmptyFilter() {

        List<Ride> mockRides = Arrays.asList(ride,ride2);
        when(rideRepository.findAll()).thenReturn(mockRides);

        RideHistoryFilterDTO filter = new RideHistoryFilterDTO();

        List<Ride> rides = rideService.findAllFilter(filter);

        assertNotNull(rides);
        assertEquals(2, rides.size());
    }


}
