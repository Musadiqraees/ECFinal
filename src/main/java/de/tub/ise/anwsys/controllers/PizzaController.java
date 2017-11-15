package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.Pizza;

import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.models.Pizza.Size;
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
	 
	 @Autowired
	    public PizzaController(PizzaRepository PizzaRepository,ToppingRepository ToppingRepository) {
	        this.PizzaRepository = PizzaRepository;
	        this.ToppingRepository = ToppingRepository;
	    }
	 
	 
	
	public PizzaController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
 
	 
	////////Added new pizza
	
	@RequestMapping(method = RequestMethod.POST, path = "/Pizza" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addPizza( @RequestBody Pizza pizza) {
		 
		 if(PizzaRepository.exists(pizza.getId())) 
		 {
		
		 return ResponseEntity.ok(String.format("pizza with this ID  \"%s!\" already exist", pizza.getId() ));  
	 }
		 else {
			 
			 PizzaRepository.save(pizza);
			 
			 return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new pizza.")); 
				
		 } 
	}
	
	///update
	
	@RequestMapping(method = RequestMethod.PUT, path = "/Pizza/{PizzaId}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
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
	
	
 @RequestMapping(method = RequestMethod.DELETE, path = "/Pizza/{PizzaId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> removePizzaWithID(@PathVariable Integer PizzaId){
	 
	 if(PizzaRepository.findOne(PizzaId) != null)
	 {
		 PizzaRepository.delete(PizzaId);
		 
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
				 
				 //(PizzaRepository.delete(PizzaId));
	 }
	 else {
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Pizza not found")); 
  }
	 }
	
	
	
////////get pizza with PizzaId
	
	
	 @RequestMapping(method = RequestMethod.GET, path = "/Pizza/{PizzaId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> getPizzaWithID(@PathVariable Integer PizzaId){
		 
		 if(PizzaRepository.findOne(PizzaId) != null)
		 {
			 
			 return ResponseEntity.ok(PizzaRepository.findOne(PizzaId));
		 }
		 
		 else {
		 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza could not be found")); 
	  }
		 }
	    
	 
////////get all pizza
		
		
	 @RequestMapping(method = RequestMethod.GET, path = "/Pizza",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
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
	 @RequestMapping(method = RequestMethod.POST, path = "/Pizza/{pizzaId}/topping" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> addPizzaTopping( @RequestBody Topping topping,@PathVariable Integer pizzaId) {
			 
			 if(PizzaRepository.exists(pizzaId)) 
			 {
				 //topping.setPizzaId(pizzaId);
				 
			Pizza pizza=	PizzaRepository.findOne(pizzaId);
			
			ArrayList<Integer> ToppingIds= pizza.getToppingIds();
			
			ToppingIds.add(topping.getId());
			
			pizza.setToppingIds(ToppingIds);
			
			PizzaRepository.save(pizza);
		
				 
			
			 return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new Topping for pizza."));
			 }
			 else 
			 {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid input")); 
					
			 } 
		}
		
	 
	 
	 //getPizzaToppings
	 @RequestMapping(method = RequestMethod.GET, path = "/Pizza/{pizzaId}/topping" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
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
		
	 
	 /////find pizza by Id
	 
	//getPizzaToppings
		 @RequestMapping(method = RequestMethod.GET, path = "/Pizza/{pizzaId}/topping/{toppingId}" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
		    public ResponseEntity<?> getPizzaToppingById(@PathVariable Integer pizzaId,@PathVariable Integer toppingId) {
				 
				 if(PizzaRepository.exists(pizzaId)) 
				 {
					 Pizza pizza=	PizzaRepository.findOne(pizzaId);
					 
					 ArrayList<Integer> toppingObjects = new ArrayList<Integer>();
					 
					 toppingObjects = pizza.getToppingIds();
					 
					 if(toppingObjects.contains(toppingId))
					 {
						 
					Topping toppingObj	 = ToppingRepository.findOne(toppingId);
						
					return ResponseEntity.status(HttpStatus.CREATED).body(toppingObj);
					 }
					 
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Invalid ID(s) supplied."));
					 
				 }
				 else 
				 {
					 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Pizza or Topping not found.")); 
						
				 } 
			}
	 
}
