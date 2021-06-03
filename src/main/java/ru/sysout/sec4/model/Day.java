package ru.sysout.sec4.model;

import lombok.Data;
import ru.sysout.sec4.model.Note;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "day")
@Data
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Note> notes = new ArrayList<>();
}
