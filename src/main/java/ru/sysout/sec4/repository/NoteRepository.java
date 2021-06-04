package ru.sysout.sec4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.sec4.model.MyUser;
import ru.sysout.sec4.model.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByMyuser(MyUser myUser);
}
