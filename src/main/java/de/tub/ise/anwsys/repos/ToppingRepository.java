package de.tub.ise.anwsys.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.models.User;

public interface ToppingRepository extends CrudRepository<Topping, Integer>
{

	 
	 

}
