package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface AccidentTypeDataJPA extends CrudRepository<AccidentType, Integer>  {
}
