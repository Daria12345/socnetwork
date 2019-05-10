package com.example.sweater.Repositories;

import com.example.sweater.domain.Event;
import com.example.sweater.domain.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuestRepository extends CrudRepository<Guest, Integer> {
    List<Guest> findByevent(Event event);
}

