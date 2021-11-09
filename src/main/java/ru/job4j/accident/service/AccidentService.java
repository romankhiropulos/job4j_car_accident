package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Storage;

import java.util.Collection;
import java.util.Date;

@Service
public class AccidentService {

    @Autowired
    private Storage storage;

    public Collection<Accident> getAllAccidents() {
        return storage.getAllAccidents();
    }

    public void create(Accident accident) {
        accident.setId(new Date().getSeconds());
        storage.create(accident);
    }

    public void update(Accident accident) {
        storage.update(accident);
    }

}
