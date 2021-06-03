package ru.sysout.sec4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String text;
}
