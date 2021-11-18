package ru.job4j.accident.repository.springdata;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.*;
import ru.job4j.accident.repository.Storage;

import java.util.Collection;

@Repository
public class AccidentSpringData implements Storage {

    private final AccidentDataJPA accidentDataJPA;

    private final AccidentTypeDataJPA accidentTypeDataJPA;

    private final RuleDataJPA ruleDataJPA;

    private final AuthorityDataJPA authorityDataJPA;

    private final UserDataJPA userDataJPA;

    public AccidentSpringData(AccidentDataJPA accidentDataJPA,
                              AccidentTypeDataJPA accidentTypeDataJPA,
                              RuleDataJPA ruleDataJPA,
                              AuthorityDataJPA authorityDataJPA,
                              UserDataJPA userDataJPA) {
        this.accidentDataJPA = accidentDataJPA;
        this.accidentTypeDataJPA = accidentTypeDataJPA;
        this.ruleDataJPA = ruleDataJPA;
        this.authorityDataJPA = authorityDataJPA;
        this.userDataJPA = userDataJPA;
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

    @Override
    public User saveUser(User user) {
        return userDataJPA.save(user);
    }

    @Override
    public Authority findByAuthority(String authority) {
        return authorityDataJPA.findByAuthority(authority);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDataJPA.findByUsername(username);
    }
}
