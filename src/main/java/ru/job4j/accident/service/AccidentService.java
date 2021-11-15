package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentSpringData;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    @Autowired
    private AccidentSpringData storage;

    public Collection<Accident> getAllAccidents() {
        return storage.getAllAccidents();
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return storage.getAllAccidentTypes();
    }

    public Collection<Rule> getAllRules() {
        return storage.getAllRules();
    }

    public void create(Accident accident, String[] rIds) {
        prepareAccidentForSave(accident, rIds);
        storage.create(accident);
    }

    public void update(Accident accident, String[] rIds) {
        prepareAccidentForSave(accident, rIds);
        storage.create(accident);
    }

    public Accident getAccidentById(int id) {
        return storage.getAccidentById(id);
    }

    private void prepareAccidentForSave(Accident accident, String[] rIds) {
        accident.setType(storage.getAccidentTypeById(accident.getType().getId()));
        Set<Rule> rules = storage.getAllRules()
                .stream()
                .filter(r -> Arrays.asList(rIds).contains(String.valueOf(r.getId())))
                .collect(Collectors.toSet());
        accident.setRules(rules);
    }
}
