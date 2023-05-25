package belajar.ProductOrder.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailsKey implements Serializable{

	@Column(name = "orders_id")
	Long ordersId;

    @Column(name = "product_id")
    Long productId;

	public OrderDetailsKey(Long ordersId, Long productId) {
		super();
		this.ordersId = ordersId;
		this.productId = productId;
	}

	public OrderDetailsKey() {
		super();
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ordersId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsKey other = (OrderDetailsKey) obj;
		return Objects.equals(ordersId, other.ordersId) && Objects.equals(productId, other.productId);
	}
    
    
	
}

