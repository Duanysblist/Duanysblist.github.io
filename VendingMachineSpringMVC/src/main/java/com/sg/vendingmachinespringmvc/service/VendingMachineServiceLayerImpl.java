/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Dan's Laptop
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    VendingMachineDao dao;
    private BigDecimal balance;
    private int itemChoice;
    private Change change;
    private String textMessage;
    
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
        balance = new BigDecimal ("0.00");
        itemChoice = 0;
        change = null;
        textMessage = null;
    }
    
    private void validateItemInventory(Item item) throws NoItemInventoryException{
       if(item.getItemInventory() == 0){
   
            throw new NoItemInventoryException("SOLD OUT!!!");

       }
    }
    
    private void validateSufficientFunds(Item item, BigDecimal userMoneySelection) throws InsufficientFundsException{
        BigDecimal value = item.getItemCost();
        if(value.compareTo(userMoneySelection) > 0 ){
            BigDecimal whatTheyOwe = value.subtract(balance);
            throw new InsufficientFundsException("Please deposit : $" + whatTheyOwe);
            
        }
    }
    
    private void validateId(Item item) throws VendingMachineInvalidIdException{
            if(item == null || itemChoice == 0 || item.getItemId() == 0){
            throw new VendingMachineInvalidIdException("No such item.");
            }
       }
    
    @Override
    public void vendItem(){
        try {
       Item item = dao.getItemById(itemChoice);
       validateId(item);
       BigDecimal cost = item.getItemCost();
       validateItemInventory(item);
       validateSufficientFunds(item, balance);
       BigDecimal changeReturned = balance.subtract(cost);
       Change daChangeReturned = new Change(changeReturned);
       change = daChangeReturned;
       balance = new BigDecimal("0.00");
       int inventoryChange = item.getItemInventory();
       inventoryChange--;
       item.setItemInventory(inventoryChange);
       dao.vendItem(item);
       textMessage = "Thank You!";
        } catch (NoItemInventoryException | InsufficientFundsException | VendingMachineInvalidIdException ex) {
            textMessage = ex.getMessage();
        }
          

    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItemById(int itemId) throws FileNotFoundException, NoItemInventoryException {
        Item item = dao.getItemById(itemId);
        validateItemInventory(item);
        return dao.getItemById(item.getItemId());
    }

    @Override
    public void addMoney(String amount) { //created a swtich statement to determine the new value of balance dependent upon which button the user is pressing
        switch (amount) {
            case "dollar": //these are the corresponding names to the buttons found in the jsp
                balance = balance.add(new BigDecimal("1.00"));
                break;
            case "quarter": //they use the URL Mapping to determine what the URL is depending on the name of the button
                balance = balance.add(new BigDecimal("0.25"));
                break;
            case "dime": //you can find these in the jsp
                balance = balance.add(new BigDecimal("0.10"));
                break;
            case "nickel": //addmoney/nickel
                balance = balance.add(new BigDecimal("0.05"));
                break;
            default:
}
    }
    
    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int getItemChoice() {
        return itemChoice;
    }

    @Override
    public void setItemChoice(int itemChoice) {
        this.itemChoice = itemChoice;
    }

    @Override
    public Change getChange() {
        return change;
    }

    @Override
    public void setChange(Change change) {
        this.change = change;
    }

    @Override
    public String getTextMessage() {
        return textMessage;
    }

    @Override
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public void returnChange() { //resetting all values to nothing once change is returned
        Change theirChange = new Change(balance);
        change = theirChange;
        balance = new BigDecimal("0.00");
        itemChoice = 0;
        textMessage = null;
        
    }
    
}
