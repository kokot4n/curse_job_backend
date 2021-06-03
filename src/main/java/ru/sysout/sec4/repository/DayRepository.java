package ru.sysout.sec4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sysout.sec4.model.Day;

public interface DayRepository extends JpaRepository<Day, Long> {
}
