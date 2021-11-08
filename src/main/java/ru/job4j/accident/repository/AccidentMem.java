package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.entity.Accident;

import java.util.*;

@Repository
public class AccidentMem implements Storage {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public Collection<Accident> getAllAccidents() {
        fillTestAccidents();
        return new ArrayList<>(accidents.values());
    }

    private void fillTestAccidents() {
        accidents.clear();
        Accident accident1 = new Accident(1, "Accident1", "Big mistake1", "street 1");
        Accident accident2 = new Accident(2, "Accident2", "Big mistake2", "street 2");
        Accident accident3 = new Accident(3, "Accident3", "Big mistake3", "street 3");
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }
}
