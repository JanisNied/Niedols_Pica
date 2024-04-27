package objects;

public class CartItem {
	private Pizza pizzaObj;
	private int amount;
	private double finalprice;
	
	public CartItem(int amount, Pizza pizzaObj) {
		this.amount = amount;
		this.pizzaObj = pizzaObj;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getFinalPrice() {
		return finalprice;
	}
	public void setFinalPrice(int finalprice) {
		this.finalprice = finalprice;
	}
	public Pizza getPizzaObj() {
		return pizzaObj;
	}
	public void setPizzaObj(Pizza pizzaObj) {
		this.pizzaObj = pizzaObj;
	}
	
}
