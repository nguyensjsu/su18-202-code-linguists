package com.sjsu.cmpe202.OneStopCoffee.service;


import com.sjsu.cmpe202.OneStopCoffee.model.ManageOrder;
import com.sjsu.cmpe202.OneStopCoffee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManageOrdersService {

    @Autowired
    private OrderRepository orderRepository;
    private String id;
    private ManageOrder order;
    private Double bill;

    public ManageOrder addItems(Map<String, Double> items){ return orderRepository.save( new ManageOrder(items));}

    public List<ManageOrder> displayOrders(){ return orderRepository.findAll();}

    public ManageOrder findItemByPrice(ManageOrder items){
        return orderRepository.findItemPrice(items);}

    public Double calculateTotalBill() {
        for (Map.Entry<String, Double> entry : order.getItems().entrySet()) {
            bill += entry.getValue();
        }
        return bill;
    }
}
