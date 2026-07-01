package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PenaltyRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PoliceRepository;
import jakarta.validation.Valid;

@Controller
public class PoliceController {

    @Autowired
    private PoliceRepository policeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private PenaltyRepository penaltyRepository;

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

        // Cadastro
        if (police.getId() == null) {

            police.setPassword(passwordEncoder.encode(police.getPassword()));

        } else {

            // Edição
            Police original = policeRepository.findById(police.getId()).orElseThrow();

            if (police.getPassword() == null || police.getPassword().isBlank()) {

                // Mantém a senha antiga
                police.setPassword(original.getPassword());

            } else {

                // Nova senha
                police.setPassword(passwordEncoder.encode(police.getPassword()));
            }
        }

        policeRepository.save(police);

        return "redirect:/police";
    }

    @GetMapping("/police/{id}")
    public String policeUpdate(@PathVariable Integer id, Model model) {

        Optional<Police> policeOpt = policeRepository.findById(id);

        if (policeOpt.isPresent()) {

            Police police = policeOpt.get();

            // Nunca envia a senha criptografada para o formulário
            police.setPassword("");

            model.addAttribute("police", police);
        }

        return "police_form";
    }

    @GetMapping("/police/delete/{id}")
    public String policeDelete(@PathVariable Integer id,
                               Model model) {

        Optional<Police> policeOpt = policeRepository.findById(id);

        if (policeOpt.isPresent()) {

            Police police = policeOpt.get();

            if (penaltyRepository.existsByPoliceman(police)) {

                model.addAttribute("erro",
                        "Este policial ainda possui multas registradas. Remova as multas para demitir ele.");

                model.addAttribute("police", policeRepository.findAll());

                return "police";
            }

            policeRepository.delete(police);
        }

        return "redirect:/police";
    }

}