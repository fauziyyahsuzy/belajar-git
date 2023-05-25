package belajar.ProductOrder.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import belajar.ProductOrder.Dto.OrderDetailsDto;
import belajar.ProductOrder.Dto.OrdersDto;
import belajar.ProductOrder.exception.ResourceNotFoundException;
import belajar.ProductOrder.model.Orders;
import belajar.ProductOrder.model.OrderDetails;
import belajar.ProductOrder.repository.OrderDetailsRepository;
import belajar.ProductOrder.repository.OrdersRepository;

@RestController
@RequestMapping("/orderproduct")
public class OrderProductController {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
    private OrdersRepository ordersRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("/create")
	public OrdersDto createOrder(@Valid @RequestBody OrdersDto ordersDto) {
	    Orders orders = modelMapper.map(ordersDto, Orders.class);
	    Orders savedOrders = ordersRepository.save(orders);
	    
	    List<OrderDetailsDto> orderDetailsDtoList = ordersDto.getOrderDetailsList();
	    for (OrderDetailsDto orderDetailsDto : orderDetailsDtoList) {
	        OrderDetails orderDetails = modelMapper.map(orderDetailsDto, OrderDetails.class);
	        /**orderDetails.setOrders(savedOrders);
	        orderDetails.setOrderDetailsId(null); 
	        orderDetailsRepository.save(orderDetails);**/
	        
	        
	        Orders orders2 = ordersRepository.findById(orderDetailsDto.getOrders().getOrdersId())
	        		.orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + orderDetailsDto.getOrders().getOrdersId()));
	       
	        orderDetails.setOrders(orders2);
	        orderDetailsRepository.save(orderDetails);
	    }


	    OrdersDto savedOrdersDto = modelMapper.map(savedOrders, OrdersDto.class);
	    return savedOrdersDto;
	}
	
}
