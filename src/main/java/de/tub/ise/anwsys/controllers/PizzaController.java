package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Pizza.Size;
import de.tub.ise.anwsys.repos.PizzaRepository;
import de.tub.ise.anwsys.repos.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;

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

import antlr.collections.List;

@RestController
public class PizzaController {
	 
	
	 private PizzaRepository PizzaRepository;
	 
	 @Autowired
	    public PizzaController(PizzaRepository PizzaRepository) {
	        this.PizzaRepository = PizzaRepository;
	    }
	
	public PizzaController() {
		// TODO Auto-generated constructor stub
	}
	 
	////////Added new pizza
	
	@RequestMapping(method = RequestMethod.POST, path = "/Pizza" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> AddPizza( @RequestBody Pizza pizza) {
		 
		 if(PizzaRepository.exists(pizza.getId())) 
		 {
		
		 return ResponseEntity.ok(String.format("pizza with this ID  \"%s!\" already exist", pizza.getId() ));  
	 }
		 else {
			 
			 PizzaRepository.save(pizza);
			 
			 return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Created new pizza.")); 
				
		 } 
	}
	

	
////////get pizza with PizzaId
	
	
 @RequestMapping(method = RequestMethod.PUT, path = "/Pizza/{PizzaId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
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
		 	return ResponseEntity.ok(String.format("Pizza Added check detail %s!", 123)); 
	  }
		 }
	    
	 
////////get all pizza
		
		
	 @RequestMapping(method = RequestMethod.GET, path = "/Pizza",consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<?> getAllpizza(){
		 
		 ArrayList<Pizza> PizzaObjects = new ArrayList<Pizza>();
		 PizzaObjects =   (ArrayList<Pizza>) PizzaRepository.findAll();
		 
		 
		 return ResponseEntity.ok(PizzaObjects);  
	 }
 	
	
	 @RequestMapping(method = RequestMethod.POST, path = "/AddPizza")
	    public ResponseEntity<?> AddPizza(@RequestParam(value = "name", defaultValue = "AnwSys Student") String name, @RequestParam (value = "Id", defaultValue = "23" ) Integer Id
	            , @RequestParam (value = "size", defaultValue = "12" ) Size size
	            , @RequestParam(value = "price", defaultValue = "23" ) Integer price) {
	    	
		 
		 if(PizzaRepository.findOne(Id) != null) 
		 {
			
			 return ResponseEntity.ok(String.format("pizza with this ID  %s! already exist", Id ));  
		 }
		 else
		 {
		 
		 Pizza tempPizza = new Pizza( name, Id , price,  size ) ;
		 
		   PizzaRepository.save(tempPizza);
	    	
	    	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(String.format("Pizza Added check detail %s!", name+price+size)); 
	    
		  }
		  
	    	
	    }
	
}
