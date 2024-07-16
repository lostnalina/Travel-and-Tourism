package application;

import java.sql.Date;

public class Table_reservation {
	public Table_reservation() {
		
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	private String id;
	 private String phone;
	 private String packages;
	 private String person;
	 private double price;
	 private Date date;
   public Table_reservation(String name,String id,String phone,String packages,String person,Double price,Date date) {
	this.name=name;
	this.id=id;
	this.phone=phone;
	this.packages=packages;
	this.person=person;
	this.price=price;
	this.date=date;
	
	
	
	
}

}