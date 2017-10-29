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

	public boolean isEmpty() {
		return StringUtils.isEmpty(number);
	}

	@Override
	public String toString() {
		return number == null ? "" : number;
	}
}
