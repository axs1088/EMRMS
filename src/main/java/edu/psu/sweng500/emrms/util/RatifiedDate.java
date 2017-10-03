package edu.psu.sweng500.emrms.util;

import java.util.Date;

/** A date that can store the original user input if it is not ratified
 * (i.e. could not be parsed).
 */
public class RatifiedDate extends Date {
	private static final long serialVersionUID = -692862184165047219L;

	/** The original user input. */
	private String original;

	public RatifiedDate() {
		super();
	}

	public RatifiedDate(long date) {
		super(date);
	}

	/**
	 * Gets the original.
	 *
	 * @return the original
	 */
	public String getOriginal() {
		return original;
	}

	/**
	 * Sets the original.
	 *
	 * @param original the new original
	 */
	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	 * Checks if this date is ratified (i.e. its original input was valid)
	 *
	 * @return true, if is ratified
	 */
	public boolean isRatified() {
		return (original == null);
	}

	@Override
	public String toString() {
		if(original != null) {
			return original;
		} else {
			return super.toString();
		}
	}
}
