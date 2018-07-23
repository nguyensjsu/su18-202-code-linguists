package com.sjsu.cmpe202.OneStopCoffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Card {

    @Id private String id;
    private String cardNumber;
    private String cardCvv;
    private String balance;
    private String username;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Card(String cardNumber, String cardCvv) {
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.balance = "0.00";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber(String id) {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
}
