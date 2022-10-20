package com.demo.repository;

import com.demo.entity.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	public Customer findByCustomerId(Long customerId);

	
}
