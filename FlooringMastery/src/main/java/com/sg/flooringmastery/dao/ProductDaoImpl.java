/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author acetip
 */
public class ProductDaoImpl implements ProductDao{
    
    private Map<String, Product > products = new HashMap<>(); //puts Product objects into a HashMap with their key as the String productName

    public String PRODUCT_LIST = "Products.txt"; //assigns PRODUCT_LIST to the Products.txt file
    public static final String DELIMITER = "::";
    
    @Override
    public Product getProduct(String name) throws FlooringMasteryPersistenceException{
       loadProductList(); //will load the Products.txt file
       return products.get(name); //will get the Product object by the String productName passed into it
    }

    @Override
    public List<Product> getAllProducts() { //not sure that I need this
         return new ArrayList<Product>(products.values());

    }
    
    private void loadProductList() throws FlooringMasteryPersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_LIST))); //reading the Products.txt file(see above)
        }catch(FileNotFoundException e){
            throw new FlooringMasteryPersistenceException("Could not load item inventory into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product(currentTokens[0], new BigDecimal(currentTokens[1]), new BigDecimal(currentTokens[2]));
            
            
            products.put(currentProduct.getName(), currentProduct);
        }
        scanner.close();
    }
    
    
    
}
