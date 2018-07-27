package com.sjsu.cmpe202.OneStopCoffee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document
public class ManageOrder {
    @Id private String id;
    private Map<String,Double> items;
    //private String items;
    private Double bill;

    public ManageOrder(Map<String, Double> items, Double total){
        this.items = items;
        this.bill = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String,Double> getItems(){ return items; }

    public void setItems(Map<String,Double> items){ this.items = items; }

}
