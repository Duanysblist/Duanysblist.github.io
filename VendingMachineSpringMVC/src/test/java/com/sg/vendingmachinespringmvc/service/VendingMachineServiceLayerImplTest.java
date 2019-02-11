/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDaoInMemImpl;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dan's Laptop
 */
public class VendingMachineServiceLayerImplTest {
    
    VendingMachineDaoInMemImpl dao;
    VendingMachineServiceLayer service;
    private BigDecimal balance;
    private int itemChoice;
    private Change change;
    private String textMessage;
    
    public VendingMachineServiceLayerImplTest() {
        this.dao = dao;
        balance = new BigDecimal ("0.00");
        itemChoice = 0;
        change = null;
        textMessage = null;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("vendingMachineServiceLayer", VendingMachineServiceLayer.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAllItems(){
        VendingMachineDao something = new VendingMachineDaoInMemImpl();
        List<Item> result = something.getAllItems();
        int length = result.size();
        assertEquals(9, length);
    }
    
    @Test
    public void testGetItemById(){
        VendingMachineDao something = new VendingMachineDaoInMemImpl();
        Item currentItem = something.getItemById(1);
        assertTrue(currentItem != null);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
