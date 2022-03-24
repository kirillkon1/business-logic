package com.example.buslogic.controller;

import com.example.buslogic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "test")
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping()
    public String getTest() {

        UserDetails details = userDetailsService.loadUserByUsername("admin1");
        if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            System.out.println(details.getAuthorities());
        }

        return "test";
    }
}
