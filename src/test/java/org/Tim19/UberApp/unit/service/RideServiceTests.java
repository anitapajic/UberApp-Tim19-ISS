package org.Tim19.UberApp.unit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.repository.RideRepository;
import org.Tim19.UberApp.service.DriverService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.RideService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

        rideRepository = Mockito.mock(RideRepository.class);
        passengerService = Mockito.mock(PassengerService.class);
        driverService = Mockito.mock(DriverService.class);


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
        Location departure = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(null, departure, destination);
        vehicle = new Vehicle(null, "audi", VehicleType.STANDARDNO, "lo123td", 4, departure, true, true);
        Set<Ride> rides = new HashSet<>();
        Driver driver = new Driver(null, "driver@gmail.com", "Driver", "Rider", "hcierhfi", "64584685689", "Adresa",
                "sifra", true, false, rides, null, vehicle, "DRIVER", false);
        Passenger passenger = new Passenger();
        passenger.setId(2);
        passenger.setUsername("anita@gmail.com");
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(passenger);
        locations.add(path);

        RideDTO rideDTO = new RideDTO(123, LocalDateTime.now(), null, 350.0, driver, passengers,
                7, null, vehicle, false, false, false, "PENDING", locations,
                null, "", 0);
        ride = new Ride(rideDTO);
    }

//    @Test
//    @DisplayName("Test Should Save New Ride")
//    public void shouldSaveRide(){
//
//        when(rideRepository.findById(123)).thenReturn(Optional.ofNullable(ride));
//        Ride savedRide = rideService.save(ride);
//        assertEquals(ride, savedRide);
//
//    }

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
// SAVE STEP
// ========================================================



}
