package belajar.ProductOrder.Dto;

import java.sql.Date;
import java.util.List;

public class OrdersDto {
	
	private Long ordersId;
	private Date orderDate;
	private List<OrderDetailsDto> orderDetailsList;
	
	public OrdersDto(Long ordersId, Date orderDate) {
		super();
		this.ordersId = ordersId;
		this.orderDate = orderDate;
	}

	public OrdersDto() {
		super();
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderDetailsDto> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailsDto> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	
	
}
