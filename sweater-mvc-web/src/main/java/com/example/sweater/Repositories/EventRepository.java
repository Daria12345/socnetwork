package com.example.sweater.Repositories;

import com.example.sweater.domain.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

       List<Event> findByplace(String place);
       Event findByname(String name);
       Event findByidevent(Long id_event);
        }