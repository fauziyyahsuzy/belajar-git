package belajar.ProductOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import belajar.ProductOrder.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
