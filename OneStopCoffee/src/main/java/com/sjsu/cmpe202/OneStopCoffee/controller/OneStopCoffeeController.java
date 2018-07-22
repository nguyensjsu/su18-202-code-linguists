package com.sjsu.cmpe202.OneStopCoffee.controller;

import java.util.List;
import com.sjsu.cmpe202.OneStopCoffee.model.Card;
import com.sjsu.cmpe202.OneStopCoffee.service.CardService;
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

public class OneStopCoffeeController {
    @Autowired
    CardService cardService;


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

    @RequestMapping(value = "/addcard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody String cardNum, String cardCVV,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Card");


        cardService.addCard(cardNum,cardCVV);
        Card c = cardService.findCardByNumber(cardNum);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cards/{cardNumber}").buildAndExpand(cardService.findCardByNumber(cardNum)).toUri());


        return new ResponseEntity<Void>(headers, CREATED);
    }
}