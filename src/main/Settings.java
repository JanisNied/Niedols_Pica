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
		prices.put("20", 			1.50);
		prices.put("30", 			2.00);
		prices.put("60", 			3.10);
		
		prices.put("thin", 			1.50);
		prices.put("regular", 		2.00);
		prices.put("thick", 		3.00);
		
		prices.put("tomatosauce", 	1.95);
		prices.put("bbqsauce", 		3.25);
		
		prices.put("normal", 		4.75);
		prices.put("mozzarella", 	3.15);
		prices.put("bluecheese", 	3.00);
	}
}
