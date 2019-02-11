/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.MinimumAreaException;
import com.sg.flooringmastery.service.OrderDateValidationException;
import com.sg.flooringmastery.service.OrderNumberValidationException;
import com.sg.flooringmastery.service.ProductValidationException;
import com.sg.flooringmastery.service.StateValidationException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author acetip
 */
public class FlooringMasteryController {
    
    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;
    
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void Run()throws FlooringMasteryPersistenceException{
        boolean keepGoing = true;
        int getSelection = 0;
        service.loadMap();

        while(keepGoing){
            getSelection = menuSelection();
            
            switch(getSelection){
                case 1:
                    viewOrders();
                    break;
                case 2:
                    viewOrder();
                    break;
                case 3:
                    addOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    saveCurrentWork();
                    keepGoing = false;
                    break;   
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
    
    public int menuSelection(){
       return view.printMainMenuAndGetSelection();
    }
    
    public void addOrder()throws FlooringMasteryPersistenceException{
        String customerName = view.getCustomerName(); //asking the user for neccessary information for an order (Name, Product, Area, State)
        customerName = service.validateNoDoubleColon(customerName);
        try{
        String productName = view.getProduct();
        service.validateProduct(productName);
        String area = view.getArea();
        service.validateArea(area);
        BigDecimal Area = new BigDecimal(area);
        String stateAbv = view.getState();
        service.validateState(stateAbv);//validating that the state exists before adding the order    
        Order order = service.addOrder(customerName, productName, Area, stateAbv); //Passing neccessary information to the service layer to add the order
        view.displayOrderInfo(order);
        boolean saveIt = view.wannaSave();
        if(saveIt){
            save(order); //saving it to a hashmap
        }
        }catch(ProductValidationException | MinimumAreaException | StateValidationException | NumberFormatException e){
            view.displayErrorMessage(e.getMessage());
        }
    }//WHEN PASSING IN AN ORDER TO ADD, I WANT TO PASS IN THE DATE AND THE ORDER OBJECT
    
    public void removeOrder()throws FlooringMasteryPersistenceException{
       try{
       String date = view.getUserDate(); //asking for Date and Order Number
       service.validateDate(date); //validating that an order exists for the chosen date
       int orderNumber = view.getOrderNumber();
       service.validateOrderNumber(date ,orderNumber); //validating that their order number exists on the specific date
       view.displayOrderInfo(service.viewOrder(date, orderNumber)); //displaying the order they are about to remove
       boolean save = view.wannaSave(); //asking if they would like to save their work
       if(save == true){
          service.removeOrder(date ,orderNumber);//sending the information to the service layer to find and remove that specific order
          view.displayOrderRemoved();
       }
       }catch(OrderNumberValidationException | OrderDateValidationException e){
           view.displayErrorMessage(e.getMessage());
       }
    }
    
    public void editOrder()throws FlooringMasteryPersistenceException{
        try{
        String date = view.getUserDate(); //getting the date for their order
        service.validateDate(date); //validating that there is an order for that date
        int orderNumber = view.getOrderNumber(); //getting the order number specified
        service.validateOrderNumber(date, orderNumber); //validating that there is an order number dependent upon the date chosen
        Order order = service.viewOrder(date, orderNumber); //look into this. Is the order being assigned to the viewOrder order? May have to just pass date and order number to service to find order, then go from there
        String editName = view.editCustomerName(order);
        String editProduct = view.editProduct(order);
        service.validateProduct(editProduct);
        String editArea = view.editArea(order);
        service.validateArea(editArea);
        BigDecimal Area = new BigDecimal(editArea);
        String editState = view.editState(order);
        service.validateState(editState);
        boolean save = view.wannaSave();
        if(save == true){
        Order editedOrder = service.addOrder(editName, editProduct, Area, editState); //passing the order to the service layer for it to be edited(How does it edit within the service layer?)***
        editedOrder.setOrderNumber(order.getOrderNumber());
        service.editOrder(date, order, editedOrder); 
        }
        }catch(OrderNumberValidationException | OrderDateValidationException | ProductValidationException | MinimumAreaException | StateValidationException e){
            view.displayErrorMessage(e.getMessage());
        }

    }
    
    public void viewOrder()throws FlooringMasteryPersistenceException{
        try{
        String date = view.getUserDate(); //getting the date from the user
        service.validateDate(date); //validating that the date exists within the files
        int orderNumber = view.getOrderNumber(); //getting the order number from the user
        service.validateOrderNumber(date, orderNumber); //validating that the order exists for that date.(Since it's finding the order number specified by date, do we have to pass in the date as well in order to validate it?)***
        Order order = service.viewOrder(date, orderNumber);
        view.displayOrderInfo(order);
        }catch(OrderNumberValidationException | OrderDateValidationException e){
            view.displayErrorMessage(e.getMessage());
        }
        //sends the information to the service layer to view the order
    }
    
    public void viewOrders() throws FlooringMasteryPersistenceException{
        try{
        String date = view.getUserDate(); //getting the date from the user
        service.validateDate(date); //validating that a file exists for the date specified
        List<Order> orderList = service.viewOrders(date); //having it return a list of orders for that date (Should I have the return type as a list then?)***
        view.displayOrders(orderList);
        }catch(OrderDateValidationException e){
            view.displayErrorMessage(e.getMessage());
        }
        
    }
    
    public void viewProduct() throws FlooringMasteryPersistenceException{ //getting the product and it's values from the file by passing it to the service layer which passes it to the product DAO
        String productName = view.getProduct();
        service.getProduct(productName);
    }
    
    public void viewProducts(){
        service.viewProducts();
    }
    
    public void getStateTax()throws FlooringMasteryPersistenceException{
        String stateAbv = view.getState();
        service.getStateTax(stateAbv);
    }
    
    public void viewTaxes(){
        service.viewTaxes();
    }
    
    public void exitMessage(){
        view.displayExitMessage();
    }
    
    public void unknownCommand(){
        view.displayUnknownCommandMessage();
    }
    
    public void save(Order order) throws FlooringMasteryPersistenceException{
           service.saveToHashMap(order);
    }


    private void saveCurrentWork() throws FlooringMasteryPersistenceException{
        boolean save = view.wannaSave();
        if(save == true){
            service.saveWork();
            view.workSaved();
        }else{
            view.workNotSave();
        }
                //if wannasave save, else, dont save
    }
}