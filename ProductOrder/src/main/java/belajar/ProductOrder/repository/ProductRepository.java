package belajar.ProductOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import belajar.ProductOrder.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
