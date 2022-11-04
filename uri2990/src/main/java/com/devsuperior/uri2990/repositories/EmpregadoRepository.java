package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {
	
	
	@Query(nativeQuery = true, value = "select empregados.cpf, empregados.enome, departamentos.dnome "
			+ "from empregados "
			+ "inner join departamentos on empregados.dnumero = departamentos.dnumero "
			+ "where empregados.cpf not in ( "
			+ "	select empregados.cpf "
			+ "	from empregados "
			+ "	inner join trabalha on trabalha.cpf_emp = empregados.cpf "
			+ ") "
			+ "order by empregados.cpf")
	List<EmpregadoDeptProjection> searchSQL();
	
	
	@Query( "select new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(emp.cpf, emp.enome, emp.departamento.dnome) "
			+ "from Empregado emp "
			+ "where emp.cpf not in ( "
			+ "	select emp.cpf "
			+ "	from Empregado emp"
			+ "	inner join emp.projetosOndeTrabalha "
			+ ") "
			+ "order by emp.cpf")
	List<EmpregadoDeptDTO> searchJPQL();

}
