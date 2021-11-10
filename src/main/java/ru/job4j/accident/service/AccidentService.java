package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.Storage;

import java.util.Collection;

@Service
public class AccidentService {

    @Autowired
    private Storage storage;

    public Collection<Accident> getAllAccidents() {
        return storage.getAllAccidents();
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return storage.getAllAccidentTypes();
    }

    public void create(Accident accident) {
        storage.create(accident);
    }

    public void update(Accident accident) {
        storage.create(accident);
    }

    public Accident getAccidentById(int id) {
        return storage.getAccidentById(id);
    }
}
