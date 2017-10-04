package edu.psu.sweng500.emrms.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;

import edu.psu.sweng500.emrms.util.Constants;

public class Address implements Serializable {

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
	
	private String mailingAddrSameAsStreetAddr;

	public Address() {
		this.country = Constants.COUNTRY_USA;
	}

	public boolean isEmpty() {
		return isEmptyIgnoreCountryUS(false);
	}

	public boolean isEmptyIgnoreCountryUS(boolean ignoreCountryUS) {
		return StringUtils.isEmpty(specialHandlingText) &&
			   StringUtils.isEmpty(line1) &&
			   StringUtils.isEmpty(line2) &&
			   StringUtils.isEmpty(city) &&
			   StringUtils.isEmpty(state) &&
			   StringUtils.isEmpty(zip) &&
			   StringUtils.isEmpty(mailingAddrSameAsStreetAddr) &&
			   (StringUtils.isEmpty(country) || (ignoreCountryUS && Constants.COUNTRY_USA.equals(country)));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((line1 == null) ? 0 : line1.hashCode());
		result = prime * result + ((line2 == null) ? 0 : line2.hashCode());
		result = prime * result
				+ ((specialHandlingText == null) ? 0 : specialHandlingText.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		result = prime * result + ((mailingAddrSameAsStreetAddr == null) ? 0 : mailingAddrSameAsStreetAddr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Address other = (Address)obj;
		if(city == null) {
			if(other.city != null) {
				return false;
			}
		} else if(!city.equals(other.city)) {
			return false;
		}
		if(country == null) {
			if(other.country != null) {
				return false;
			}
		} else if(!country.equals(other.country)) {
			return false;
		}
		if(line1 == null) {
			if(other.line1 != null) {
				return false;
			}
		} else if(!line1.equals(other.line1)) {
			return false;
		}
		if(line2 == null) {
			if(other.line2 != null) {
				return false;
			}
		} else if(!line2.equals(other.line2)) {
			return false;
		}
		if(specialHandlingText == null) {
			if(other.specialHandlingText != null) {
				return false;
			}
		} else if(!specialHandlingText.equals(other.specialHandlingText)) {
			return false;
		}
		if(state == null) {
			if(other.state != null) {
				return false;
			}
		} else if(!state.equals(other.state)) {
			return false;
		}
		if(zip == null) {
			if(other.zip != null) {
				return false;
			}
		} else if(!zip.equals(other.zip)) {
			return false;
		}
		if(mailingAddrSameAsStreetAddr == null) {
			if(other.mailingAddrSameAsStreetAddr != null) {
				return false;
			}
		} else if(!mailingAddrSameAsStreetAddr.equals(other.mailingAddrSameAsStreetAddr)) {
			return false;
		}
		return true;
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
				+ ", mailingAddrSameAsStreetAddr=" + mailingAddrSameAsStreetAddr + ", country=" + country + ", addressType=" + addressType + "]";
	}
	
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getMailingAddrSameAsStreetAddr() {
		return mailingAddrSameAsStreetAddr;
	}

	public void setMailingAddrSameAsStreetAddr(String mailingAddrSameAsStreetAddr) {
		this.mailingAddrSameAsStreetAddr = mailingAddrSameAsStreetAddr;
	}
	
}
