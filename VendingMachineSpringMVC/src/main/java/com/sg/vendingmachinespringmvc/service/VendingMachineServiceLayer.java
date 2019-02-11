/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dan's Laptop
 */
public interface VendingMachineServiceLayer {
    
    public void vendItem();
    
    public List<Item> getAllItems();
    
    public Item getItemById(int itemId) throws FileNotFoundException, NoItemInventoryException;
    
    public void addMoney(String amount);

    public BigDecimal getBalance();
    
    public void returnChange();
    
    String getTextMessage();
    
    Change getChange();
    
    int getItemChoice();
    
    void setBalance(BigDecimal balance);
    
    void setTextMessage(String textMessage);
    
    void setChange(Change change);
    
    void setItemChoice(int itemChoice);
    
}
