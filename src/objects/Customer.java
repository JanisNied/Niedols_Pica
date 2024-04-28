package objects;

import org.jxmapviewer.viewer.GeoPosition;

public class Customer {
	private String name, surname, number, typeofpayment, extraInfo, typeofdelivery;
	private GeoPosition address;
	
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
}
