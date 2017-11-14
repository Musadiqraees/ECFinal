package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class Pizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6009190778756332608L;

	@Id
	Integer id;
	
	String name;
	Integer price;
	
	public enum Size
	{
	
		Standard, Large 
		
	}
	Size size;
	
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public Integer getPrice() {
		return price;
	}
 
	
	
	
	public Size getSize() {
		return size;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	 
	public void setSize(Size size) {
		
		this.size=size;
		
	}
	 
	
	public Pizza( String name, Integer id ,Integer price, Size size ) 
	{
	this.name=name;
	this.id=id;
	this.price=price;
	this.size=size;
	}

	 
	
	public Pizza() {}
	
	

}
