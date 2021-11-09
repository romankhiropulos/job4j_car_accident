package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface Storage {

    Collection<Accident> getAllAccidents();

    void create(Accident accident);

    void update(Accident accident);

    Accident getAccidentById(int id);
}
