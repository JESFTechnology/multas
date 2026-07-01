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

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Car;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CarRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.UserRepository;
import jakarta.validation.Valid;

@Controller
public class CarController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public String listCar(Model model) {

        List<Car> car = carRepository.findAll();
        model.addAttribute("carros", car);

        return "cars.html";
    }

    @GetMapping("/cars/form")
    public String carForm(@ModelAttribute("car") Car car,
            Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "car_form";
    }

    @PostMapping("/cars/save")
    public String carSave(@ModelAttribute("car") @Valid Car car,
            BindingResult errors,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            return "car_form";
        }

        carRepository.save(car);

        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}")
    public String carUpdate(@PathVariable Integer id,
            Model model) {

        Optional<Car> car = carRepository.findById(id);

        model.addAttribute("car", car.orElse(null));
        model.addAttribute("users", userRepository.findAll());

        return "car_form";
    }

    @GetMapping("/cars/delete/{id}")
    public String carDelete(@PathVariable Integer id) {

        carRepository.findById(id).ifPresent(carRepository::delete);

        return "redirect:/cars";
    }
}
