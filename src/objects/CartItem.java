package objects;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
	private static final long serialVersionUID = 5972435378762824013L;
	private Pizza pizzaObj;
	private Integer amount;
	private String date, time;
	
	
	public CartItem(Integer amount, Pizza pizzaObj, String date, String time) {
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
		double finalprice = pizzaObj.getPrice() * amount.doubleValue();
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
