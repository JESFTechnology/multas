package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Car;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Penalty;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.AdrressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.CarRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PenaltyRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.PoliceRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories.UserRepository;

@Component
public class InitializeDataBase implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PoliceRepository policeRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PenaltyRepository penaltyRepository;

	@Autowired
	private AdrressRepository adrressRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		Address aEmerson = new Address();
		aEmerson.setPlace("Rua emerson");
		aEmerson.setNumber("10");
		aEmerson.setZipCode("130000");
		adrressRepository.save(aEmerson);

		Address aLuiza = new Address();
		aLuiza.setPlace("Rua luiza");
		aLuiza.setNumber("11");
		aLuiza.setZipCode("131000");
		adrressRepository.save(aLuiza);

		Address aAna = new Address();
		aAna.setPlace("Rua ana");
		aAna.setNumber("12");
		aAna.setZipCode("132000");
		adrressRepository.save(aAna);

		// Consolida a tupla no banco
		adrressRepository.flush();

		User emerson = new User();
		emerson.setName("Emerson Carvalho");
		emerson.setGender("M");
		emerson.setEmail("emerson@mail.com");
		emerson.setAddress(aEmerson);

		User luiza = new User();
		luiza.setName("Luiza Carvalho");
		luiza.setGender("F");
		luiza.setEmail("lu@mail.com");
		luiza.setAddress(aLuiza);

		User ana = new User();
		ana.setName("Ana Carvalho");
		ana.setGender("F");
		ana.setEmail("ana@mail.com");
		ana.setAddress(aAna);

		userRepository.save(emerson);
		userRepository.save(luiza);
		userRepository.save(ana);

		Police johann = new Police();
		johann.setName("Johann");
		johann.setCpf("13642344674");
		johann.setEmail("johannsacconi@gmail.com");
		johann.setPassword(passwordEncoder.encode("12345678"));
		johann.setRank("Coronel");

		policeRepository.save(johann);

		Car carroEmerson = new Car();
		carroEmerson.setChassis("8726348276443");
		carroEmerson.setLicensePlate("ABC1234");
		carroEmerson.setManufactureYear(2020);
		carroEmerson.setUser(emerson);

		carRepository.save(carroEmerson);

		Penalty multaEmerson = new Penalty();
		multaEmerson.setCar(carroEmerson);
		multaEmerson.setCost(2000.00);
		multaEmerson.setLocation("Alfenas - MG");
		multaEmerson.setPoliceman(johann);

		penaltyRepository.save(multaEmerson);

	}
}
