package belajar.ProductOrder.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "orders")
public class Orders {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ordersId;
	
	@Column(name="order_date")
	private Date orderDate;
	
	@OneToMany(mappedBy = "orders")
    Set<OrderDetails> orderDetails;

	public Orders(@NotNull Long ordersId, Date orderDate) {
		super();
		this.ordersId = ordersId;
		this.orderDate = orderDate;
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


	public Orders() {
		super();
	}
	
	
	
	
}

