package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

public interface AccidentDataJPA extends CrudRepository<Accident, Integer> {
}
