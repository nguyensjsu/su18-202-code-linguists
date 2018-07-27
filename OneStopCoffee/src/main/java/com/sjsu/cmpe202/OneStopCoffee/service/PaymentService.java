package com.sjsu.cmpe202.OneStopCoffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.model.Payment;
import com.sjsu.cmpe202.OneStopCoffee.repository.CardRepository;
import com.sjsu.cmpe202.OneStopCoffee.repository.PaymentRepository;
import java.util.Optional;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private CardRepository cardRepository;
	private CardService cardService;
	private String id;

	public Boolean makePayment(String cardID, String amount ){
		//Find card id
	       Optional<Card> result = cardRepository.findById(cardID);
	       if(result.isPresent()){
	    	  Card c = result.get();
	    	  //if balance is sufficient , accept payment 
	    	  if(c.getBalance() >= Double.parseDouble(amount)){	
	    		  paymentRepository.save(new Payment(cardID, amount));
	    		  return true;
	    	  }
	       }
	       return false;
	}
}
