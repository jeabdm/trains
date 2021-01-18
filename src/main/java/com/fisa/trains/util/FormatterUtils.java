package com.fisa.trains.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatterUtils {

	public static String formatDouble(Double value, int maximumFraction) {
		
		NumberFormat nf = DecimalFormat.getInstance();
		nf.setMaximumFractionDigits(maximumFraction);
		return nf.format(value);
	}
}
