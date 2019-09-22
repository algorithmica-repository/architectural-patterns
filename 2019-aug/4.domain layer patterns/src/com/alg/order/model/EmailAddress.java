package com.alg.order.model;

import java.util.regex.Pattern;

import com.alg.order.common.AssertionConcern;
import com.alg.order.common.ValueObject;

public final class EmailAddress extends AssertionConcern implements ValueObject {
	private String address;

	public EmailAddress() {
		super();
	}

	public EmailAddress(String anAddress) {
		super();
		this.setAddress(anAddress);
	}

	public String address() {
		return this.address;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			EmailAddress typedObject = (EmailAddress) anObject;
			equalObjects = this.address().equals(typedObject.address());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		return this.address().hashCode();
	}

	@Override
	public String toString() {
		return "EmailAddress [address=" + address + "]";
	}

	private void setAddress(String anAddress) {
		this.assertArgumentNotEmpty(anAddress, "The email address is required.");
		this.assertArgumentLength(anAddress, 1, 100,
				"Email address must be 100 characters or less.");
		this.assertArgumentTrue(Pattern.matches(
				"\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",
				anAddress), "Email address format is invalid.");

		this.address = anAddress;
	}
}