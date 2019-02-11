/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.OrderDateValidationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dan's Laptop
 */
public class OrderDaoStubImpl implements OrderDao{
    
    private Order onlyOrder;
    private List<Order> orderList = new ArrayList<>();
    private Map<String, Map<Integer, Order>>orderTestMap = new HashMap<>();
    private String date = "09302018";

    public OrderDaoStubImpl(Order onlyOrder) {
        onlyOrder = new Order(90, "Someone", "MI", new BigDecimal("5.75"), "Carpet", new BigDecimal("500"), new BigDecimal("2.25"), new BigDecimal("2.10"), new BigDecimal("1125.00"), new BigDecimal("1050.00"), new BigDecimal("125.06"), new BigDecimal("2300.06"));
        Map<Integer, Order> hm = orderTestMap.get(date);
        orderTestMap.put(date, hm);
        hm.put(onlyOrder.getOrderNumber(), onlyOrder);
    }

//    1::Jeremy::MI::5.75::Carpet::500::2.25::2.10::1125.00::1050.00::125.06::2300.06
    @Override
    public Order viewOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException {
        if(orderNumber == onlyOrder.getOrderNumber()){
            return onlyOrder;
        }else{
            return null;
        }
        
    }

    @Override
    public List<Order> viewOrders(String date) throws FlooringMasteryPersistenceException {
            return orderList;
    }

    @Override
    public void editOrder(String date, Order editedOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException {
    if(orderNumber == onlyOrder.getOrderNumber()){
            return onlyOrder;
        }else{
            return null;
        }
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        if(onlyOrder.getOrderNumber() == order.getOrderNumber()){
            return onlyOrder;
        }else{
            return null;
        }
    }

    @Override
    public void saveOrders() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String createFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order saveToHashmap(Order order) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrderList(String date) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadMap() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean orderNumberExists(String date, int orderNumber) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dateExists(String date) throws OrderDateValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
