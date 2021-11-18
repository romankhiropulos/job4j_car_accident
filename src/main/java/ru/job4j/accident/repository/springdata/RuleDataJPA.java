package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleDataJPA extends CrudRepository<Rule, Integer> {
}
