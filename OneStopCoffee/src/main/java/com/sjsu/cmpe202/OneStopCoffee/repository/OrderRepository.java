package com.sjsu.cmpe202.OneStopCoffee.repository;

import com.sjsu.cmpe202.OneStopCoffee.model.ManageOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends MongoRepository<ManageOrder, String> {

    //ManageOrder findItemPrice(@Param("ItemName") ManageOrder items);

}
