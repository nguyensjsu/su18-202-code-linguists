package com.sjsu.cmpe202.OneStopCoffee.controller;

import java.util.*;

import com.sjsu.cmpe202.OneStopCoffee.repository.OrderRepository;
import org.json.*;
import org.json.JSONException;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.model.ManageOrder;
import com.sjsu.cmpe202.OneStopCoffee.service.CardService;
import com.sjsu.cmpe202.OneStopCoffee.service.PaymentService;
import com.sjsu.cmpe202.OneStopCoffee.service.ManageOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class OneStopCoffeeController {
    @Autowired
    CardService cardService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ManageOrdersService orderService;

    @RequestMapping(value = "/cards/", method = RequestMethod.GET)
    public ResponseEntity<List<Card>> listAllUsers() {
        List<Card> cards = cardService.displayALL();
        if(cards.isEmpty()){
            return new ResponseEntity<List<Card>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = "/cards/{cardNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> getUser(@PathVariable("cardNumber") String cardNumber) {
        System.out.println("Fetching User with id " + cardNumber);
        Card card = cardService.findCardByNumber(cardNumber);
        if (card == null) {
            System.out.println("User with id " + cardNumber + " not found");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @RequestMapping(value = "/addCard/", method = RequestMethod.POST)
    public ResponseEntity<Void> postCard(@RequestBody String newCard) throws JSONException {
        JSONObject obj;
        obj = new JSONObject(newCard);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String cardNum = obj.getString("cardNum");
        String cardCVV = obj.getString("cardCVV");
        double bal = obj.getDouble("balance");
        Card card = cardService.addCard(cardNum, cardCVV, bal);
        if ((card.getId())!=null){
            System.out.println(" Card Created ");
            return new ResponseEntity<Void>(headers, CREATED);
        }
        else return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/deductMoney/", method = RequestMethod.POST)
    public ResponseEntity<Void> deductMoney(@RequestBody String inputD) throws JSONException {
        JSONObject obj;
        obj = new JSONObject(inputD);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String cardID = obj.getString("cardID");
        String amt = obj.getString("amt");
        System.out.println("deducting money");

        cardService.deductMoney(cardID, Double.parseDouble(amt));

        return new ResponseEntity<Void>(headers, CREATED);
    }

    @RequestMapping(value = "/addMoney/", method = RequestMethod.POST)
    public ResponseEntity<Void> addMoney(@RequestBody String inputD) throws JSONException {
        JSONObject obj;
        obj = new JSONObject(inputD);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String cardID = obj.getString("cardID");
        String amt = obj.getString("amt");
        System.out.println("adding money");

        cardService.addMoney(cardID, Double.parseDouble(amt));

        return new ResponseEntity<Void>(headers, CREATED);
    }

    @RequestMapping(value = "/makePayment", method = RequestMethod.POST)
    public ResponseEntity<Void> postPayment(@RequestBody String cardNum, String cardCVV, String amount,  UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Payment");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(paymentService.makePayment(cardNum, cardCVV, amount)){
        	
        	 
        	 return new ResponseEntity<>(headers, HttpStatus.CREATED);

        }else{

        return new ResponseEntity<Void>(headers,HttpStatus.NOT_FOUND);
        }
    }
}
