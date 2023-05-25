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

import belajar.ProductOrder.Dto.ProductDto;
import belajar.ProductOrder.exception.ResourceNotFoundException;
import belajar.ProductOrder.model.Category;
import belajar.ProductOrder.model.Product;
import belajar.ProductOrder.repository.CategoryRepository;
import belajar.ProductOrder.repository.ProductRepository;
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	ModelMapper modelMapper = new ModelMapper();
    

    @GetMapping("/get")
    public List<ProductDto> getAllProduct() {
        List<Product> product = productRepository.findAll();
        
        List <ProductDto> listDTO = new ArrayList<ProductDto>();

        for (Product entity : product) {
        	ProductDto productDto = modelMapper.map(entity, ProductDto.class);
          listDTO.add(productDto);
        }
        
        return listDTO;
    }
    
    @GetMapping("/get/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        
        return modelMapper.map(product, ProductDto.class);
    }
	
    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        
        Product product = modelMapper.map(productDto, Product.class);

        Category category = categoryRepository.findById(productDto.getCategory().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + productDto.getCategory().getCategoryId()));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
        
    }
    

    @PutMapping("/update/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto productDto) {
        if (!productRepository.findById(productId).isPresent()) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        
        Product product = modelMapper.map(productDto, Product.class);
		product.setProductId(productId);

        productRepository.save(product);
   
        return modelMapper.map(product, ProductDto.class);
    }
    
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        productRepository.delete(existingProduct);
        return ResponseEntity.ok().build();
    }
}
