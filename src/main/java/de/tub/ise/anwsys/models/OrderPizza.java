package de.tub.ise.anwsys.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class OrderPizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989848317697433933L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
Integer id;

Float totalPrice;

String recipient;

ArrayList<OrderItem> orderItems;
	
	
	
	public OrderPizza() {
		// TODO Auto-generated constructor stub
	}

	public OrderPizza(Integer id,ArrayList<OrderItem> orderItems,Float totalPrice,String recipient) {
		// TODO Auto-generated constructor stub
		//this.id=id;
		this.orderItems=orderItems;
		this.totalPrice=totalPrice;
		this.recipient=recipient;
		
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Float getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getRecipient() {
		return recipient;
	}



	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}



	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	

}
