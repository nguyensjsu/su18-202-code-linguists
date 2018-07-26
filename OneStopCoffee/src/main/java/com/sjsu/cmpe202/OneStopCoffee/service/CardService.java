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

    private double money;

    public Card addCard(String cardNumber, String cardCvv){
        if(cardNumber.length()==9)
            return cardRepository.save(new Card(cardNumber,cardCvv));
        return null;
    }

    public List<Card> displayALL(){
        return cardRepository.findAll();
    }

    public Card addMoney(String cardNumber, String amount){
         Card c= cardRepository.findByCardNumber(cardNumber);
        double newBal = Double.parseDouble(c.getBalance(cardNumber)) + Double.parseDouble(amount);
         String newAmount = Double.toString(newBal);
         c.setBalance(newAmount);
         return cardRepository.save(c);
    }

    public Card findCardByNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }
}
