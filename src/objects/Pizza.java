package objects;

import java.util.ArrayList;

import main.Settings;

public class Pizza {
	private int doughSize;
	private IngredientHolder doughType;
	private ArrayList<IngredientHolder> sauces, cheese, meat, veggies;
	private double price, doughsizetypemultiplier;
	
	public Pizza(int doughSize, IngredientHolder doughType) {
		this.doughSize = doughSize;
		this.doughType = doughType;
		sauces = new ArrayList<IngredientHolder>();
		cheese = new ArrayList<IngredientHolder>();
		meat = new ArrayList<IngredientHolder>();
		veggies = new ArrayList<IngredientHolder>();
	}
	public void addToPrice(double price) {
		this.price -= price;
	}
	public void removeFromPrice(double price) {
		this.price -= price;
	}
	public Double getPrice() {
		doughsizetypemultiplier = Settings.prices.get(Integer.toString(doughSize)) * Settings.prices.get(doughType.getName());
		return price + doughsizetypemultiplier;
	}
	public void setDoughSize(int doughSize) {
		this.doughSize = doughSize;
	}
	public void setDoughType(IngredientHolder doughType) {
		this.doughType = doughType;
	}
	public void addIngredient(IngredientHolder ingredient) {
		switch(ingredient.getType()) {
			case "sauce":
				sauces.add(ingredient);
				break;
			case "cheese":
				cheese.add(ingredient);
				break;
			case "meat":
				meat.add(ingredient);
				break;
			case "veggies":
				veggies.add(ingredient);
				break;	
		}
	}
	public void removeIngredient(IngredientHolder ingredient) {
		switch(ingredient.getType()) {
			case "sauce":
				sauces.remove(ingredient);
				break;
			case "cheese":
				cheese.remove(ingredient);
				break;
			case "meat":
				meat.remove(ingredient);
				break;
			case "veggies":
				veggies.remove(ingredient);
				break;	
		}
	}
	public String description() {
		StringBuilder desc = new StringBuilder(doughSize+" cm, " +Settings.lang.get(doughType.getLocale())+" "+Settings.lang.get("dough.text")+"!");
		int allElements = sauces.size() + cheese.size() + meat.size() + veggies.size();
		if (allElements > 0) {
			desc.deleteCharAt(desc.length() - 1);
			ArrayList<IngredientHolder> combined = new ArrayList<IngredientHolder>();
			combined.addAll(sauces);
			combined.addAll(cheese);
			combined.addAll(meat);
			combined.addAll(veggies);
			for (int i = 0; i < combined.size(); i++) {
				if (i == combined.size()-1) {
					desc.append(" and "+Settings.lang.get(combined.get(i).getLocale())+"!");
				} else
					desc.append(", "+Settings.lang.get(combined.get(i).getLocale()));
			}
		}
		return desc.toString();
	}
}