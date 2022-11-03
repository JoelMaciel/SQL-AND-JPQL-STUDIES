package com.sql.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sql.uri2621.dto.ProductMinDTO;
import com.sql.uri2621.projections.ProductMinProjection;
import com.sql.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProductMinProjection> products = repository.searchSQL(10, 20, "P");
		List<ProductMinDTO> productsDto = products.stream().
				map(product -> new ProductMinDTO(product)).collect(Collectors.toList());
		
		System.out.println("\n *******  ROOT SQL RESULT  ***********\n");
		
		for (ProductMinDTO product : productsDto) {
			System.out.println(product);
		}
		System.out.println("\n\n");
		
		List<ProductMinDTO> productDto = repository.searchJPQL(10, 20, "P");
		
		System.out.println("\n *******  ROOT JPQL RESULT  ***********\n");
		for(ProductMinDTO product : productDto) {
			System.err.println(product);
		}
		
	}
}









