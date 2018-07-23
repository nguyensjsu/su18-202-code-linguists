package com.sjsu.cmpe202.OneStopCoffee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sjsu.cmpe202.OneStopCoffee.model.Payment;


@RepositoryRestResource(collectionResourceRel = "payment", path = "payment")
public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByCardNo(@Param("CardNumber") String cardNumber);


}
