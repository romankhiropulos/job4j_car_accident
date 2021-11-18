package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

public interface AuthorityDataJPA extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
