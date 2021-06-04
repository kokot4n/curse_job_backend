package ru.sysout.sec4.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Optional;

@Entity
@Table(name = "notes")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "my_user_login", nullable = false)
    private MyUser myuser;

}
