package com.sql.uri2737;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sql.uri2737.dto.LawyerMinDTO;
import com.sql.uri2737.projections.LawyerMinProjection;
import com.sql.uri2737.repositories.LawyerRepository;

@SpringBootApplication
public class Uri2737Application implements CommandLineRunner {

	@Autowired
	private LawyerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2737Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<LawyerMinProjection> list = repository.searchSQL();
		List<LawyerMinDTO> listDto = list.stream().map(lawyer -> new LawyerMinDTO(lawyer)).collect(Collectors.toList());

		System.out.println("\n******  ROOT SQL RESULT  ******** \n");

		for (LawyerMinDTO lawyer : listDto) {
			System.out.println(lawyer);
		}

	}
}
