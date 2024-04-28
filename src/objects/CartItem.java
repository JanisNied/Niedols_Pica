package objects;

import java.util.Objects;

public class CartItem {
	private Pizza pizzaObj;
	private int amount;
	private String date, time;
	
	
	public CartItem(int amount, Pizza pizzaObj, String date, String time) {
		this.amount = amount;
		this.pizzaObj = pizzaObj;
		this.date = date;
		this.time = time;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getFinalPrice() {
		double finalprice = pizzaObj.getPrice() * Double.valueOf(amount);
		return finalprice;
	}
	public Pizza getPizzaObj() {
		return pizzaObj;
	}
	public void setPizzaObj(Pizza pizzaObj) {
		this.pizzaObj = pizzaObj;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    CartItem cartItem = (CartItem) o;
	    return Objects.equals(pizzaObj, cartItem.pizzaObj);
	}
	    
	@Override
	public int hashCode() {
	    return Objects.hash(pizzaObj);
	}
}
