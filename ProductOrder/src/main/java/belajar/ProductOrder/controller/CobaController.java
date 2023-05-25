package belajar.ProductOrder.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import belajar.ProductOrder.repository.CategoryRepository;
import belajar.ProductOrder.Dto.ProductDto;
import belajar.ProductOrder.repository.CategoryRepository;
import belajar.ProductOrder.repository.ProductRepository;

import belajar.ProductOrder.repository.*;

@RestController
@RequestMapping("/coba")
public class CobaController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	
}
