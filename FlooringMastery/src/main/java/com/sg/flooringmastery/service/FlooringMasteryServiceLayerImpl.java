/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.StateTaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author acetip
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{
    
    OrderDao orderdao;
    ProductDao productdao;
    StateTaxDao statedao;

    public FlooringMasteryServiceLayerImpl(OrderDao orderdao, ProductDao productdao, StateTaxDao statedao) { //gives the service layer access to the three DAO's
        this.orderdao = orderdao;
        this.productdao = productdao;
        this.statedao = statedao;
    }

    @Override
    public Order addOrder(String customerName, String productName, BigDecimal Area, String stateAbv) throws FlooringMasteryPersistenceException{

        BigDecimal convertingTax = new BigDecimal(100);
        BigDecimal stateTax = getStateTax(stateAbv).getStateTax();
        BigDecimal convertedStateTax = stateTax.divide(convertingTax);
        BigDecimal costPerSqFoot = getProduct(productName).getCostPerSqInch();
        BigDecimal laborCost = getProduct(productName).getLaborPerSqInch();
        BigDecimal totalCostPerSqFoot = Area.multiply(costPerSqFoot).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalLaborCost = Area.multiply(laborCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal subtotal = totalCostPerSqFoot.add(totalLaborCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalTax = subtotal.multiply(convertedStateTax).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(totalTax).setScale(2, RoundingMode.HALF_UP);
        Order order = new Order(90 ,customerName, stateAbv, stateTax, productName, Area, costPerSqFoot, laborCost, totalCostPerSqFoot, totalLaborCost, totalTax, total);   
        return orderdao.addOrder(order); //passes the order information to the Order DAO to add an order to the file

    }

    @Override
    public Order viewOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException{
       return orderdao.viewOrder(date, orderNumber);
    }

    @Override
    public Order removeOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException{
        return orderdao.removeOrder(date, orderNumber);
    }

    @Override
    public void editOrder(String date, Order order, Order editedOrder) throws FlooringMasteryPersistenceException{
        orderdao.editOrder(date, editedOrder);
    }

    @Override
    public void saveWork() throws FlooringMasteryPersistenceException{
        orderdao.saveOrders();
    }

    @Override
    public void validateArea(String area) throws MinimumAreaException{
        try{
            Integer.parseInt(area);
        }
        catch(NumberFormatException e){
            throw new NumberFormatException("That's not a number. Please enter a number");
        }
        if(Integer.parseInt(area) < 100){
            throw new MinimumAreaException("We don't take orders that have an area less than 100 sq.ft. It's just not economically feasible. Please enter an order with an area greater than 100:");
        }
    }

    @Override
    public void validateState(String stateAbv) throws FlooringMasteryPersistenceException, StateValidationException{
        if(statedao.getStateTax(stateAbv) == null){
            throw new StateValidationException("That state does not exist. Please select one of the following states: OH, PA, MI, IN");
        }
    }

    @Override
    public void validateDate(String date) throws OrderDateValidationException{ //create order dao method fileDateExists() and have it retunr true if file.exists(), if it doesn't, return false
        if(orderdao.dateExists(date) == false){
            throw new OrderDateValidationException("The date that you entered does not match any files on record. Perhaps you entered it incorrectly?");
        }
    }

    @Override
    public void validateOrderNumber(String date ,int orderNumber) throws OrderNumberValidationException, FlooringMasteryPersistenceException{
        if(orderdao.orderNumberExists(date, orderNumber) == false){
            throw new OrderNumberValidationException("That is not a valid order number for this date. Pleae check if you have the correct order number");
        }
    }
    
    @Override
    public String validateNoDoubleColon(String customerName) {
       customerName =  customerName.replaceAll(":", " ");
        return customerName;
    }

    @Override
    public List<Order> viewOrders(String date) throws FlooringMasteryPersistenceException{
        return orderdao.viewOrders(date);
    }

    @Override
    public List<Product> viewProducts() { //Do I actually need these to view the product and taxes and all their values? I don't see a point in which I will need all of those things at the same time
       return productdao.getAllProducts();
    }

    @Override
    public List<StateTax> viewTaxes() {
        return statedao.getAllTaxes();
    }

    @Override
    public Product getProduct(String productName) throws FlooringMasteryPersistenceException{
      Product product = productdao.getProduct(productName); //needed to add this so the controller could ask the service layer to ask the product DAO what values are associated with the product name entered by the user and add those to the parameters of the order to do calculations on the cost and tax
      return product;
    }

    @Override
    public StateTax getStateTax(String stateAbv) throws FlooringMasteryPersistenceException{
        return statedao.getStateTax(stateAbv); //asks the state DAO what values are associated with the state abreviation that was entered by the user so it can see what tax teh state has
    }

    @Override
    public void saveToHashMap(Order order)  throws FlooringMasteryPersistenceException{
        orderdao.saveToHashmap(order);
    }
    
    @Override
    public void loadMap() throws FlooringMasteryPersistenceException{
        orderdao.loadMap();
    }

    @Override
    public void validateProduct(String productName) throws ProductValidationException, FlooringMasteryPersistenceException {
        if(productdao.getProduct(productName) == null){
            throw new ProductValidationException("That product does not exist. Please select one of the following: Carpet, Tile, Laminate, Wood");
        }
    }


    
    
}
