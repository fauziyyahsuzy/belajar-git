package belajar.ProductOrder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import belajar.ProductOrder.Dto.OrderDetailsDto;
import belajar.ProductOrder.Dto.ProductDto;
import belajar.ProductOrder.exception.ResourceNotFoundException;
import belajar.ProductOrder.model.Category;
import belajar.ProductOrder.model.OrderDetails;
import belajar.ProductOrder.model.OrderDetailsKey;
import belajar.ProductOrder.model.Orders;
import belajar.ProductOrder.model.Product;
import belajar.ProductOrder.repository.OrderDetailsRepository;
import belajar.ProductOrder.repository.OrdersRepository;
import belajar.ProductOrder.repository.ProductRepository;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
	
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
    private OrdersRepository ordersRepository;
	
	
	ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("/get")
    public List<OrderDetailsDto> getAllOrderDetails() {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        
        List <OrderDetailsDto> listDTO = new ArrayList<OrderDetailsDto>();

        for (OrderDetails entity : orderDetails) {
        	OrderDetailsDto orderDetailsDto = modelMapper.map(entity, OrderDetailsDto.class);
          listDTO.add(orderDetailsDto);
        }
        
        return listDTO;
    }
	
	@GetMapping("get/{ordersId}/{productId}")
	    public OrderDetailsDto getOrderDetailsById(@PathVariable Long productId, @PathVariable Long ordersId) {
		 OrderDetailsKey orderDetailsKey = new OrderDetailsKey(ordersId, productId);  
		 OrderDetails orderDetails = orderDetailsRepository.findById(orderDetailsKey)
	        .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found with id: " + orderDetailsKey));
	        
	        return modelMapper.map(orderDetails, OrderDetailsDto.class);
	}
	
	@PostMapping
    public OrderDetailsDto createOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {
        
        OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
        
        Orders orders = ordersRepository.findById(orderDetailsDto.getOrders().getOrdersId())
        		.orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + orderDetailsDto.getOrders().getOrdersId()));
       
        orderDetails.setOrders(orders);

        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);

        return modelMapper.map(savedOrderDetails, OrderDetailsDto.class);
        
    }
	

	 @PutMapping("update/{ordersId}/{productId}")
	    public OrderDetailsDto updateOrderDetails(@PathVariable Long ordersId, @PathVariable Long productId, @Valid @RequestBody OrderDetailsDto orderDetailsDto) {
	        
		 OrderDetailsKey orderDetailsKey = new OrderDetailsKey(ordersId, productId);  
		 if (!orderDetailsRepository.findById(orderDetailsKey).isPresent()) {
	            throw new ResourceNotFoundException("OrderDetails not found with id: " + orderDetailsKey );
	     }
	        
	        OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
	        
	        OrderDetailsKey orderDetailsKeyNew = new OrderDetailsKey(orderDetails.getOrders().getOrdersId(), orderDetails.getProduct().getProductId());  
			orderDetails.setOrderDetailsId(orderDetailsKeyNew);

			orderDetailsRepository.save(orderDetails);
	   
	        return modelMapper.map(orderDetails, OrderDetailsDto.class);
	    }
	    
	    @DeleteMapping("/delete/{ordersId}/{productId}")
	    public ResponseEntity<?> deleteOrderDetails(@PathVariable Long ordersId, @PathVariable Long productId) {
	    	OrderDetailsKey orderDetailsKey = new OrderDetailsKey(ordersId, productId);
	        OrderDetails existingOrderDetails = orderDetailsRepository.findById(orderDetailsKey)
	                .orElseThrow(() -> new ResourceNotFoundException("OrderDetails not found with id: " + orderDetailsKey));

	        orderDetailsRepository.delete(existingOrderDetails);
	        return ResponseEntity.ok().build();
	    }

	
}
