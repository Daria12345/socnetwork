package com.example.sweater.Repositories;

import com.example.sweater.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListOfOrgRepository extends CrudRepository<Category, Integer> {
    List<Category> findByKindOfCategory(String kindOfCategory);
}