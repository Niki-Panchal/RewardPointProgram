package com.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import com.demo.entity.CustomerPurchase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PurchaseRepository extends CrudRepository<CustomerPurchase,Long> {
	
	public List<CustomerPurchase> findAllByCustomerId(Long customerId);
	
	public List<CustomerPurchase> findAllByCustomerIdAndPurchaseDateBetween(Long customerId, Timestamp from,Timestamp to);

}
