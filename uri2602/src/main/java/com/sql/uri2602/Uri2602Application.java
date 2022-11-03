package com.sql.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sql.uri2602.dto.CustomerMinDTO;
import com.sql.uri2602.projections.CustomerMinProjection;
import com.sql.uri2602.repositories.CustomersRepositories;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{
	
	@Autowired
	private CustomersRepositories customersRepositories;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = customersRepositories.search1("rs");
		List<CustomerMinDTO> result1 = list.stream().map(result -> new CustomerMinDTO(result))
				.collect(Collectors.toList());

		System.out.println("\n ***Result SQL SOURCE****\n");
		
		for(CustomerMinDTO obj : result1) {
			System.out.println(obj);
		}
		
		System.out.println("\n\n");
		
		List<CustomerMinDTO> result2 = customersRepositories.search2("rs");
		System.out.println("\n ****RESULT JPQL*****\n ");
		for(CustomerMinDTO obj : result2) {
			System.out.println(obj);
		}
		
	}
}











