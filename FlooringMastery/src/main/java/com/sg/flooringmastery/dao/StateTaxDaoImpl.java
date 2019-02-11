/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
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
 * @author Dan's Laptop
 */
public class StateTaxDaoImpl implements StateTaxDao{

    private Map<String, StateTax > taxes = new HashMap<>(); //puts the taxes in a hashmap where the key is the String stateAbv
    
    public String TAX_LIST = "Taxes.txt"; //assigns TAX_LIST to the file Taxes.txt
    public static final String DELIMITER = "::";
    
    @Override
    public StateTax getStateByName(String stateName) { //pretty sure I don't need these methods
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStateAbv(String stateName) { //^^^
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StateTax> getAllTaxes() { //Am not sure if I would ever need to list all the taxes in the file at once
         return new ArrayList<StateTax>(taxes.values());
    }

    @Override
    public StateTax getStateTax(String stateAbv) throws FlooringMasteryPersistenceException{ 
        loadTaxList(); //will load the file Taxes.txt and find the StateTax object by the stateAbv
        return taxes.get(stateAbv); //return the StateTax object found by it's StateAbv
    }
    
    private void loadTaxList() throws FlooringMasteryPersistenceException{
        Scanner scanner;
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_LIST))); //reading the Taxes.txt file(see above)
        }catch(FileNotFoundException e){
            throw new FlooringMasteryPersistenceException("Could not load item inventory into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            StateTax currentState = new StateTax(currentTokens[0], new BigDecimal(currentTokens[1]));
            
            
            taxes.put(currentState.getStateAbv(), currentState);
        }
        scanner.close();
    }
    

    
}
