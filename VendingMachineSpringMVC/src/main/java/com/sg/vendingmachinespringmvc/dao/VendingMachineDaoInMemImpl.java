/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Dan's Laptop
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao{
    
    Map<Integer, Item> items;
    
    public VendingMachineDaoInMemImpl(){
        items = new HashMap<>();
        items.put(1, new Item(1,"Snickers",new BigDecimal("1.85"),1));
        items.put(2, new Item(2,"M & Ms",new BigDecimal("1.50"),9));
        items.put(3, new Item(3,"Pringles",new BigDecimal("2.10"),2));
        items.put(4, new Item(4,"Reese's",new BigDecimal("1.85"),5));
        items.put(5, new Item(5,"Pretzels",new BigDecimal("1.25"),4));
        items.put(6, new Item(6,"Twinkies",new BigDecimal("1.95"),0));
        items.put(7, new Item(7,"Doritos",new BigDecimal("1.75"),3));
        items.put(8, new Item(8,"Almond Joy",new BigDecimal("1.85"),11));
        items.put(9, new Item(9,"Trident",new BigDecimal("1.95"),1));
        
    }

    @Override
    public void vendItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems(){
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItemById(int itemId){
        return items.get(itemId);
    }
    

    
}
