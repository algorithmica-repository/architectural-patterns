package com.alg.order.model;

import com.alg.order.common.AssertionConcern;
import com.alg.order.common.ValueObject;

public class PostalAddress extends AssertionConcern implements ValueObject {
	private String city;
	private String countryCode;
	private String postalCode;
	private String stateProvince;
	private String streetAddress;

	public PostalAddress(String aStreetAddress, String aCity,
			String aStateProvince, String aPostalCode, String aCountryCode) {

		super();

		this.setCity(aCity);
		this.setCountryCode(aCountryCode);
		this.setPostalCode(aPostalCode);
		this.setStateProvince(aStateProvince);
		this.setStreetAddress(aStreetAddress);
	}

	public PostalAddress(PostalAddress aPostalAddress) {
		this(aPostalAddress.streetAddress(), aPostalAddress.city(),
				aPostalAddress.stateProvince(), aPostalAddress.postalCode(),
				aPostalAddress.countryCode());
	}

	public String city() {
		return this.city;
	}

	public String countryCode() {
		return this.countryCode;
	}

	public String postalCode() {
		return this.postalCode;
	}

	public String stateProvince() {
		return this.stateProvince;
	}

	public String streetAddress() {
		return this.streetAddress;
	}

	@Override
	public boolean equals(Object anObject) {
		boolean equalObjects = false;

		if (anObject != null && this.getClass() == anObject.getClass()) {
			PostalAddress typedObject = (PostalAddress) anObject;
			equalObjects = this.streetAddress().equals(
					typedObject.streetAddress())
					&& this.city().equals(typedObject.city())
					&& this.stateProvince().equals(typedObject.stateProvince())
					&& this.postalCode().equals(typedObject.postalCode())
					&& this.countryCode().equals(typedObject.countryCode());
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = this.streetAddress().hashCode()
				+ this.city().hashCode() + this.stateProvince().hashCode()
				+ this.postalCode().hashCode() + this.countryCode().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "PostalAddress [streetAddress=" + streetAddress + ", city="
				+ city + ", stateProvince=" + stateProvince + ", postalCode="
				+ postalCode + ", countryCode=" + countryCode + "]";
	}

	protected PostalAddress() {
		super();
	}

	private void setCity(String aCity) {
		this.assertArgumentNotEmpty(aCity, "The city is required.");
		this.assertArgumentLength(aCity, 1, 100,
				"The city must be 100 characters or less.");

		this.city = aCity;
	}

	private void setCountryCode(String aCountryCode) {
		this.assertArgumentNotEmpty(aCountryCode, "The country is required.");
		this.assertArgumentLength(aCountryCode, 2, 2,
				"The country code must be two characters.");

		this.countryCode = aCountryCode;
	}

	private void setPostalCode(String aPostalCode) {
		this.assertArgumentNotEmpty(aPostalCode, "The postal code is required.");
		this.assertArgumentLength(aPostalCode, 5, 12,
				"The postal code must be 12 characters or less.");

		this.postalCode = aPostalCode;
	}

	private void setStateProvince(String aStateProvince) {
		this.assertArgumentNotEmpty(aStateProvince,
				"The state/province is required.");
		this.assertArgumentLength(aStateProvince, 2, 100,
				"The state/province must be 100 characters or less.");

		this.stateProvince = aStateProvince;
	}

	private void setStreetAddress(String aStreetAddress) {
		this.assertArgumentNotEmpty(aStreetAddress,
				"The street address is required.");
		this.assertArgumentLength(aStreetAddress, 1, 100,
				"The street address must be 100 characters or less.");

		this.streetAddress = aStreetAddress;
	}

}
