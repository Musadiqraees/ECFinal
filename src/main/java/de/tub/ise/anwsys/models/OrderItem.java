package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class OrderItem implements Serializable {
	
private static final long serialVersionUID = -6526001541954993511L;

@Id
Integer pizzaId;	
Integer quantity;

public Integer getPizzaId() {
	return pizzaId;
}
public Integer getQuantity() {
	return quantity;
}

public void setPizzaId(Integer pizzaId) {
	this.pizzaId = pizzaId;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}

public OrderItem(Integer pizzaId,Integer quantity) 
{
	this.pizzaId=pizzaId;
	
	this.quantity =quantity;
	
	
	// TODO Auto-generated constructor stub
}
	
	/**
	 * 
	 */
	

	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

}
