package com.xbrain.testproject.repositories;

import com.xbrain.testproject.models.entities.OrderModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Long> {
}
