package ru.sysout.sec4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.sec4.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
