package ru.job4j.accident.repository;

import ru.job4j.accident.entity.Accident;

import java.util.Collection;

public interface Storage {

    Collection<Accident> getAllAccidents();

}
