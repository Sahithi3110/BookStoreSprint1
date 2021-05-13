package com.cg.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Address {
	
	/*
	 * Field Declaration
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	
	@NotNull(message = "Address is required.")
	@Size(max = 100, message = "Address must contain atmost 100 characters.")
	private String address;
	
	@NotNull(message = "City name required.")
	@Size(max = 20, message = "City name should not contain more than 20 characters.")
	private String city;
	
	@NotNull(message = "Country name required.")
	@Size(max = 20, message = "Country name should not contain more than 20 characters.")
	private String country;
	
	@NotNull(message = "Pin Code can not be null.")
	@Size(min = 6, max = 6, message = "Pin Code must contain 6 digits.")
	private String pincode;
	
	/*
	 * Default Constructor
	 */
	
	public Address() {
		
	}

	/*
	 * Parameterized Constructor
	 */
	
	public Address(int addressId, String address, String city, String country, String pincode) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
	}

	/*
	 * Getter, Setter For All Fields
	 */
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
