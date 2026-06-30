package com.ifsuldeminas.edu.br.multas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ifsuldeminas.edu.br.multas.model.entities.Police;
import com.ifsuldeminas.edu.br.multas.model.repositories.PoliceRepository;

import jakarta.validation.Valid;

@Controller
public class PoliceController {

    @Autowired
    private PoliceRepository policeRepository;

    @GetMapping("/police")
    public String listPolice(Model model) {

        List<Police> police = policeRepository.findAll();
        model.addAttribute("police", police);

        return "police.html";
    }

    @GetMapping("/police/form")
    public String policeForm(@ModelAttribute("police") Police police) {
        return "police_form";
    }

    @PostMapping("/police/save")
    public String policeSave(@ModelAttribute("police") @Valid Police police,
                             BindingResult errors) {

        if (errors.hasErrors())
            return "police_form";

        policeRepository.save(police);

        return "redirect:/police";
    }

    @GetMapping("/police/{id}")
    public String policeUpdate(@PathVariable Integer id, Model model) {

        Optional<Police> police = policeRepository.findById(id);

        model.addAttribute("police", police.orElse(null));

        return "police_form";
    }

    @GetMapping("/police/delete/{id}")
    public String policeDelete(@PathVariable Integer id) {

        policeRepository.findById(id).ifPresent(policeRepository::delete);

        return "redirect:/police";
    }
}