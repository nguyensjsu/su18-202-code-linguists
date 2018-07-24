package com.sjsu.cmpe202.OneStopCoffee.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = Payment.class)
public class Payment {
@Id
private String id;
private String amount ;
private String cardNo;
private String cvv;

@JsonCreator
public Payment( @JsonProperty(value = "amount", required = true)String amount ,@JsonProperty(value = "cardNo", required = true)String cardNo, @JsonProperty(value = "cvv", required = true)String  cvv ) {
	this.cvv =cvv;
    this.amount = amount ;
    this.cardNo = cardNo;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getAmount() {
	return amount;
}

public void setAmount(String amount) {
	this.amount = amount;
}

public String getCardNo() {
	return cardNo;
}

public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}

public String getCvv() {
	return cvv;
}

public void setCvv(String cvv) {
	this.cvv = cvv;
}


}
