package com.sjsu.cmpe202.OneStopCoffee.controller;

import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.service.CardService;
import com.sjsu.cmpe202.OneStopCoffee.service.ManageOrdersService;
import com.sjsu.cmpe202.OneStopCoffee.service.PaymentService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "/addCard/", method = RequestMethod.POST) // ADD CARD REST API
    public ResponseEntity<Void> postCard(@RequestBody String newCard) throws org.json.JSONException  {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject obj;
        obj = new JSONObject(newCard);

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


    @RequestMapping(value = "/deductMoney/", method = RequestMethod.POST) // DEDUCT MONEY FROM CARD REST API
    public ResponseEntity<Void> deductMoney(@RequestBody String inputD) throws org.json.JSONException {
        JSONObject obj;
        obj = new JSONObject(inputD);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String cardID = obj.getString("cardID");
        double amt = obj.getDouble("amt");

        System.out.println("deducting money");
        cardService.deductMoney(cardID, amt);

        return new ResponseEntity<Void>(headers, CREATED);
    }

    @RequestMapping(value = "/addMoney/", method = RequestMethod.POST) // ADD MONEY TO CARD REST API
    public ResponseEntity<Void> addMoney(@RequestBody String inputD) throws org.json.JSONException {

        JSONObject obj;
        obj = new JSONObject(inputD);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String cardID = obj.getString("cardID");
        double amt = obj.getDouble("amt");

        System.out.println("adding money");
        cardService.addMoney(cardID, amt);

        return new ResponseEntity<Void>(headers, CREATED);
    }

    @RequestMapping (value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<Void> addOrder(@RequestBody HashMap<String, Double> items) throws org.json.JSONException{

        Set<String> keys = items.keySet();
        Iterator itr = keys.iterator();

        Double total = 0.0;

        while(itr.hasNext()) {
            String key = (String)itr.next();
            Double price = (Double) items.get(key);
            total += price;
        }

        orderService.addItems(items, total);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/makePayment", method = RequestMethod.POST)
    public ResponseEntity<Void> postPayment(@RequestBody String payRequest) throws JSONException {
        System.out.println("Creating Payment");
        JSONObject obj = new JSONObject(payRequest);
        String cardID = obj.getString("cardID");
        String amt = obj.getString("amount");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
      
        if(paymentService.makePayment(cardID, amt)){ 	 
        	 return new ResponseEntity<>(headers, HttpStatus.CREATED);

        }else{
        	return new ResponseEntity<Void>(headers,HttpStatus.NOT_FOUND);
        }
    }
}
