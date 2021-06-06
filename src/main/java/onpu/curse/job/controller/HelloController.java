package onpu.curse.job.controller;


import onpu.curse.job.model.MyUser;
import onpu.curse.job.repository.MyUserRepository;
import onpu.curse.job.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import onpu.curse.job.model.Note;

@RestController
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
        return ResponseEntity.ok()
                .body(noteDao.findByMyuserOrderByDate(userDao.findByLogin(currentPrincipalName)));
        //return ResponseEntity.ok().body(noteDao.findAll());
    }

    @PostMapping("user/note")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        note.setMyuser(userDao.findByLogin(currentPrincipalName));
        //note.setMyuser(userDao.findByLogin("admin"));
        noteDao.save(note);
        return ResponseEntity.ok()
                .body(note);
    }

    @RequestMapping(value="user/note", method = RequestMethod.OPTIONS)
    ResponseEntity<?> singularOptions()
    {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS)
                .build();
    }

    @PostMapping("/user")
    public MyUser createUser(@RequestBody MyUser myUser){
        if(userDao.findByLogin(myUser.getLogin()) == null) {
            myUser.setRoles("USER");
            userDao.save(myUser);
        }
        return myUser;
    }
}
