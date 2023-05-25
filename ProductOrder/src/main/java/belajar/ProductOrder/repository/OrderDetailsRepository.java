package belajar.ProductOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import belajar.ProductOrder.model.OrderDetails;
import belajar.ProductOrder.model.OrderDetailsKey;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsKey>{
	
}
