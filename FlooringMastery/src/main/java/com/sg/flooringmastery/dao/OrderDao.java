/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.OrderDateValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Dan's Laptop
 */
public interface OrderDao {
    
    Order viewOrder(String date, int orderNumber)throws FlooringMasteryPersistenceException;
    
    List<Order> viewOrders(String date)throws FlooringMasteryPersistenceException;
    
    void editOrder(String date, Order editedOrder);
    
    Order removeOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException;
    
    Order addOrder(Order order)throws FlooringMasteryPersistenceException;
    
    void saveOrders() throws FlooringMasteryPersistenceException;
    
    String createFile();
    
    String getDate();
    
    Order saveToHashmap(Order order) throws FlooringMasteryPersistenceException;
    
    void loadOrderList(String date) throws FlooringMasteryPersistenceException;
    
    void loadMap() throws FlooringMasteryPersistenceException;
    
    boolean orderNumberExists(String date, int orderNumber) throws FlooringMasteryPersistenceException;
    
    boolean dateExists(String date) throws OrderDateValidationException;
    
    
//    int correctMyMistake(Order order) throws FlooringMasteryPersistenceException;
}
