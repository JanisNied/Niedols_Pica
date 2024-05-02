package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import org.jxmapviewer.viewer.GeoPosition;

public class Customer implements Serializable{
	private static final long serialVersionUID = 5972435378762824013L;
	private String name, surname, number, typeofpayment, extraInfo, typeofdelivery, date, time;
	private GeoPosition address;
	private int savedLevel;
	private double total, deliveryFee;
	private long orderNum;
	private ArrayList<CartItem> cart = new ArrayList<CartItem>();
	private boolean orderComplete = false;
	
	public Customer() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeoPosition getAddress() {
		return address;
	}

	public void setAddress(GeoPosition address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTypeofpayment() {
		return typeofpayment;
	}

	public void setTypeofpayment(String typeofpayment) {
		this.typeofpayment = typeofpayment;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTypeofdelivery() {
		return typeofdelivery;
	}

	public void setTypeofdelivery(String typeofdelivery) {
		this.typeofdelivery = typeofdelivery;
	};
	public String toString() {
		String data = "Name: "+getName()+
					  ", Surname: "+getSurname()+
					  ", Type of Payment: "+getTypeofpayment()+
					  ", Delivery: "+getTypeofdelivery();
		return data;
	}

	public double getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	public double getFull() {
		double full = 0.00;
		full = getTotal() + getDeliveryFee();
		return full;
	}

	public ArrayList<CartItem> getCart() {
		return cart;
	}

	public void setCart(ArrayList<CartItem> cart) {
		this.cart = cart;
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	@Override
    public int hashCode() {
        return Objects.hash(name, surname, number, typeofpayment, extraInfo, typeofdelivery, date, time, address, total, deliveryFee, orderNum, orderComplete, cart);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customer customer = (Customer) obj;
        return Double.compare(customer.total, total) == 0 &&
                Double.compare(customer.deliveryFee, deliveryFee) == 0 &&
                orderNum == customer.orderNum &&
                orderComplete == customer.orderComplete &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(number, customer.number) &&
                Objects.equals(typeofpayment, customer.typeofpayment) &&
                Objects.equals(extraInfo, customer.extraInfo) &&
                Objects.equals(typeofdelivery, customer.typeofdelivery) &&
                Objects.equals(date, customer.date) &&
                Objects.equals(time, customer.time) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(cart, customer.cart);
    }

	public int getSavedLevel() {
		return savedLevel;
	}

	public void setSavedLevel(int savedLevel) {
		this.savedLevel = savedLevel;
	}
}
