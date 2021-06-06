package onpu.curse.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import onpu.curse.job.model.MyUser;
import onpu.curse.job.model.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByMyuserOrderByDate(MyUser myUser);
}
