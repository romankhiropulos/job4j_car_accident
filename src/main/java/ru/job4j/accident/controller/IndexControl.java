package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.entity.Accident;
import ru.job4j.accident.repository.Storage;

import java.util.List;

@Controller
public class IndexControl {

    @Autowired
    Storage storage;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents  = (List<Accident>) storage.getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
