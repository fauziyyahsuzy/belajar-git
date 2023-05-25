package belajar.ProductOrder.controller;

import java.util.ArrayList;
import java.util.List;

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

import belajar.ProductOrder.Dto.CategoryDto;
import belajar.ProductOrder.Dto.OrdersDto;
import belajar.ProductOrder.exception.ResourceNotFoundException;
import belajar.ProductOrder.model.Category;
import belajar.ProductOrder.model.Orders;
import belajar.ProductOrder.repository.OrdersRepository;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersRepository ordersRepository;
	
	ModelMapper modelMapper = new ModelMapper();
    

    @GetMapping("/get")
    public List<OrdersDto> getAllOrders() {
        
    	List<Orders> orders  = ordersRepository.findAll();
        List <OrdersDto> listDTO = new ArrayList<OrdersDto>();

        for (Orders entity : orders) {
        	OrdersDto ordersDto = modelMapper.map(entity, OrdersDto.class);
          listDTO.add(ordersDto);
        }
        
        return listDTO;
    }
    
    @GetMapping("/get/{ordersId}")
    public OrdersDto getOrdersById(@PathVariable Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId)
        .orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + ordersId));
        
        return modelMapper.map(orders, OrdersDto.class);
    }
    
    @PostMapping
    public OrdersDto createOrders(@Valid @RequestBody OrdersDto ordersDto) {
        Orders orders = modelMapper.map(ordersDto, Orders.class);
        Orders savedOrders = ordersRepository.save(orders);
        return modelMapper.map(savedOrders, OrdersDto.class);
    }
    

    @PutMapping("/update/{ordersId}")
    public OrdersDto updateOrders(@PathVariable Long ordersId, @Valid @RequestBody OrdersDto ordersDto) {
        
    	 if (!ordersRepository.findById(ordersId).isPresent()) {
             throw new ResourceNotFoundException("Orders not found with id: " + ordersId);
         }
    	
        Orders orders = modelMapper.map(ordersDto, Orders.class);
		orders.setOrdersId(ordersId);

        ordersRepository.save(orders);
   
        return modelMapper.map(orders, OrdersDto.class);
    }
	
    @DeleteMapping("/delete/{ordersId}")
    public ResponseEntity<?> deleteOrders(@PathVariable Long ordersId) {
        Orders existingOrders = ordersRepository.findById(ordersId)
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found with id: " + ordersId));

        ordersRepository.delete(existingOrders);
        return ResponseEntity.ok().build();
    }
	
}
