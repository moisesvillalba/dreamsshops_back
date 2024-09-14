package com.dailycodework.dreamsshops.repository;

import com.dailycodework.dreamsshops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Additional methods for specific product retrieval
    Product getProductById(Long id);
    // Lo anterior ya hice.
/*    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategory(String categoryName);
    List<Product> getProductByCategoryAndBrand(String categoryName, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    // Method for counting products by brand and name
    @Query("SELECT COUNT(p) FROM Product p WHERE p.brand = :brand AND p.name = :name")
    Long countProductByBrandAndName(@Param("brand") String brand, @Param("name") String name);
    Long countByBrandAndName(String brand, String name);*/
}
