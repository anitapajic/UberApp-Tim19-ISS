package org.Tim19.UberApp.service;

import org.Tim19.UberApp.model.FavoriteRoute;
import org.Tim19.UberApp.repository.FavoriteRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FavoriteRouteService {
    @Autowired
    private FavoriteRouteRepository favoriteRouteRepository;

    public Optional<FavoriteRoute> findOneById(Integer id){return favoriteRouteRepository.findById(id);}
    public Page<FavoriteRoute> findAll(Pageable page){return favoriteRouteRepository.findAll(page);}
    public FavoriteRoute save(FavoriteRoute favoriteRoute){return favoriteRouteRepository.save(favoriteRoute);}
    public List<FavoriteRoute> findAll(){return favoriteRouteRepository.findAll();}
    public void remove(Integer id){favoriteRouteRepository.deleteById(id);}
    public FavoriteRoute findOneFavoriteRouteById(Integer id){
        return favoriteRouteRepository.findOneFavoriteRouteById(id);
    }
    public Page<FavoriteRoute> findByPassengerId(Integer id, Pageable pageable){
        Page<FavoriteRoute> routes = favoriteRouteRepository.findAllByPassengersId(id, pageable);

        return routes;
    }
    public Set<FavoriteRoute> findAllByPassengerId(Integer id){
        Set<FavoriteRoute> routes = favoriteRouteRepository.findAllByPassengersId(id);

        return routes;
    }
    public Set<FavoriteRoute> findAllByPassengerUsername(String username){
        Set<FavoriteRoute> routes = favoriteRouteRepository.findAllByPassengersUsername(username);

        return routes;
    }
    public FavoriteRoute findOne(Integer id){return favoriteRouteRepository.findById(id).orElse(null);}
}
