package org.camunda.demo.multipleDatasources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order", schema = "public")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "order_number")
	private String orderNumber;

	@Column(name = "order_fail")
	private Boolean orderFail;

	@Column(name = "process_fail")
	private Boolean processFail;

	@Column(name = "order_amount")
	private Double orderAmount;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Boolean getOrderFail() {
		return orderFail;
	}

	public void setOrderFail(Boolean orderFail) {
		this.orderFail = orderFail;
	}

	public Boolean getProcessFail() {
		return processFail;
	}

	public void setProcessFail(Boolean processFail) {
		this.processFail = processFail;
	}

}
