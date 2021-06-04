package ru.sysout.sec4.controller;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sysout.sec4.model.MyUser;
import ru.sysout.sec4.model.Note;
import ru.sysout.sec4.repository.MyUserRepository;
import ru.sysout.sec4.repository.NoteRepository;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloController {

    @Autowired
    private MyUserRepository userDao;

    @Autowired
    private NoteRepository noteDao;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/user")
    public MyUser user(Authentication authentication) {
        System.out.println((UserDetails)authentication.getPrincipal());
        return userDao.findByLogin(((UserDetails) authentication.getPrincipal()).getUsername());
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/user/note")
    public ResponseEntity notes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        //return noteDao.findByMyuser(userDao.findByLogin(currentPrincipalName));
        return ResponseEntity.ok()
                .body(noteDao.findAll());
    }

    @PostMapping("user/note")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        //note.setMyuser(userDao.findByLogin(currentPrincipalName));
        note.setMyuser(userDao.findByLogin("admin"));
        noteDao.save(note);
        return ResponseEntity.ok()
                .body(note);
    }

    @PostMapping("/user")
    public MyUser createUser(@RequestBody MyUser myUser){
        userDao.save(myUser);
        return myUser;
    }
}
