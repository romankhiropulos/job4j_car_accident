package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
public class IndexControl {

    @Autowired
    private AccidentService service;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents  = (List<Accident>) service.getAllAccidents();
        model.addAttribute("accidents", accidents);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
