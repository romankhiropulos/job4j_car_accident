package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> accidents  = new ArrayList<>(Arrays.asList("Авария", "Столкновение", "Обгон"));
        model.addAttribute("accidents", accidents);
        return "index";
    }
}
