package org.restful.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.restful.ws.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	private static BigInteger nextId;
	private static Map<BigInteger, Customer> customerMap;
	
	private static Customer save(Customer customer) {
		if(customerMap == null) {
			customerMap = new HashMap<BigInteger, Customer>();
			nextId = BigInteger.ONE;
		}
		customer.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		customerMap.put(customer.getId(), customer);
		
		return customer;
	}
	
	static {
		Customer c1 = new Customer();
		c1.setName("AAA");
		save(c1);
		
		Customer c2 = new Customer();
		c2.setName("BBB");
		save(c2);
	}
	
	@RequestMapping(value="/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Customer>> getCustomers() {
		
		Collection<Customer> customers = customerMap.values();
		
		return new ResponseEntity<Collection<Customer>>(customers, HttpStatus.OK);
	}

	@RequestMapping(value="/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") BigInteger id) {
		
		Customer customer = customerMap.get(id);
		if(customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		
		Customer savedCustomer = save(customer);
		
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.OK);
	}
}
