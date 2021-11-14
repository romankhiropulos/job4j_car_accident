package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

/**
 *  This class can be @Repository instead of Accident Hibernate
 */
public class AccidentMem implements Storage {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        fillTestAccidents();
        fillTestAccidentTypes();
        fillTestAccidentRules();
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
    public Collection<Rule> getAllRules() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(new Date().getSeconds());
        }
        accidents.put(accident.getId(), accident);
        return accident;
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

    private void fillTestAccidentRules() {
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }
}
