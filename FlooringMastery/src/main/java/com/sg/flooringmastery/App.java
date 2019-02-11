/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.MinimumAreaException;
import com.sg.flooringmastery.service.StateValidationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author acetip
 */
public class App {
    public static void main(String[] args) throws FlooringMasteryPersistenceException,  MinimumAreaException, StateValidationException{
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        controller.Run();
    }
}
