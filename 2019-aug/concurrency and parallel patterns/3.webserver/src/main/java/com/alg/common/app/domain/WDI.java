package com.alg.common.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WDI {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String countryName;
	private String countryCode;
	private String indicatorName;
	private String indicatorCode;
	@Column(length = 1000)
	private String year_values;

	public void setData(String[] data) throws Exception {
		if (data.length != Constants.NUM_FEATURES) {
			throw new Exception("Data length is not correct: " + data.length);
		}
		countryName = getString(data[0]);
		countryCode = getString(data[1]);
		indicatorName = getString(data[2]);
		indicatorCode = getString(data[3]);
		String res = "";
		for (int i = 4; i < data.length; ++i) {
			if (data[i].trim().length() == 0)
				res += "0.0,";
			else
				res += data[i] + ",";
		}
		year_values = res;

	}

	private String getString(String string) {
		if (string.startsWith("\"")) {
			return string.substring(1, string.length() - 1);
		}
		return string;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}

	public String getYear_values() {
		return year_values;
	}

	public void setYear_values(String year_values) {
		this.year_values = year_values;
	}

	@Override
	public String toString() {
		return "WDI [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode + ", indicatorName="
				+ indicatorName + ", indicatorCode=" + indicatorCode + ", year_values=" + year_values + "]";
	}
	
	

}
