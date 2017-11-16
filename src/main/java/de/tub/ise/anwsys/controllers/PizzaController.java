package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.OrderPizza;
import de.tub.ise.anwsys.models.Pizza;

import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.models.Pizza.Size;
import de.tub.ise.anwsys.repos.OrderItemRepository;
import de.tub.ise.anwsys.repos.OrderRepository;
import de.tub.ise.anwsys.repos.PizzaRepository;
import de.tub.ise.anwsys.repos.UserRepository;
import de.tub.ise.anwsys.repos.ToppingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 



import com.jayway.jsonpath.Criteria;

import antlr.collections.List;

@RestController
public class PizzaController {
	 
	
	 private PizzaRepository PizzaRepository;
	 
	 private ToppingRepository ToppingRepository;
	 
	 private OrderItemRepository OrderItemRepo;
	 
	 private OrderRepository orderRepository;
	 
	 
	 @Autowired
	    public PizzaController(PizzaRepository PizzaRepository,ToppingRepository ToppingRepository,OrderItemRepository OrderItemRepo,OrderRepository orderRepository) {
	        this.PizzaRepository = PizzaRepository;
	        this.ToppingRepository = ToppingRepository;
	        this.OrderItemRepo= OrderItemRepo;
	        this.orderRepository = orderRepository;
	    }
	 
	 
	
	public PizzaController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
 
	 
	////////Added new pizza
	
	@RequestMapping(method = RequestMethod.POST, path = "/pizza" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addPizza( @RequestBody Pizza pizza) {
		 
		 if(PizzaRepository.exists(pizza.getId())) 
		 {
		
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid input")); 
	 }
		 else {
			 
			 PizzaRepository.save(pizza);
			 
			 return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new pizza.")); 
				
		 } 
	}
	
	///update
	
	@RequestMapping(method = RequestMethod.PUT, path = "/pizza/{pizzaId}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updatePizza( @RequestBody Pizza pizza,@PathVariable Integer PizzaId) {
		 
		 if(PizzaRepository.findOne(PizzaId) != null) 
		 {
              pizza.setId(PizzaId);
			 
			// PizzaRepository.save(pizza);
			 
			 return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Update okay")); 
				
		 }
		 else 
		 {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza not found"));  
		 }
	}
	
	

	
////////DElete pizza with PizzaId
	
	
 @RequestMapping(method = RequestMethod.DELETE, path = "/pizza/{pizzaId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> removePizzaWithID(@PathVariable Integer PizzaId){
	 
	 if(PizzaRepository.findOne(PizzaId) != null)
	 {
		 PizzaRepository.delete(PizzaId);
		 
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
		
	 }
	 else {
		 
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Pizza not found")); 
      }
	 }
	
	
	
////////get pizza with PizzaId
	
	
	 @RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> getPizzaWithID(@PathVariable Integer PizzaId){
		 
		 if(PizzaRepository.findOne(PizzaId) != null)
		 {
			  return ResponseEntity.ok(PizzaRepository.findOne(PizzaId));
		 }
		 
		 else 
		 {
		 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza could not be found")); 
	     }
		 
		 }
	    
	 
////////get all pizza
		
		
	 @RequestMapping(method = RequestMethod.GET, path = "/pizza",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> getAllpizza()
	 {
		 ArrayList<Pizza> PizzaObjects = new ArrayList<Pizza>();
		 PizzaObjects =   (ArrayList<Pizza>) PizzaRepository.findAll();
		 
		 
		 if (PizzaObjects.isEmpty()) {
			 
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("No pizzas exist")); 
			    
		 }
		 else 
		 {
		 return ResponseEntity.ok(PizzaObjects);  
	 }
	 }
	
	 
	 
//	 @RequestMapping(method = RequestMethod.POST, path = "/AddPizza")
//	    public ResponseEntity<?> AddPizza(@RequestParam(value = "name", defaultValue = "AnwSys Student") String name, @RequestParam (value = "Id", defaultValue = "23" ) Integer Id
//	            , @RequestParam (value = "size", defaultValue = "12" ) Size size
//	            , @RequestParam(value = "price", defaultValue = "23" ) Integer price) {
//	    	
//		 
//		 if(PizzaRepository.findOne(Id) != null) 
//		 {
//			
//			 return ResponseEntity.ok(String.format("pizza with this ID  %s! already exist", Id ));  
//		 }
//		 else
//		 {
//		 
//		 Pizza tempPizza = new Pizza( name, Id , price,  size ) ;
//		 
//		   PizzaRepository.save(tempPizza);
//	    	
//	    	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Pizza Added check detail %s!", name+price+size)); 
//	    
//		  }
//		  
//	    	
//	    }
//	/////////////////////////////////////////
	 ////////////////////////////////Topping////////
	 //////////////////////////////////////////////////////////////////
	 
	 //addingPizzaTopping
	 @RequestMapping(method = RequestMethod.POST, path = "/pizza/{pizzaId}/topping" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> addPizzaTopping( @RequestBody Topping topping,@PathVariable Integer pizzaId) {
			 
			 if(PizzaRepository.exists(pizzaId)) 
			 {
				 //topping.setPizzaId(pizzaId);
				 
			Pizza pizza=	PizzaRepository.findOne(pizzaId);
			
			ArrayList<Integer> ToppingIds= pizza.getToppingIds();
			
			ToppingIds.add(topping.getId());
			
			pizza.setToppingIds(ToppingIds);
			
			Float toppingCost = topping.getPrice();
			
			Float pizzaCost =  pizza.getPrice();
			
			Float totalPizzaPrice=  toppingCost+pizzaCost;
			
			pizza.setPrice(totalPizzaPrice);
			
			PizzaRepository.save(pizza);
			
			ToppingRepository.save(topping);
		
		    return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new Topping for pizza."));
			 }
			 else 
			 {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid input")); 
					
			 } 
		}
		
	 
	 
	 //getPizzaToppings
	 @RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}/topping" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> getPizzaToppings(@PathVariable Integer pizzaId) {
			 
			 if(PizzaRepository.exists(pizzaId)) 
			 {
				 Pizza pizza=	PizzaRepository.findOne(pizzaId);
				 
				 ArrayList<Integer> PizzaObjects = new ArrayList<Integer>();
				 
				 PizzaObjects = pizza.getToppingIds();
				 
				 return ResponseEntity.status(HttpStatus.CREATED).body(PizzaObjects);
			 }
			 else 
			 {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid input")); 
					
			 } 
		}
		
	 
	 
	 
	//getListOfPizzaToppingsBYID
		 @RequestMapping(method = RequestMethod.GET, path = "/pizza/{pizzaId}/topping/{toppingId}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
		    public ResponseEntity<?> getPizzaToppingById(@PathVariable Integer pizzaId,@PathVariable Integer toppingId) {
				 
				 if(PizzaRepository.exists(pizzaId)) 
				 {
					 Pizza pizza=	PizzaRepository.findOne(pizzaId);
					 
					 ArrayList<Integer> toppingObjects = new ArrayList<Integer>();
					 
					 toppingObjects = pizza.getToppingIds();
					 
					 if(toppingObjects.contains(toppingId))
					 {
						 
					Topping toppingObj	= ToppingRepository.findOne(toppingId);
						
					return ResponseEntity.status(HttpStatus.CREATED).body(toppingObj);
					 }
					 
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid ID(s) supplied."));
					 
				 }
				 else 
				 {
					 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza or Topping not found.")); 
						
				 } 
			}
		 
		 
		 
		//deletePizzaToppingsBYID
		 @RequestMapping(method = RequestMethod.DELETE, path = "/pizza/{pizzaId}/topping/{toppingId}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
		    public ResponseEntity<?> deletePizzaToppingById(@PathVariable Integer pizzaId,@PathVariable Integer toppingId) {
				 
				 if(PizzaRepository.exists(pizzaId)) 
				 {
					 Pizza pizza=	PizzaRepository.findOne(pizzaId);
					 
					 ArrayList<Integer> toppingObjects = new ArrayList<Integer>();
					 
					 toppingObjects = pizza.getToppingIds();
					 
					 if(toppingObjects.contains(toppingId))
					 {
						 
					Topping toppingObj	= ToppingRepository.findOne(toppingId);
					
					
					////////price change in pizza after topping is removed
					Float pizzaPrice =  pizza.getPrice();
					
					Float toppingPrice =  toppingObj.getPrice();
					
				 Float newPizzaPrice = pizzaPrice - toppingPrice;
				
				 pizza.setPrice(newPizzaPrice);
				 
				 
				 //////// remove topping Id
				 
				 toppingObjects.remove(toppingId);
				 
				 pizza.setToppingIds(toppingObjects);
				 
				 PizzaRepository.save(pizza);
				 
				 
				 
				 /////removing topping
				 
				 ToppingRepository.delete(toppingId);
						
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body(toppingObj);
					 }
					 
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid ID(s) supplied."));
					 
				 }
				 else 
				 {
					 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza or Topping not found.")); 
						
				 } 
			}
		 
		 
		 
		 ///////////////////
		 /////////////////////////////
		 /////////OrderItem
		 
		 
			@RequestMapping(method = RequestMethod.POST, path = "/order" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
		    public ResponseEntity<?> addPizzaOrder( @RequestBody OrderPizza order) {
				 
				 if(orderRepository.exists(order.getId())) 
				 {
				
				 return ResponseEntity.ok(String.format("Invalid Order. "));  
			 }
				 else {
					 
					 orderRepository.save(order);
					 
					 return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new order successfully.")); 
						
				 } 
			} 
		 
		 
	 
}
