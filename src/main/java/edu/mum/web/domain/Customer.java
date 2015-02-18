package edu.mum.web.domain;

public class Customer {
	private String customerId;
	private String name;	
	private Address billingAddress;
	private String phoneNumber;
	
	
	public Customer() {
		super();
		this.billingAddress = new Address();
		}
	
	public Customer(String customerId, String name) {
		this();
		this.customerId = customerId;
		this.name = name;
		}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	
}
