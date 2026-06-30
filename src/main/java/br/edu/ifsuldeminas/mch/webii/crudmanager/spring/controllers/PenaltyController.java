package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.controllers;

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

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Penalty;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CarRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PenaltyRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PoliceRepository;
import jakarta.validation.Valid;

@Controller
public class PenaltyController {

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PoliceRepository policeRepository;

    @GetMapping("/penalties")
    public String listPenalties(Model model) {

        List<Penalty> penalties = penaltyRepository.findAll();
        model.addAttribute("penalties", penalties);

        return "penalties.html";
    }

    @GetMapping("/penalties/form")
    public String penaltyForm(@ModelAttribute("penalty") Penalty penalty,
                              Model model) {

        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("police", policeRepository.findAll());

        return "penalty_form";
    }

    @PostMapping("/penalties/save")
    public String penaltySave(@ModelAttribute("penalty")
                              @Valid Penalty penalty,
                              BindingResult errors,
                              Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("cars", carRepository.findAll());
            model.addAttribute("police", policeRepository.findAll());
            return "penalty_form";
        }

        penaltyRepository.save(penalty);

        return "redirect:/penalties";
    }

    @GetMapping("/penalties/{id}")
    public String penaltyUpdate(@PathVariable("id") Integer id,
                                Model model) {

        Optional<Penalty> penaltyOpt = penaltyRepository.findById(id);

        Penalty penalty = null;
        if (penaltyOpt.isPresent())
            penalty = penaltyOpt.get();

        model.addAttribute("penalty", penalty);
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("police", policeRepository.findAll());

        return "penalty_form";
    }

    @GetMapping("/penalties/delete/{id}")
    public String penaltyDelete(@PathVariable("id") Integer id) {

        Optional<Penalty> penaltyOpt = penaltyRepository.findById(id);

        if (penaltyOpt.isPresent())
            penaltyRepository.delete(penaltyOpt.get());

        return "redirect:/penalties";
    }

}