package main;

import java.util.HashMap;

public class Settings {
	public static HashMap<String, String> currentSettings = 
			new HashMap<String, String>();
	public static HashMap<String, String> lang = 
			new HashMap<String, String>();
	
	public static HashMap<String, Double> prices;
	static {
		prices = new HashMap<String, Double>();
		prices.put("20", 		1.00);
		prices.put("30", 		1.50);
		prices.put("60", 		2.50);
		
		prices.put("thin", 		1.50);
		prices.put("regular", 	2.00);
		prices.put("thick", 	3.00);
	}
}
