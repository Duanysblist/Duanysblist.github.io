/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author acetip
 */
public interface FlooringMasteryServiceLayer {
    
    Order addOrder(String customerName, String productName, BigDecimal Area, String stateAbv)throws FlooringMasteryPersistenceException;
    
    Order viewOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException;
    
    List<Order> viewOrders(String date) throws FlooringMasteryPersistenceException;
    
    List<Product> viewProducts();
    
    Product getProduct(String productName) throws FlooringMasteryPersistenceException;
    
    List<StateTax> viewTaxes();
    
    StateTax getStateTax(String stateAbv)throws FlooringMasteryPersistenceException;
    
    Order removeOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException;
    
    void editOrder(String date, Order order, Order editedOrder) throws FlooringMasteryPersistenceException;
    
    void saveWork()throws FlooringMasteryPersistenceException;

    public void validateArea(String area) throws MinimumAreaException;

    public void validateState(String stateAbv) throws FlooringMasteryPersistenceException, StateValidationException;

    public void validateDate(String date) throws OrderDateValidationException;

    public void validateOrderNumber(String date, int orderNumber) throws OrderNumberValidationException, FlooringMasteryPersistenceException;
    
    public void validateProduct(String productName) throws ProductValidationException, FlooringMasteryPersistenceException;
    
    public String validateNoDoubleColon(String customerName);

    public void saveToHashMap(Order order) throws FlooringMasteryPersistenceException;
    
    public void loadMap() throws FlooringMasteryPersistenceException;
    
//    void correctMyMistake(Order order) throws FlooringMasteryPersistenceException;
}
