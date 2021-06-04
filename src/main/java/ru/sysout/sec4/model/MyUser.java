package ru.sysout.sec4.model;

import lombok.Data;
import ru.sysout.sec4.model.Note;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "myuser")
@Data
public class MyUser {

    @Id
    private String login;
    private String password;
    private String roles;
    @OneToMany(
            cascade={CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval=true
    )
    private List<Note> notes = new ArrayList<>();
}
