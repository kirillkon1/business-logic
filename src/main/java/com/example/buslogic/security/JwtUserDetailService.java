package com.example.buslogic.security;

import com.example.buslogic.model.User;
import com.example.buslogic.security.jwt.JwtUser;
import com.example.buslogic.security.jwt.JwtUserFactory;
import com.example.buslogic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User "+ username + " was not found");

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user username: {} has been created", username);

        return jwtUser;
    }


}
