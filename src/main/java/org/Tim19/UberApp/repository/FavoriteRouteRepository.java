package org.Tim19.UberApp.repository;

import org.Tim19.UberApp.model.FavoriteRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FavoriteRouteRepository extends JpaRepository<FavoriteRoute, Integer> {
    public FavoriteRoute findOneById(Integer id);
    public Page<FavoriteRoute> findAll(Pageable pageable);
    public FavoriteRoute findOneFavoriteRouteById(Integer id);
    public Page<FavoriteRoute> findAllByPassengersId(Integer id, Pageable pageable);
    public Set<FavoriteRoute> findAllByPassengersId(Integer id);
    public Set<FavoriteRoute> findAllByPassengersUsername(String username);
}
