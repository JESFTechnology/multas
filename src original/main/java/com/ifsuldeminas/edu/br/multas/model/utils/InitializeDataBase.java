package com.ifsuldeminas.edu.br.multas.model.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifsuldeminas.edu.br.multas.model.entities.Car;
import com.ifsuldeminas.edu.br.multas.model.entities.Penalty;
import com.ifsuldeminas.edu.br.multas.model.entities.Police;
import com.ifsuldeminas.edu.br.multas.model.repositories.CarRepository;
import com.ifsuldeminas.edu.br.multas.model.repositories.PenaltyRepository;
import com.ifsuldeminas.edu.br.multas.model.repositories.PoliceRepository;

@Component
public class InitializeDataBase implements CommandLineRunner {

    @Autowired
    private PoliceRepository policeRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Override
    public void run(String... args) throws Exception {

        // Cars
        Car car1 = new Car();
        car1.setLicensePlate("ABC1234");
        car1.setCpfOrCnpj("12345678901");
        car1.setChassis("9BWZZZ377VT004251");
        car1.setYear(2022);
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setLicensePlate("DEF5678");
        car2.setCpfOrCnpj("98765432100");
        car2.setChassis("9BWZZZ377VT004252");
        car2.setYear(2021);
        carRepository.save(car2);

        Car car3 = new Car();
        car3.setLicensePlate("GHI9012");
        car3.setCpfOrCnpj("11122233344");
        car3.setChassis("9BWZZZ377VT004253");
        car3.setYear(2020);
        carRepository.save(car3);

        carRepository.flush();

        // Police
        Police officer1 = new Police();
        officer1.setName("John Smith");
        officer1.setRank("Sergeant");
        officer1.setCpf("12345678901");
        officer1.setEmail("john@police.com");
        officer1.setPassword("123456");

        Police officer2 = new Police();
        officer2.setName("Mary Johnson");
        officer2.setRank("Captain");
        officer2.setCpf("23456789012");
        officer2.setEmail("mary@police.com");
        officer2.setPassword("123456");

        Police officer3 = new Police();
        officer3.setName("Michael Brown");
        officer3.setRank("Lieutenant");
        officer3.setCpf("34567890123");
        officer3.setEmail("michael@police.com");
        officer3.setPassword("123456");

        policeRepository.save(officer1);
        policeRepository.save(officer2);
        policeRepository.save(officer3);

        policeRepository.flush();

        // Penalties
        Penalty penalty1 = new Penalty();
        penalty1.setCar(car1);
        penalty1.setCost(293.47);
        penalty1.setLocation("Downtown");
        penalty1.setPoliceman(officer1);

        Penalty penalty2 = new Penalty();
        penalty2.setCar(car2);
        penalty2.setCost(130.16);
        penalty2.setLocation("Highway");
        penalty2.setPoliceman(officer2);

        Penalty penalty3 = new Penalty();
        penalty3.setCar(car3);
        penalty3.setCost(880.41);
        penalty3.setLocation("Central Avenue");
        penalty3.setPoliceman(officer3);

        penaltyRepository.save(penalty1);
        penaltyRepository.save(penalty2);
        penaltyRepository.save(penalty3);
    }
}