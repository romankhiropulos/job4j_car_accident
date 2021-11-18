package ru.job4j.accident.repository;

import ru.job4j.accident.model.*;

import java.util.Collection;

public interface Storage {

    Collection<Accident> getAllAccidents();

    Collection<AccidentType> getAllAccidentTypes();

    Collection<Rule> getAllRules();

    Accident create(Accident accident);

    Accident getAccidentById(int id);

    AccidentType getAccidentTypeById(int id);

    User saveUser(User user);

    Authority findByAuthority(String authorityRoleName);

    User findUserByUserName(String username);
}
