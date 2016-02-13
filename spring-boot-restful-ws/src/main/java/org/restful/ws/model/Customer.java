package org.restful.ws.model;

import java.math.BigInteger;

public class Customer {
	
	private BigInteger id;
	private String name;
	
	public Customer() {

	}
	
	public Customer(BigInteger id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BigInteger getId() {
		return id;
	}
	
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Customer[ id: " + id + ", name: " + name + "]";
	}
}
