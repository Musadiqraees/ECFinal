package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Topping implements Serializable {

	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 5883552364326747851L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
    String name;
    Float  price;
     
   
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Float getPrice() {
		return price;
	}
	
 
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	
 
	
	public Topping(Integer id, String name , Float price) 
	{
		this.price=price;
		this.name=name;
		//this.id=id;
		
	}
	
	public Topping() 
	{
		// TODO Auto-generated constructor stub
	}

}
