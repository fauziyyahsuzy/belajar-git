package belajar.ProductOrder.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="order_details")
public class OrderDetails {
	
	@EmbeddedId
	OrderDetailsKey orderDetailsKey;
	
	@NotNull
    @ManyToOne
    @MapsId("orders_id")
    @JoinColumn(name = "orders_id")
    private Orders orders;
	
	@NotNull
    @ManyToOne
    @MapsId("produt_id")
    @JoinColumn(name = "product_id")
    private Product product;
	
	@NotNull
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="order_price")
	private BigDecimal orderPrice;

	
	public OrderDetails(OrderDetailsKey orderDetailsKey, @NotNull Orders orders, @NotNull Product product,
			@NotNull int quantity, BigDecimal orderPrice) {
		super();
		this.orderDetailsKey = orderDetailsKey;
		this.orders = orders;
		this.product = product;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
	}
	
	

	public OrderDetails() {
		super();
	}



	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public OrderDetailsKey getOrderDetailsId() {
		return orderDetailsKey;
	}

	public void setOrderDetailsId(OrderDetailsKey orderDetailsId) {
		this.orderDetailsKey = orderDetailsId;
	}

	public Orders getOrders() {
		return orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderDetailsKey, orderPrice, orders, product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		return Objects.equals(orderDetailsKey, other.orderDetailsKey) && Objects.equals(orderPrice, other.orderPrice)
				&& Objects.equals(orders, other.orders) && Objects.equals(product, other.product)
				&& quantity == other.quantity;
	}
	
	
	
}
