package com.sql.uri2609;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sql.uri2609.dto.CategorySumDTO;
import com.sql.uri2609.projections.CategorySumProjection;
import com.sql.uri2609.repositories.CategoryRepository;

@SpringBootApplication
public class Uri2609Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2609Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<CategorySumProjection> list = repository.searchSQL();
		List<CategorySumDTO> categoryDto = list.stream()
				.map(category -> new CategorySumDTO(category)).collect(Collectors.toList());
		
		System.out.println("\n******  ROOT SQL RESULT  ******** \n");
		
		for (CategorySumDTO category : categoryDto) {
			System.out.println(category);
		}
		
		System.out.println("\n\n");
		
		List<CategorySumDTO> categories = repository.searchJPQL();
		
		System.out.println("\n******  ROOT JPQL RESULT  ******** \n");
		
		for (CategorySumDTO category : categories) {
			System.out.println(category);
		}
		

	}
}








