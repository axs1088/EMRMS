package edu.psu.sweng500.emrms.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import edu.psu.sweng500.emrms.util.Constants;

public class Address implements Serializable {

	private int addressId;

	@Size(max=60)
	private String specialHandlingText;

	@Size(max=40)
	private String line1;

	@Size(max=40)
	private String line2;

	@Size(max=30)
	private String city;

	@Size(max=2)
	private String state;

	@Size(max=10)
	private String zip;

	private String country;

	private String addressType;
	
	private int mailingAddrSameAsHomeAddr;

	public Address() {
		this.country = Constants.COUNTRY_USA;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getSpecialHandlingText() {
		return specialHandlingText;
	}

	public void setSpecialHandlingText(String specialHandlingText) {
		this.specialHandlingText = specialHandlingText;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [specialHandlingText=" + specialHandlingText + ", line1=" + line1
				+ ", line2=" + line2 + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", mailingAddrSameAsHomeAddr=" + mailingAddrSameAsHomeAddr + ", country=" + country + ", addressType=" + addressType + "]";
	}
	
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public int getMailingAddrSameAsHomeAddr() {
		return mailingAddrSameAsHomeAddr;
	}

	public void setMailingAddrSameAsHomeAddr(int mailingAddrSameAsHomeAddr) {
		this.mailingAddrSameAsHomeAddr = mailingAddrSameAsHomeAddr;
	}	

}
