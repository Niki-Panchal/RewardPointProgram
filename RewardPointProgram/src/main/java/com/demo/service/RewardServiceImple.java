package com.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.Constants.Constants;
import com.demo.entity.Customer;
import com.demo.entity.CustomerPurchase;
import com.demo.model.RewardsPoint;
import com.demo.repository.CustomerRepository;
import com.demo.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;


@Service
public class RewardServiceImple implements RewardService{
	
	@Autowired
	PurchaseRepository purchaserepo;
	
	@Autowired
	CustomerRepository custrepo;
	
	@Override
	public Customer addcustomer(Customer customer) {
		Customer savecustomer = custrepo.save(customer);
		return savecustomer;
		
	}
	
	@Override
	public CustomerPurchase addpurchasedetail(CustomerPurchase purdetail) {
		
		CustomerPurchase savedetail = purchaserepo.save(purdetail);
		return savedetail;
	}
	
	
	@Override
	public RewardsPoint getRewardsByCustomerId(Long customerId) {
			
		Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
		Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonths);
		Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonths);

		List<CustomerPurchase> lastMonthTransactions = purchaserepo.findAllByCustomerIdAndPurchaseDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		
		List<CustomerPurchase> lastSecondMonthTransactions = purchaserepo
				.findAllByCustomerIdAndPurchaseDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		
	
		List<CustomerPurchase> lastThirdMonthTransactions = purchaserepo
				.findAllByCustomerIdAndPurchaseDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		RewardsPoint customerRewards = new RewardsPoint();
		customerRewards.setCustomerId(customerId);
		customerRewards.setFirstMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;
	}
	
	private Long getRewardsPerMonth(List<CustomerPurchase> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	private Long calculateRewards(CustomerPurchase t) {
		if (t.getAmount() > Constants.firstRewardLimit && t.getAmount() <= Constants.secondRewardLimit) {
			return Math.round(t.getAmount() - Constants.firstRewardLimit);
		} else if (t.getAmount() > Constants.secondRewardLimit) {
			return Math.round(t.getAmount() - Constants.secondRewardLimit) * 2
					+ (Constants.secondRewardLimit - Constants.firstRewardLimit);
		} else
			return 0l;

	}

	
	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}


	
	
	
	
	

}
