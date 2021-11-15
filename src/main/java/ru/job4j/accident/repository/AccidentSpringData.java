package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class AccidentSpringData implements Storage {

    private final AccidentDataJPA accidentDataJPA;

    private final AccidentTypeDataJPA accidentTypeDataJPA;

    private final RuleDataJPA ruleDataJPA;

    public AccidentSpringData(AccidentDataJPA accidentDataJPA,
                              AccidentTypeDataJPA accidentTypeDataJPA,
                              RuleDataJPA ruleDataJPA) {
        this.accidentDataJPA = accidentDataJPA;
        this.accidentTypeDataJPA = accidentTypeDataJPA;
        this.ruleDataJPA = ruleDataJPA;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return (Collection<Accident>) accidentDataJPA.findAll();
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return (Collection<AccidentType>) accidentTypeDataJPA.findAll();
    }

    @Override
    public Collection<Rule> getAllRules() {
        return (Collection<Rule>) ruleDataJPA.findAll();
    }

    @Override
    public Accident create(Accident accident) {
        return accidentDataJPA.save(accident);
    }

    @Override
    public Accident getAccidentById(int id) {
        return accidentDataJPA.findById(id).isPresent() ? accidentDataJPA.findById(id).get() : null;
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return accidentTypeDataJPA.findById(id).isPresent() ? accidentTypeDataJPA.findById(id).get() : null;
    }
}
