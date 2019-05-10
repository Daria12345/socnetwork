package com.example.sweater.Repositories;


import com.example.sweater.domain.Event;

import com.example.sweater.domain.PostCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalRepository extends CrudRepository<PostCategories, Integer> {
    List<PostCategories> findByevent(Event event);
    List<PostCategories> findByNameOfCategoryAndEvent(String name, Event event);
}