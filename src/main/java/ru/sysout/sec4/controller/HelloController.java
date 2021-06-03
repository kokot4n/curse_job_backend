package ru.sysout.sec4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sysout.sec4.model.MyUser;
import ru.sysout.sec4.repository.MyUserRepository;

import java.net.URI;

@RestController
public class HelloController {

    @Autowired
    private MyUserRepository dao;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user")
    public String user(Authentication authentication) {
        System.out.println((UserDetails)authentication.getPrincipal());
        return dao.findByLogin(((UserDetails) authentication.getPrincipal()).getUsername()).getPassword();
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/user/weather")
    public ResponseEntity weather(@RequestParam String lat, @RequestParam String lon){
        final RestTemplate restTemplate = new RestTemplate();
        String uri = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=hourly,minutely&appid=11f6c7832b5096ea219b19ec5d65f8b2";
        ResponseEntity responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity;
    }

}
