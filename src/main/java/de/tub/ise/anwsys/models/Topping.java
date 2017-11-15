package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Topping implements Serializable {

	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 5883552364326747851L;
	
	@Id
	Integer id;
    String name;
    Number  price;
    Integer   pizzaId;
    
    
    
    
    Pizza pizza;
    
    
    public Pizza getPizza() {
		return pizza;
	}
    
    public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Number getPrice() {
		return price;
	}
	
	public Integer getPizzaId() {
		return pizzaId;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Number price) {
		this.price = price;
	}
	
	
	
	 public void setPizzaId(Integer pizzaId) {
		this.pizzaId = pizzaId;
	}
	
	public Topping(Integer id, String name , Number price) 
	{
		this.price=price;
		this.name=name;
		this.id=id;
		
	}
	
	public Topping() 
	{
		// TODO Auto-generated constructor stub
	}

}
