package belajar.ProductOrder.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_quantity")
	private int productQuantity;
	
	@Column(name="product_price")
	private BigDecimal productPrice;
	
	public Product(@NotNull Long productId, @NotNull Category category, String productName, int productQuantity,
			BigDecimal productPrice) {
		super();
		this.productId = productId;
		this.category = category;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
	}

	public Product() {
		super();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
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
