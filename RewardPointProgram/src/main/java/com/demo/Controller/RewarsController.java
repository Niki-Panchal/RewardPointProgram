package com.demo.Controller;

import com.demo.Exception.ExceptionHandler;
import com.demo.entity.Customer;
import com.demo.entity.CustomerPurchase;
import com.demo.model.RewardsPoint;
import com.demo.repository.CustomerRepository;
import com.demo.service.RewardService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class RewarsController {
	

	@Autowired
	RewardService rewardservice;
	
	@Autowired
	CustomerRepository custorepo;
	
	@GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        
		Customer customer = custorepo.findByCustomerId(customerId);
       
    
//        	try {
//        		
//        		if(customer == null)
//        			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        	}
//        	catch(Exception e) 
//        	{
//        		ExceptionHandler ex = new ExceptionHandler("Exception occured while fethching data for customer id"+ customerId );
//        		return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
//        	}
        
		if(customer==null)
		{
			ExceptionHandler ex = new ExceptionHandler();
			ex.setErrorMessage("Id Not Found");
			return new ResponseEntity<>(ex,HttpStatus.NOT_FOUND);
		}
			
        RewardsPoint customerRewards = rewardservice.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards,HttpStatus.OK);
    }
	
	@PostMapping("/createcustomer")
	public Customer addcustomer (@RequestBody Customer customer) {
		return rewardservice.addcustomer(customer);
	}
	
	@PostMapping("/purchaseDetail")
	public CustomerPurchase addpurchasedetail(@RequestBody CustomerPurchase purdetail)
	{
		return rewardservice.addpurchasedetail(purdetail);
	}
	
}
