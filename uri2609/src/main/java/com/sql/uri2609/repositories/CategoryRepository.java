package com.sql.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sql.uri2609.dto.CategorySumDTO;
import com.sql.uri2609.entities.Category;
import com.sql.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(nativeQuery = true, value = "select categories.name, sum(products.amount) "
			+ "from categories "
			+ "inner join products on products.id_categories = categories.id "
			+ "group by categories.name")
	List<CategorySumProjection> searchSQL();
	
	
	@Query("select new com.sql.uri2609.dto.CategorySumDTO(p.category.name, sum(p.amount)) "
			+ "from Product p "
			+ "group by p.category.name")
	List<CategorySumDTO> searchJPQL();

}
