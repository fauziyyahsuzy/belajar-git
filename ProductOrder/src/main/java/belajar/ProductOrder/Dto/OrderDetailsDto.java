package belajar.ProductOrder.Dto;


import java.math.BigDecimal;

import belajar.ProductOrder.model.OrderDetailsKey;


public class OrderDetailsDto {
	
	OrderDetailsKey orderDetailsKey;
    private OrdersDto orders;
    private ProductDto product;
	private int quantity;
	private BigDecimal orderPrice;
	
	public OrderDetailsDto(OrderDetailsKey orderDetailsKey, OrdersDto orders, ProductDto product, int quantity,
			BigDecimal orderPrice) {
		super();
		this.orderDetailsKey = orderDetailsKey;
		this.orders = orders;
		this.product = product;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
	}

	public OrderDetailsDto() {
		super();
	}

	public OrderDetailsKey getOrderDetailsKey() {
		return orderDetailsKey;
	}

	public void setOrderDetailsKey(OrderDetailsKey orderDetailsKey) {
		this.orderDetailsKey = orderDetailsKey;
	}

	public OrdersDto getOrders() {
		return orders;
	}

	public void setOrders(OrdersDto orders) {
		this.orders = orders;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
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

	
	
}
