/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author acetip
 */
public interface ProductDao {
    
    Product getProduct(String name) throws FlooringMasteryPersistenceException;
    
    List<Product> getAllProducts();
}
