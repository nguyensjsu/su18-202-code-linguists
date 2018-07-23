package com.sjsu.cmpe202.OneStopCoffee.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.model.Payment;
import com.sjsu.cmpe202.OneStopCoffee.repository.PaymentRepository;;

public class PaymentService {
	 @Autowired
	    private PaymentRepository paymentRepository;
	    private String id;
	    private Card card ;
	  

	    public Boolean makePayment(String cardNumber, String cardCvv , String amount ){
	       
	    	if(Double.parseDouble(card.getBalance(cardNumber))>=Double.parseDouble(amount) ){	
	    	paymentRepository.save(new Payment(cardNumber,cardCvv,amount));
	    	return true;}
	    	else {
	    		return false;
	    	}

}
}
