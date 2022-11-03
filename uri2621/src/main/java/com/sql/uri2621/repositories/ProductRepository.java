package com.sql.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sql.uri2621.dto.ProductMinDTO;
import com.sql.uri2621.entities.Product;
import com.sql.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(nativeQuery = true, value = "select products.name "
			+ "from products "
			+ "inner join providers on products.id_providers = providers.id "
			+ "where products.amount BETWEEN :min and :max "
			+ "and providers.name like concat(:beginName, '%')")
	List<ProductMinProjection> searchSQL(Integer min, Integer max, String beginName);
	
	
	@Query( "select new com.sql.uri2621.dto.ProductMinDTO(product.name) "
			+ "from Product product "
			+ "where product.amount BETWEEN :min and :max "
			+ "and product.providers.name like concat(:beginName, '%')")
	List<ProductMinDTO> searchJPQL(Integer min, Integer max, String beginName);
	
	

}
