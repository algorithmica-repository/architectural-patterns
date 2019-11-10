package com.alg.common.app.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WDILoader {

	public static List<WDI> load() {
		Path file = Paths.get(Constants.DATA_ROUTE);
		List<WDI> dataSet = new ArrayList<>();
		int lineNumber = 1;
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			// First line are headers
			line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String data[] = parse(line);
				++lineNumber;
				WDI dataObject = new WDI();
				dataObject.setData(data);
				dataSet.add(dataObject);
				if (lineNumber == 10) {
					System.out.println("data loading finished");
					break;
				}
			}
		} catch (IOException x) {
			x.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSet;
	}

	private static String[] parse(String line) {
		String[] ret = new String[65];
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		boolean enComillas = false;
		for (int i = 0; i < line.length(); i++) {
			char letra = line.charAt(i);
			if (letra == '"') {
				enComillas = !enComillas;
			} else if ((letra == ',') && (enComillas == false)) {
				ret[index] = buffer.toString();
				index++;
				buffer = new StringBuffer();
			} else {
				buffer.append(letra);
			}
		}
		ret[index] = buffer.toString();
		index++;
		return ret;
	}

}
