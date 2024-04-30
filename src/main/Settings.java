package main;

import java.util.HashMap;

public class Settings {
	public static HashMap<String, String> currentSettings = 
			new HashMap<String, String>();
	public static HashMap<String, String> lang = 
			new HashMap<String, String>();
	
	public static double deliveryfee = 0.39;
	public static HashMap<String, Double> prices;
	static {
		prices = new HashMap<String, Double>();
		prices.put("20", 			1.59);
		prices.put("30", 			1.99);
		prices.put("60", 			3.19);
		
		prices.put("thin", 			1.59);
		prices.put("regular", 		2.59);
		prices.put("thick", 		3.59);
		
		prices.put("tomatosauce", 	1.99);
		prices.put("bbqsauce", 		3.29);
		
		prices.put("normal", 		4.79);
		prices.put("mozzarella", 	3.19);
		prices.put("bluecheese", 	2.99);
		
		prices.put("ham", 			4.29);
		prices.put("chicken", 		2.19);
		prices.put("salami", 		3.99);
		
		prices.put("pineapple", 	2.29);
		prices.put("mushroom", 		1.99);
		prices.put("tomato", 		1.89);
		prices.put("onion", 		0.99);
		prices.put("pickles", 		1.49);
		prices.put("jalapeno", 		2.29);
	}
}
