package com.example.webapp;

import com.example.webapp.model.Contact;
import com.example.webapp.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(ContactRepository contactRepository){
		return args -> {
			//contactRepository.save(new Contact(5L,"Abdel","Abdelila",466));
			contactRepository.save(new Contact(null,"fatou","ndiaye",4559));
			contactRepository.findAll().forEach(c->{
				System.out.println(c.getNom());
			});
		};
	}}


