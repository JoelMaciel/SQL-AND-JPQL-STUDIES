package com.devsuperior.uri2990;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import com.devsuperior.uri2990.repositories.EmpregadoRepository;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<EmpregadoDeptProjection> list = repository.searchSQL();
		List<EmpregadoDeptDTO> empregadoDto = list.stream()
				.map(empregado -> new EmpregadoDeptDTO(empregado)).collect(Collectors.toList());
		
		System.out.println("\n******  ROOT SQL RESULT NOT IN  ******** \n");

		for (EmpregadoDeptDTO empregado : empregadoDto) {
			System.out.println(empregado);
		}
		
		System.out.println("\n******  JPQL RESULT  ******** \n");
		List<EmpregadoDeptDTO> listJpql = repository.searchJPQL();
		for (EmpregadoDeptDTO empregado : listJpql) {
			System.out.println(empregado);
		}
		
		System.out.println("\n\n");
	}
}






