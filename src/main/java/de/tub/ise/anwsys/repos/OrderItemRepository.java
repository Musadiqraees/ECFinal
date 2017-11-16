package de.tub.ise.anwsys.repos;

import org.springframework.data.repository.CrudRepository;

import de.tub.ise.anwsys.models.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>
{

}
