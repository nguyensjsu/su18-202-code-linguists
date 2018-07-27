package com.sjsu.cmpe202.OneStopCoffee.service;


import com.sjsu.cmpe202.OneStopCoffee.model.ManageOrder;
import com.sjsu.cmpe202.OneStopCoffee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManageOrdersService {

    @Autowired
    private OrderRepository orderRepository;
    private String id;
    private Double bill = 0.00;

    public void addItems(Map<String, Double> items, Double total){
        System.out.println("In --- add Items ");
        orderRepository.save( new ManageOrder(items, total));
    }
}
