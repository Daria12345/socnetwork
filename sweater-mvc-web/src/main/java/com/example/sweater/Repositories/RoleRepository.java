package com.example.sweater.Repositories;

import com.example.sweater.domain.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Integer> {
    Roles findByusername(String username);
}
