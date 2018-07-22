package com.sjsu.cmpe202.OneStopCoffee.service;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    private String id;

    public Card addCard(String cardNumber, String cardCvv){
        return cardRepository.save(new Card(cardNumber,cardCvv));
    }

    public List<Card> displayALL(){
        return cardRepository.findAll();
    }

    public Card findCardByNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }
}
