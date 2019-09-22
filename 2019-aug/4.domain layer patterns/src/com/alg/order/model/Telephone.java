package com.alg.order.model;

import com.alg.order.common.AssertionConcern;
import com.alg.order.common.ValueObject;

public final class Telephone extends AssertionConcern implements ValueObject {
	private String number;

	public Telephone(String aNumber) {
		this();

		this.setNumber(aNumber);
	}

	public String number() {
		return this.number;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			Telephone typedObject = (Telephone) anObject;
			equalObjects = this.number().equals(typedObject.number());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(35137 * 239) + this.number().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "Telephone [number=" + number + "]";
	}

	public Telephone() {
		super();
	}

	private void setNumber(String aNumber) {
		this.assertArgumentNotEmpty(aNumber, "Telephone number is required.");
		this.assertArgumentLength(aNumber, 5, 20,
				"Telephone number may not be more than 20 characters.");
/*		this.assertArgumentTrue(Pattern.matches(
				"((\\(\\d{3}\\))|(\\d{3}-))\\d{3}-\\d{4}", aNumber),
				"Telephone number or its format is invalid.");*/

		this.number = aNumber;
	}
}
