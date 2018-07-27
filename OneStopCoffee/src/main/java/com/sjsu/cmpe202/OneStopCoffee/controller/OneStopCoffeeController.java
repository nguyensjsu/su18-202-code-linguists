package com.sjsu.cmpe202.OneStopCoffee.controller;

import java.util.List;
import java.util.Map;

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
    PaymentService paymentService;
    ManageOrdersService orderService;
    ManageOrder order;


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

    @RequestMapping(value = "/orders/", method = RequestMethod.GET)
    public ResponseEntity<List<ManageOrder>> listAllOrders() {
        ManageOrder order = orderService.addItems()
        List<ManageOrder> orders = orderService.addItems();
        if(orders.isEmpty()){
            return new ResponseEntity<List<ManageOrder>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ManageOrder>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBill", method = RequestMethod.GET)
    public ResponseEntity<Double> getTotalBill(@RequestBody ManageOrder order){
        Double bill = orderService.calculateTotalBill(order);
        if(bill==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @RequestMapping (value = "/addOrder", method = RequestMethod.POST)
    public ResponseEntity<Void> getOrder(@RequestBody Map<String, Double> items, UriComponentsBuilder ucBuilder){
        System.out.println("Please add items and their price");

        orderService.addItems(items);

    }

    @RequestMapping(value = "/addcard/", method = RequestMethod.POST)
    public ResponseEntity<Void> postCard(@RequestBody String cardNum, String cardCVV,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Card");


        cardService.addCard(cardNum,cardCVV);
        Card c = cardService.findCardByNumber(cardNum);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cards/{cardNumber}").buildAndExpand(cardService.findCardByNumber(cardNum)).toUri());


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
