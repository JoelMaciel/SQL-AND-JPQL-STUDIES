package com.sql.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sql.uri2602.dto.CustomerMinDTO;
import com.sql.uri2602.entities.Customer;
import com.sql.uri2602.projections.CustomerMinProjection;

public interface CustomersRepositories extends JpaRepository<Customer, Long>{
	
	@Query(nativeQuery = true, value = "select name"
			+ " from customers where upper(state) = upper(:state)")
	List<CustomerMinProjection> search1(String state);
	
	@Query("select new com.sql.uri2602.dto.CustomerMinDTO(obj.name)"
			+ " from Customer obj where upper(obj.state) = upper(:state)")
	List<CustomerMinDTO> search2(String state);

}
