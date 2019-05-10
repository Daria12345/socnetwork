package com.example.sweater.Services;


import com.example.sweater.Repositories.RoleRepository;
import com.example.sweater.Repositories.UserRepository;
import com.example.sweater.domain.Roles;
import com.example.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("userService")
public class UserService {
     @Autowired
    private UserRepository userRepository;
     @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findUserByUsername(String username) {
        return userRepository.findByusername(username);
    }

    public boolean saveUser(User user) {
        User userFromDb = userRepository.findByusername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Roles userRole = new Roles();
        userRole.setUsername(user.getUsername());
        userRole.setRole("ROLE_USER");
        roleRepository.save(userRole);
        userRepository.save(user);
        return true;
    }
    public void deleteUser(User user)
    {
        User userFromDb = userRepository.findByusername(user.getUsername());
        Roles userRole = roleRepository.findByusername(user.getUsername());
        roleRepository.delete(userRole);
        userRepository.delete(userFromDb);
    }

    public void updateUser(String username, String password) {
       User user = userRepository.findByusername(username);
        if(!StringUtils.isEmpty(password))
        {
            user.setPassword(passwordEncoder.encode(password));
            user.setPassword2(password);
        }
        userRepository.save(user);
    }
}
