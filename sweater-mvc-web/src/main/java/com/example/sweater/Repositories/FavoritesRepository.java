package com.example.sweater.Repositories;

import com.example.sweater.domain.Favorites;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoritesRepository extends CrudRepository<Favorites, Integer> {
    List<Favorites> findByUser(String user);
    Favorites findByUserAndAndEvent(String user, String event);
}
