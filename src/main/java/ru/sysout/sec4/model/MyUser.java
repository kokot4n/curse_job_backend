package ru.sysout.sec4.model;

import lombok.Data;
import ru.sysout.sec4.model.Note;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
@Data
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String login;
    private String password;
    private String roles;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Note> notes = new ArrayList<>();
}
