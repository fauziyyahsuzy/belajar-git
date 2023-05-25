package belajar.ProductOrder.controller;

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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import belajar.ProductOrder.Dto.CategoryDto;
import belajar.ProductOrder.exception.ResourceNotFoundException;
import belajar.ProductOrder.model.Category;
import belajar.ProductOrder.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	ModelMapper modelMapper = new ModelMapper();
    

    @GetMapping("/get")
    public List<CategoryDto> getAllCategory() {
        List<Category> category = categoryRepository.findAll();
        
        List <CategoryDto> listDTO = new ArrayList<CategoryDto>();

        for (Category entity : category) {
        	CategoryDto categoryDto = modelMapper.map(entity, CategoryDto.class);
          listDTO.add(categoryDto);
        }
        
        return listDTO;
    }
    
    @GetMapping("/get/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
        
        return modelMapper.map(category, CategoryDto.class);
    }
    
    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }
    

    @PutMapping("/update/{categoryId}")
    public CategoryDto updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDto categoryDto) {
        
    	 if (!categoryRepository.findById(categoryId).isPresent()) {
             throw new ResourceNotFoundException("Category not found with id: " + categoryId);
         }
    	
        Category category = modelMapper.map(categoryDto, Category.class);
		category.setCategoryId(categoryId);

        categoryRepository.save(category);
   
        return modelMapper.map(category, CategoryDto.class);
    }
	
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        categoryRepository.delete(existingCategory);
        return ResponseEntity.ok().build();
    }
}

