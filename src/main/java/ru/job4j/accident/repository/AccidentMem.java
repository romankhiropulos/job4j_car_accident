package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;

@Repository
public class AccidentMem implements Storage {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentMem() {
        fillTestAccidents();
        fillTestAccidentTypes();
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    @Override
    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    private void fillTestAccidents() {
        Accident accident1 = new Accident(1, "Accident1", "Big mistake1", "street 1");
        Accident accident2 = new Accident(2, "Accident2", "Big mistake2", "street 2");
        Accident accident3 = new Accident(3, "Accident3", "Big mistake3", "street 3");
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }

    private void fillTestAccidentTypes() {
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
    }
}
