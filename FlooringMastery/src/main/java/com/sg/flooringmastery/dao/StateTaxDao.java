/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import java.util.List;

/**
 *
 * @author Dan's Laptop
 */
public interface StateTaxDao {
    StateTax getStateByName(String stateName);
    
    String getStateAbv(String stateName);
    
    List<StateTax> getAllTaxes();
    
    StateTax getStateTax(String stateAbv)throws FlooringMasteryPersistenceException;
}
