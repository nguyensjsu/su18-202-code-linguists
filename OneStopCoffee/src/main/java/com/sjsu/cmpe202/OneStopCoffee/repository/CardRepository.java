package com.sjsu.cmpe202.OneStopCoffee.repository;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cards", path = "cards")
public interface CardRepository extends MongoRepository<Card, String> {

    Card findByCardNumber(@Param("CardNumber") String cardNumber);


}