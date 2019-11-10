package com.alg.common.app.domain;

import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alg.common.app.repository.IWDIRepository;

@Service
public class WDIService {

	@Autowired
	private IWDIRepository repository;

	public void load() {
		List<WDI> dataSet = WDILoader.load();
		for (WDI wdi : dataSet)
			repository.save(wdi);

	}

	public String query(String countryCode, String indicatorCode) {
		List<WDI> res = repository.findByCountryCodeAndIndicatorCode(countryCode, indicatorCode);
		// System.out.println("Service query:" + res);
		if (res.size() != 1)
			return null;

		StringWriter writer = new StringWriter();
		writer.write(countryCode);
		writer.write(";");
		writer.write(indicatorCode);
		writer.write(";");
		String tmp = res.get(0).getYear_values();
		String[] year_values = tmp.split(",");
		for (int i = 0; i < year_values.length; i++) {
			writer.write(year_values[i].toString());
			if (i < year_values.length - 1) {
				writer.write(";");
			}
		}
		return writer.toString();
	}

	public String report(String indicatorCode) {
		List<WDI> data = repository.findByIndicatorCode(indicatorCode);
		// System.out.println("Service report:" + data);

		StringWriter writer = new StringWriter();
		writer.write(indicatorCode);
		writer.write(";");
		for (int i = 0; i < data.size(); i++) {
			WDI wdi = data.get(i);
			String[] year_values = wdi.getYear_values().split(",");

			double mean = 0.0;
			for (int j = 0; j < year_values.length; j++)
				mean += Double.valueOf(year_values[j]);
			mean /= year_values.length;
			writer.write(wdi.getCountryCode());
			writer.write(";");
			writer.write("" + mean);
			writer.write(";");
		}
		return writer.toString();
	}

}
