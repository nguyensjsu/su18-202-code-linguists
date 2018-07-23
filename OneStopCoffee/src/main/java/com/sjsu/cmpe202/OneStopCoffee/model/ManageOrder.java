package com.sjsu.cmpe202.OneStopCoffee.model;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class ManageOrder {
    @Id
    private String id;
    private Map<String,Double> items;
    private Double bill;

    public ManageOrder(Map<String, Double> items){
        this.items = items;
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
