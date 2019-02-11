/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dan's Laptop
 */
public interface VendingMachineDao {
    
    public void vendItem(Item item);
    
    public List<Item> getAllItems();
    
    public Item getItemById(int itemId);
}
