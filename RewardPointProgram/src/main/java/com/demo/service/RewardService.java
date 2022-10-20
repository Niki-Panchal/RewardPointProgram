package com.demo.service;

import com.demo.entity.Customer;
import com.demo.entity.CustomerPurchase;
import com.demo.model.RewardsPoint;


public interface RewardService {
	
	public RewardsPoint getRewardsByCustomerId(Long customerId);

	public Customer addcustomer(Customer customer);
	
	public CustomerPurchase addpurchasedetail(CustomerPurchase purdetail);

	
}
