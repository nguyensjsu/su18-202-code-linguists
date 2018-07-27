package com.sjsu.cmpe202.OneStopCoffee.service;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    private String id;

    private double money;

    public Card addCard(String cardNumber, String cardCvv){
        if(cardNumber.length()==9 && cardCvv.length()==3) {
            System.out.println("card service addCard method called--- if");
            return cardRepository.save(new Card("1", cardNumber, cardCvv));
        }
        else {
            System.out.println("cardNumber-->"+cardNumber);
            System.out.println("cardCVV-->"+cardCvv);
            System.out.println("no. of digits in cardNumber-->"+cardNumber.length());
            System.out.println("card service addCard method called --- else");
            return null;
        }
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

    public Double deductMoney(String cardID){
       

        return 0.00;
    }


    public Card findCardByNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }
}
