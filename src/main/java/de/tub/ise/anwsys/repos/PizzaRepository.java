package de.tub.ise.anwsys.repos;

import org.springframework.data.repository.CrudRepository;

import de.tub.ise.anwsys.models.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

}
