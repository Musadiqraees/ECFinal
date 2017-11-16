package de.tub.ise.anwsys.repos;

import org.springframework.data.repository.CrudRepository;

import de.tub.ise.anwsys.models.OrderPizza;

public interface OrderRepository extends CrudRepository<OrderPizza, Integer> {

}
