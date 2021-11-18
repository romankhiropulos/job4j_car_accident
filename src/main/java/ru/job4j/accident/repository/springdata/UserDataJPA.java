package ru.job4j.accident.repository.springdata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

public interface UserDataJPA extends CrudRepository<User, Integer> {

    User findByUsername(String userName);
}
