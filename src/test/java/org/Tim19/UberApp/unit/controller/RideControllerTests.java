package org.Tim19.UberApp.unit.controller;

import org.Tim19.UberApp.controller.RideController;
import org.Tim19.UberApp.dto.LoginDTO;
import org.Tim19.UberApp.dto.PaginatedData.PanicPaginatedDTO;
import org.Tim19.UberApp.dto.RideDTO;
import org.Tim19.UberApp.dto.RideHistoryFilterDTO;
import org.Tim19.UberApp.dto.TokenDTO;
import org.Tim19.UberApp.model.*;
import org.Tim19.UberApp.service.RideService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RideControllerTests {
    @Autowired
    private RideService rideService;
    @Autowired
    private RideController rideController;

    private HttpEntity<Void> passengerEntity;
    private HttpEntity<Void> driverEntity;
    private HttpEntity<Void> adminEntity;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port = 8085;

    private String getUrl(String url){
        return "http://localhost:" + port + "/api/ride/" + url;
    }

    private RideDTO createMockRideDTO(){
        Passenger passenger = new Passenger();
        passenger.setId(2);
        passenger.setUsername("anita@gmail.com");

       Set<Passenger> passengers = new HashSet<>();
        passengers.add(passenger);


        Set<Path> locations = new HashSet<>();
        Location departure = new Location(1, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(2, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(1, departure, destination);
        locations.add(path);

        RideDTO rideDTO = new RideDTO();
        rideDTO.setPassengers(passengers);
        rideDTO.setBabyTransport(false);
        rideDTO.setPetTransport(false);
        rideDTO.setLocations(locations);
        return rideDTO;
    }

    @BeforeAll
    public void setup(){
        ResponseEntity<TokenDTO> responsePassenger = restTemplate.postForEntity("http://localhost:8085/api/user/login",new LoginDTO("anita@gmail.com", "test"), TokenDTO.class);
        HttpHeaders headersPassenger = new HttpHeaders();
        headersPassenger.setContentType(MediaType.APPLICATION_JSON);
        headersPassenger.set("X-Auth-Token", responsePassenger.getBody().getToken());
        passengerEntity = new HttpEntity<>(headersPassenger);


        ResponseEntity<TokenDTO> responseDriver = restTemplate.postForEntity("http://localhost:8085/api/user/login",new LoginDTO("anja@gmail.com", "test"), TokenDTO.class);
        HttpHeaders headersDriver = new HttpHeaders();
        headersDriver.setContentType(MediaType.APPLICATION_JSON);
        headersDriver.set("X-Auth-Token",  responseDriver.getBody().getToken());
        driverEntity = new HttpEntity<>(headersDriver);


        ResponseEntity<TokenDTO> responseAdmin = restTemplate.postForEntity("http://localhost:8085/api/user/login",new LoginDTO("admin@gmail.com", "test"), TokenDTO.class);
        HttpHeaders headersAdmin = new HttpHeaders();
        headersAdmin.setContentType(MediaType.APPLICATION_JSON);
        headersAdmin.set("X-Auth-Token", responseAdmin.getBody().getToken());
        adminEntity = new HttpEntity<>(headersAdmin);
    }

// ==============================================
// CREATE RIDE
// ==============================================

    @Test
    public void createRide_Success() throws InterruptedException {
        RideDTO rideDTO = new RideDTO();


        Set<Path> locations = new HashSet<>();
        Location departure = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(null, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(null, departure, destination);
        locations.add(path);


        rideDTO.setBabyTransport(false);
        rideDTO.setPetTransport(false);
        rideDTO.setLocations(locations);


        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride",
                HttpMethod.POST,
                new HttpEntity<>(rideDTO, passengerEntity.getHeaders()),
                RideDTO.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        RideDTO returnedRide = responseEntity.getBody();
        assertNotNull(returnedRide);
    }

    @Test
    public void createRide_BadRequest(){
        RideDTO rideDTO = new RideDTO();


        rideDTO.setBabyTransport(false);
        rideDTO.setPetTransport(false);
        rideDTO.setLocations(new HashSet<Path>());


        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride",
                HttpMethod.POST,
                new HttpEntity<>(rideDTO, passengerEntity.getHeaders()),
                RideDTO.class);


        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        RideDTO returnedRide = responseEntity.getBody();
        assertNull(returnedRide);
    }

    @Test
    public void createRide_Forbidden(){
        RideDTO rideDTO = new RideDTO();
        Set<Path> locations = new HashSet<>();


        rideDTO.setBabyTransport(false);
        rideDTO.setPetTransport(false);
        rideDTO.setLocations(locations);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride",
                HttpMethod.POST,
                new HttpEntity<>(rideDTO, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void createRide_Unauthorized() {
        RideDTO rideDTO = new RideDTO();
        Set<Path> locations = new HashSet<>();
        Location departure = new Location(1, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Location destination = new Location(2, "Strumicka 6", (float) 20.45862, (float) 47.2589);
        Path path = new Path(1, departure, destination);
        locations.add(path);


        rideDTO.setBabyTransport(false);
        rideDTO.setPetTransport(false);
        rideDTO.setLocations(locations);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride",
                HttpMethod.POST,
                new HttpEntity<>(rideDTO),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }


// ==============================================
// RIDE DETAILS
// ==============================================

    @Test
    public void rideDetails_success(){
        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(getUrl("1"),
                HttpMethod.GET,
                passengerEntity,
                new ParameterizedTypeReference<RideDTO>() {
                });
        RideDTO rideDTO = responseEntity.getBody();
        assertNotNull(rideDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1,rideDTO.getId());
    }
    @Test
    public void rideDetails_NotFound(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("0"),
                HttpMethod.GET,
                passengerEntity,
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void rideDetails_BadRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("12345"),
                HttpMethod.GET,
                passengerEntity,
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void rideDetails_Unauthorized(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("1"),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }


// ===============================================
// GET ALL RIDES
// ===============================================


    @Test
    public void getAllRides_Success() {

        RideHistoryFilterDTO filterDTO = new RideHistoryFilterDTO();
        // set values for the filterDTO


        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("all"),
                HttpMethod.POST,
                new HttpEntity<>(filterDTO, adminEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAllRides_Forbidden(){
        RideHistoryFilterDTO filterDTO = new RideHistoryFilterDTO();
        // set values for the filterDTO


        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("all"),
                HttpMethod.POST,
                new HttpEntity<>(filterDTO, passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void getAllRides_BadRequest(){
        RideHistoryFilterDTO filterDTO = new RideHistoryFilterDTO();



        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("all"),
                HttpMethod.POST,
                new HttpEntity<>(adminEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getAllRides_Unauthorized(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("all"),
                HttpMethod.POST,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

// =======================================================
// ALL ACTIVE RIDES
// =======================================================

    @Test
    public void allActiveRides_Success(){
        List<Ride> rides = new ArrayList<>();

        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("active"),
                HttpMethod.GET,
                new HttpEntity<>(adminEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

// ==========================================================
// ACTIVE RIDE FOR DRIVER
// ==========================================================

    @Test
    public void activeRideForDriver_Success(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("driver/1/active"),
                HttpMethod.GET,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void activeRideForDriver_ActiveRideNotFound(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("driver/4/active"),
                HttpMethod.GET,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForDriver_BadRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("driver/10/active"),
                HttpMethod.GET,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForDriver_Forbidden(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("driver/1/active"),
                HttpMethod.GET,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForDriver_Unauthorized(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("driver/1/active"),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }


// ==========================================================
// ACTIVE RIDE FOR PASSENGER
// ==========================================================

    @Test
    public void activeRideForPassenger_Success(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("passenger/3/active"),
                HttpMethod.GET,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void activeRideForPassenger_ActiveRideNotFound(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("passenger/2/active"),
                HttpMethod.GET,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForPassenger_BadRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("passenger/10/active"),
                HttpMethod.GET,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForPassenger_Forbidden(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("passenger/2/active"),
                HttpMethod.GET,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void activeRideForPassenger_Unauthorized(){
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUrl("passenger/2/active"),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
// ==============================================
// START RIDE
// ==============================================
    @Test
    public void startRide_Forbidden(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/start",
                HttpMethod.PUT,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void startRide_Unathorised(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/start",
                HttpMethod.PUT,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void startRide_Success(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/6/start",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void startRide_BadRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/start",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void startRide_NotFound(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/12/start",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

// ==============================================
// END RIDE
// ==============================================
    @Test
    public void endRide_Forbidden(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/end",
                HttpMethod.PUT,
                new HttpEntity<>(passengerEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void endRide_Unathorised(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/end",
                HttpMethod.PUT,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void endRide_Success(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/2/end",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void endRide_BadRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/end",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void endRide_NotFound(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/12/end",
                HttpMethod.PUT,
                new HttpEntity<>(driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

// ==============================================
// DECLINE RIDE
// ==============================================
    @Test
    public void declineRide_Forbidden(){
        Rejection rejection = new Rejection();
        rejection.setReason("Reason of rejection");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/cancel",
                HttpMethod.PUT,
                new HttpEntity<>(rejection,adminEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void declineRide_Unathorised(){
        Rejection rejection = new Rejection();
        rejection.setReason("Reason of rejection");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/cancel",
                HttpMethod.PUT,
                new HttpEntity<>(rejection),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void declineRide_Success(){
        Rejection rejection = new Rejection();
        rejection.setReason("Reason of rejection");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/5/cancel",
                HttpMethod.PUT,
                new HttpEntity<>(rejection, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void declineRide_BadRequest(){
        Rejection rejection = new Rejection();
        rejection.setReason("Reason of rejection");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/cancel",
                HttpMethod.PUT,
                new HttpEntity<>(rejection, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void declineRide_NotFound(){
        Rejection rejection = new Rejection();
        rejection.setReason("Reason of rejection");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/12/cancel",
                HttpMethod.PUT,
                new HttpEntity<>(rejection, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    // ==============================================
// PANIC RIDE
// ==============================================
    @Test
    public void panicRide_Forbidden(){
        PanicPaginatedDTO panic = new PanicPaginatedDTO();
        panic.setReason("Reason of panic");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/panic",
                HttpMethod.PUT,
                new HttpEntity<>(panic, adminEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void panicRide_Unathorised(){
        PanicPaginatedDTO panic = new PanicPaginatedDTO();
        panic.setReason("Reason of panic");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/3/panic",
                HttpMethod.PUT,
                new HttpEntity<>(panic),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void panicRide_Success(){
        PanicPaginatedDTO panic = new PanicPaginatedDTO();
        panic.setReason("Reason of panic");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/4/panic",
                HttpMethod.PUT,
                new HttpEntity<>(panic, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void panicRide_NotFound(){
        PanicPaginatedDTO panic = new PanicPaginatedDTO();
        panic.setReason("Reason of panic");

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8085/api/ride/12/panic",
                HttpMethod.PUT,
                new HttpEntity<>(panic, driverEntity.getHeaders()),
                new ParameterizedTypeReference<String>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }










}
