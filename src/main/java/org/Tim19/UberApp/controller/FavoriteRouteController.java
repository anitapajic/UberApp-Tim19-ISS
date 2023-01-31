package org.Tim19.UberApp.controller;

import org.Tim19.UberApp.dto.FavoriteRouteDTO;
import org.Tim19.UberApp.dto.PaginatedData.CreateFavoriteRouteBodyPaginatedDTO;
import org.Tim19.UberApp.model.FavoriteRoute;
import org.Tim19.UberApp.model.Passenger;
import org.Tim19.UberApp.service.FavoriteRouteService;
import org.Tim19.UberApp.service.PassengerService;
import org.Tim19.UberApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping(value="/api/ride")
@CrossOrigin(value="*")
public class FavoriteRouteController {
    @Autowired
    private FavoriteRouteService favoriteRouteService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //CREATING A FAVORITE ROUTE  /api/ride/favorites
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN')")
    @PostMapping(consumes = "application/json",
                value="/favorites")
    public ResponseEntity createFavoriteRoute(@RequestBody CreateFavoriteRouteBodyPaginatedDTO favRouteDTO) {

        System.out.println(favRouteDTO);
        if(favRouteDTO.getBabyTransport()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        FavoriteRoute favoriteRoute = new FavoriteRoute();
        favoriteRoute.setFavoriteName(favRouteDTO.getFavoriteName());
        favoriteRoute.setLocations(favRouteDTO.getLocations());
        favoriteRoute.setVehicleType(favRouteDTO.getVehicleType());
        favoriteRoute.setBabyTransport(favRouteDTO.getBabyTransport());
        favoriteRoute.setPetTransport(favRouteDTO.getPetTransport());
        favoriteRoute.setScheduledTime(LocalDateTime.now());
        Passenger currentPassenger = new Passenger();
        for (Passenger p: favRouteDTO.getPassengers()) {
            Passenger p2 = (Passenger) userService.findOneById(p.getId());
            favoriteRoute.addPassenger(p2);
            p2.addFavourite(favoriteRoute);
            currentPassenger = p2;
        }

        Set<FavoriteRoute> allRoutes = favoriteRouteService.findAllByPassengerUsername(currentPassenger.getUsername());
        if(allRoutes.size()>10){
            return new ResponseEntity<>(
                    "Number of favorite rides cannot exceed 10!", HttpStatus.BAD_REQUEST);
        }

        favoriteRouteService.save(favoriteRoute);
        this.simpMessagingTemplate.convertAndSend("/map-updates/new-favorite-route", favoriteRoute);
        return new ResponseEntity<>(new FavoriteRouteDTO(favoriteRoute), HttpStatus.CREATED);
    }

    //FAVORITE LOCATIONS  /api/ride/favorites{passengerId}
    @GetMapping(value="/favorites/{passengerId}")
    @PreAuthorize("hasAnyAuthority('PASSENGER', 'ADMIN')")
    public ResponseEntity getFavoriteRoutes(@PathVariable Integer passengerId) {

        try{
            Passenger passenger = passengerService.findOne(passengerId);
            System.out.println(passenger.getFavourite());
            Set<FavoriteRoute> routes = passenger.getFavourite();


            return new ResponseEntity<>(routes,HttpStatus.OK);
        }
        catch (NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETING PASSENGERS FAVORITE ROUTES /api/ride/favorites/{id}
    @PreAuthorize("hasAnyAuthority('PASSENGER')")
    @DeleteMapping(value = "/favorites/{id}")
    public ResponseEntity deleteFavoriteRoute(@PathVariable Integer id) {

        FavoriteRoute favoriteRoute = favoriteRouteService.findOne(id);

        if (favoriteRoute != null) {
            favoriteRouteService.remove(id);
            this.simpMessagingTemplate.convertAndSend("/map-updates/delete-favorite-route", favoriteRoute);
            return new ResponseEntity<>("Successful deletion of favorite location!", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Favorite location does not exist!", HttpStatus.NOT_FOUND);
        }
    }
}
