package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public interface Storage {

    Collection<Accident> getAllAccidents();

    Collection<AccidentType> getAllAccidentTypes();

    void create(Accident accident);

    Accident getAccidentById(int id);

    AccidentType getAccidentTypeById(int id);
}
