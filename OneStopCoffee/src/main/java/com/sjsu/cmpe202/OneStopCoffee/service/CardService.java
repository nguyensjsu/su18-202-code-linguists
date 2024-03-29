package com.sjsu.cmpe202.OneStopCoffee.service;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.model.ManageOrder;
import com.sjsu.cmpe202.OneStopCoffee.repository.CardRepository;
import com.sjsu.cmpe202.OneStopCoffee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    private String id;

    @Autowired
    private OrderRepository orderRepository;

    private double money;

    public Card addCard(String cardNumber, String cardCvv,double balance){
        if(cardNumber.length()==9 && cardCvv.length()==3) {
            System.out.println("card service addCard method called--- if");
            String id = java.util.UUID.randomUUID().toString();
            return cardRepository.save(new Card(id, cardNumber, cardCvv,balance));
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

    public double addMoney(String cardID, double amount){

        Card c = getCardID(cardID);
        double newBal = c.getBalance() + amount;
        c.setBalance(newBal);
        cardRepository.save(c);
        return newBal;
    }

    public double deductMoney(String cardID, double amount){

        Card c = getCardID(cardID);
        double newBal = c.getBalance() - amount;
        c.setBalance(newBal);
        cardRepository.save(c);
        return newBal;
    }

    public Card getCardID(String cardID){
        Optional<Card> result = cardRepository.findById(cardID);
        if(result.isPresent()) {
            Card c = result.get();
            return c;
        }
        else
            return null;
    }
    public Card findCardByNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }


}
