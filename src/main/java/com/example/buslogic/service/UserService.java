package com.example.buslogic.service;

import com.example.buslogic.model.Role;
import com.example.buslogic.model.User;
import com.example.buslogic.service.repository.RoleRepository;
import com.example.buslogic.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public String register(User user){

        User check = userRepository.findByUsername(user.getUsername());

        if(check != null) return "Такой пользватель уже существует!";

        Role roleUser = roleRepository.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return "Пользователь #" + user.getUsername()+ " успешно зарегистрирован!";
    }

    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
//        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User findByUsernameAndPassword(String username, String password){
        User result = userRepository.findByUsernameAndPassword(username, password);
        log.info("IN findByUsernameAndPassword - user: {} found by username: {}", result, username);
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
