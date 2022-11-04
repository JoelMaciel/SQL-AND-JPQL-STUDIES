package com.sql.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sql.uri2737.entities.Lawyer;
import com.sql.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
	
	@Query(nativeQuery = true, value = "(select name, customers_number AS customersNumber "
			+ "from lawyers "
			+ "where customers_number = ( "
			+ "select max(customers_number) "
			+ "from lawyers "
			+ "))"
			+ "union all "
			+ "(select name, customers_number "
			+ "from lawyers "
			+ "where customers_number  = ( "
			+ "select min(customers_number) "
			+ "from lawyers "
			+ ")) "
			+ "union all "
			+ "(select 'Average', round(avg(customers_number), 0) "
			+ " from lawyers)")
	List<LawyerMinProjection> searchSQL();

}
