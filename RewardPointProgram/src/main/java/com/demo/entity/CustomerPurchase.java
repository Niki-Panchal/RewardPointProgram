package com.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_Detail")
public class CustomerPurchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prchase_Id")
	private Long purchaseId;
	
	@Column(name= "customer_Id")
	private Long customerId;
	
	@Column(name="prchase_Date")
	private Timestamp purchaseDate;
	
	@Column(name="Amount")
	private double amount;
	
	
	public CustomerPurchase() {
		super();
	}


	public CustomerPurchase(Long purchaseId, Long customerId, Timestamp purchaseDate, double amount) {
		super();
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.purchaseDate = purchaseDate;
		this.amount = amount;
	}
	

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	

}
