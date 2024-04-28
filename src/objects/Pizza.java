package objects;

import java.util.ArrayList;
import java.util.Objects;

import main.Settings;

public class Pizza {
	private int doughSize;
	private IngredientHolder doughType;
	private String nickname;
	private ArrayList<IngredientHolder> ingredients;
	
	public Pizza(int doughSize, String nickname, IngredientHolder doughType) {
		this.doughSize = doughSize;
		this.doughType = doughType;
		ingredients = new ArrayList<IngredientHolder>();
		this.nickname = nickname;
	}
	public Pizza(Pizza pizza) {
		this.nickname = pizza.nickname;
		this.doughSize = pizza.doughSize;
		this.doughType = pizza.doughType;
		this.ingredients = new ArrayList<IngredientHolder>(pizza.ingredients);
	}
	public Double getPrice() {
		double doughsizetypemultiplier, price = 0;
		
		for (IngredientHolder i : ingredients) {
			price += Settings.prices.get(i.getIdentifier());
		}
		doughsizetypemultiplier = Settings.prices.get(Integer.toString(doughSize)) * Settings.prices.get(doughType.getName());
		return price + doughsizetypemultiplier;
	}
	public String getNickname() {
		return nickname;
	}
	public ArrayList<IngredientHolder> ingredients(){
		return ingredients;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setDoughSize(int doughSize) {
		this.doughSize = doughSize;
	}
	public void setDoughType(IngredientHolder doughType) {
		this.doughType = doughType;
	}
	public void addIngredient(IngredientHolder ingredient) {
		ingredients.add(ingredient);
	}
	public void removeIngredient(IngredientHolder ingredient) {
		ingredients.remove(ingredient);
	}
	public String description(boolean defaultinfo) {
		StringBuilder desc;
		if (defaultinfo)
			desc = new StringBuilder(doughSize+" cm, " +Settings.lang.get(doughType.getLocale())+" "+Settings.lang.get("dough.text")+" ");
		else
			desc = new StringBuilder(" ");
		if (ingredients.size() > 0) {
			desc.deleteCharAt(desc.length() - 1);
			for (int i = 0; i < ingredients.size(); i++) {
				if (i == ingredients.size()-1) {
					if (ingredients.size() != 1 || defaultinfo)
						desc.append(" "+Settings.lang.get("and.text")+" "+Settings.lang.get(ingredients.get(i).getLocale()));
					else
						desc.append(Settings.lang.get(ingredients.get(i).getLocale()));
				} else
					if (desc.isEmpty())
						desc.append(Settings.lang.get(ingredients.get(i).getLocale()));
					else
						desc.append(", "+Settings.lang.get(ingredients.get(i).getLocale()));
			}
		}
		return desc.toString();
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return doughSize == pizza.doughSize &&
                Objects.equals(doughType, pizza.doughType) &&
                Objects.equals(nickname, pizza.nickname) &&
                Objects.equals(ingredients, pizza.ingredients);
    }
    @Override
    public int hashCode() {
        return Objects.hash(doughSize, doughType, nickname, ingredients);
    }
}