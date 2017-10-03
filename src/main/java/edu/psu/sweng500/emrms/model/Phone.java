package edu.psu.sweng500.emrms.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import edu.psu.sweng500.emrms.util.FormatUtils;
import edu.psu.sweng500.emrms.validators.annotations.DigitCount;

public class Phone implements Serializable {

	@DigitCount(value=10)
	private String number;

	public Phone() {
	}

	public Phone(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : FormatUtils.unformatNumber(number).hashCode());
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
		Phone other = (Phone)obj;
		if(number == null) {
			if(other.number != null) {
				return false;
			}
		} else {
			String myNumber = FormatUtils.unformatNumber(number);
			String otherNumber = FormatUtils.unformatNumber(other.number);
			if(!myNumber.equals(otherNumber)) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return StringUtils.isEmpty(number);
	}

	@Override
	public String toString() {
		return number == null ? "" : number;
	}
}
