package ru.job4j.accident.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AccidentService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;

    private final AccidentService service;

    public RegControl(PasswordEncoder encoder, AccidentService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (service.getUserByUserName(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Данный логин уже используется");
            return "reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(service.getAuthorityByAuthorityRoleName("ROLE_USER"));
        service.createUser(user);
        return "redirect:/login";
    }
}
