package belajar.ProductOrder.Dto;


import java.math.BigDecimal;

public class ProductDto {
	
	private Long productId;
	private CategoryDto category;
	private String productName;
	private int productQuantity;
	private BigDecimal productPrice;
	
	public ProductDto(Long productId, CategoryDto category, String productName, int productQuantity, BigDecimal productPrice) {
		super();
		this.productId = productId;
		this.category = category;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
	}

	public ProductDto() {
		super();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
