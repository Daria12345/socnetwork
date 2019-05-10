package com.example.sweater.Services;


import com.example.sweater.Repositories.RoleRepository;
import com.example.sweater.domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;

public class RolesService {
    @Autowired
    RoleRepository roleRepository;


    public Roles findRolesbyUsername(String username)
    {
      Roles roles = roleRepository.findByusername(username);
      return roles;
    }
}
