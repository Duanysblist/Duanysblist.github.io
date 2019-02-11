/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author acetip
 */
public class FlooringMasteryView {

    private UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }
    
    public int printMainMenuAndGetSelection(){
        io.print("Flooring Mastery Order Management");
        io.print("1. Display Orders");
        io.print("2. Display Order");
        io.print("3. Add new Order");
        io.print("4. Edit Order");
        io.print("5. Delete Order");
        io.print("6. Save Current Work");
        io.print("7. Exit");
        int selection = io.readInt("Please make a selection from the above choices", 1, 7);
        return selection;
    }
    
    public String getProduct(){
        String product = io.readString("Please enter the product you would like to build from: "); //reads the String the user puts in and will find the product in the Products.txt file
        return product; //ADD A VALIDATION METHOD IN SERVICE TO SEE IF THE PRODUCT EXISTS IN THE PRODUCTS FILE
    }
    
    public String getState(){
        String state = io.readString("Please enter the state abreviation you are looking for:"); //ADD SOMETHING THAT WILL CHECK IF THE STATE ABREVIATIONS IS FOUND IN THE STATETEAX FILE
        return state;
    }
    
    public int getOrderNumber(){
        int orderNumber = io.readInt("Please enter the order number: "); //getting the order number
        return orderNumber; //THIS HAS TO BE CHANGED SO THE PROGRAM WILL READ WHAT ORDERS ARE ALREADY IN THE FILE, COUNT THEM AND THEN GIVE THE NEWLY CREATED ORDER AN ORDER NUMBER BASED ON WHERE IT IS IN THE LIST
    }
    
    public String getCustomerName(){
        String customer = io.readString("Please enter the customer's name"); //getting customer's name
        return customer;
    }
    
    public String getArea(){
        String area = io.readString("Please enter the square footage of the flooring plan"); //getting the area
        if(Integer.parseInt(area) < 100){
            io.readString("We don't take orders that have an rea less than 100 sq.ft. It's just not economically feasible. Please hit enter and enter an order with an area greater than 100:");
            getArea();
        }
        return area;
    }
    
    public String getOrderString(){//going to take this out, just need to supply an order number for now
        String orderNumber = io.readString("Please enter the order number");
        return orderNumber;
    }
    
    public boolean wannaSave(){
        String save = io.readString("Would you like to save your progress? Y/N"); //asking the user if thet would like to save and passing that boolean back to the controller
        boolean saveIt = false; //save starts out as false
        if(save.equalsIgnoreCase("Y")){ //if the user enters Y or y, then save will become true
            return true;
        }else if(save.equalsIgnoreCase("N")){ //if the user enters N or n, save will continue to be false
           return false; 
        }else{
            io.print("Please select either Y or N for Yes or No."); //***check to see if this actually catches an incorrect input by the user
            wannaSave();
        }
        return saveIt;
    }
    
    public void displayExitMessage(){
        io.print("Closing application, Good Bye!");
    }
    
    public void displayUnknownCommandMessage(){
        io.print("Command not recognized. Please choose a numbered choice from above.");
    }
    
    public String getUserDate(){
      String date = io.readLocalDate("Please enter the date for the orders you are looking for:  (MMddyyyy)");
      return date;
    }
    
    public void displayAlrightMessage(){
        io.print("Alright...");
    }

    public void displayOrderInfo(Order order) {
        io.print("Customer Name: " + order.getCustomerName());
        io.print("Product Name: " + order.getProductName());
        io.print("Area: " + order.getArea().toString() + "sq.ft.");
        io.print("State: " + order.getStateAbv());
        io.print("Cost per square foot rate: $" + order.getCostPerSqInch().toString());
        io.print("Labor cost per square foot rate: $" + order.getLaborPerSqInch().toString());
        io.print("State tax rate: " + order.getStateTax().toString() + "%");
        io.print("Total material cost: $" + order.getTotalMaterialCost().toString());
        io.print("Total labor cost: $" + order.getTotalLaborCost().toString());
        io.print("Total tax: $" + order.getTotalTax().toString());
        io.print("Total: $" + order.getTotal().toString());
    }
    
    public void workSaved(){
        io.print("Work Saved!");
    }
    
    public void workNotSave(){
        io.print("Work not saved!");
    }
    
    public void displayOrders(List<Order> orderList){
       for (Order currentOrder : orderList){
           io.print(currentOrder.getOrderNumber() + ": " + currentOrder.getCustomerName() + ": " + currentOrder.getProductName() + ": " + currentOrder.getArea() + ": " + currentOrder.getStateAbv() + ": " + currentOrder.getCostPerSqInch() + ": " + currentOrder.getLaborPerSqInch() + ": " + currentOrder.getStateTax() + ": " + currentOrder.getTotalMaterialCost() + ": " + currentOrder.getTotalLaborCost() + ": " + currentOrder.getTotalTax() + ": " + currentOrder.getTotal());
       }
       io.readString("Please hit enter to continue");
   }
    
   public void displayOrderRemoved(){
       io.print("This order has been removed.");
   }
   
   public String editCustomerName(Order order){
        String customer = io.readString("Please enter the customer's name : (" + order.getCustomerName() + ") : " ); //getting customer's name
        if(customer.equals("")){
            return order.getCustomerName();
        }
        return customer;
   }
   
   public String editProduct(Order order){
        String product = io.readString("Please enter the product you would like to build from (" + order.getProductName() + "): "); //reads the String the user puts in and will find the product in the Products.txt file
        if(product.equals("")){
            return order.getProductName();
        }
        return product; //ADD A VALIDATION METHOD IN SERVICE TO SEE IF THE PRODUCT EXISTS IN THE PRODUCTS FILE
   }
   
   public String editState(Order order){
        String state = io.readString("Please enter the state abreviation you are looking for (" + order.getStateAbv() + ") :"); //ADD SOMETHING THAT WILL CHECK IF THE STATE ABREVIATIONS IS FOUND IN THE STATETEAX FILE
        if(state.equals("")){
            return order.getStateAbv();
        }
        return state;
   }
   
   public String editArea(Order order){
        String area = io.readString("Please enter the square footage of the flooring plan (" + order.getArea() + ") :"); //getting the area
        if("".equals(area) || area == null){
            return order.getArea().toString();
        } 
        if(Integer.parseInt(area) < 100){
            io.readString("We don't take orders that have an area less than 100 sq.ft. It's just not economically feasible. Please hit enter and enter an order with an area greater than 100:");
            return editArea(order);
        }

        return area; //need to add something for area so if the user inputs an empty String, it will just take the original order area
   }
   
      public void displayErrorMessage(String errorMsg){
       io.print("===ERROR===");
       io.print(errorMsg);
   }
   
}
