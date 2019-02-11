/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.OrderDateValidationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Dan's Laptop
 */
public class OrderDaoImpl implements OrderDao {

    private Map<String, Map<Integer, Order>> orders = new HashMap<String, Map<Integer, Order>>();
    public int ordernumber = 0;
    public String ORDER_LIST = "orderlist.txt"; //can't make this static because this name will change when we create a file
    public String ORDER_COUNTER = "ordercounter.txt";
    public static final String DELIMITER = "::";

    @Override
    public String getDate() { //changed this so it now takes the date when the order is made, formats it correctly and returns it to use to name the file
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy")); //LocalDateTime checks what date it is today
        return date; //returning the date to the controller
    }
    
    
    @Override
    public boolean orderNumberExists(String date, int orderNumber) throws FlooringMasteryPersistenceException {
        Map<Integer, Order> map = new HashMap<>();
        map = orders.get(date);
        if(map.containsKey(orderNumber)){
            return true;
        }else{
            return false;
        }
        
    }
    
    @Override
    public boolean dateExists(String date) throws OrderDateValidationException {
        List<String> theDates = allDates();
        if(theDates.contains(date)){
            return true;
        }else{
            return false;
        }
        
    }

    @Override
    public Order viewOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException {
        loadOrderList(date);
        Map<Integer, Order> map = new HashMap<>();
        map = orders.get(date);
        orders.put(date, map);
        if (map == null) {
            orders.put(date, map = new HashMap<>());
        }
        return map.get(orderNumber);

    }

    @Override
    public List<Order> viewOrders(String date) throws FlooringMasteryPersistenceException {
        Map<Integer, Order> hm = orders.get(date);
        return new ArrayList<>(hm.values());

    }

    @Override
    public void editOrder(String date, Order editedOrder) {
        Map<Integer, Order> hm = orders.get(date);
        orders.put(date, hm);
        hm.put(editedOrder.getOrderNumber(), editedOrder); //when using .put, it overwrites whatever information is at that key in the hashmap
    }

    @Override
    public Order removeOrder(String date, int orderNumber) throws FlooringMasteryPersistenceException{
        Map<Integer, Order> hm = orders.get(date);
        Order removedOrder = hm.remove(orderNumber);
        return removedOrder;
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        String date = getDate();
        Map<Integer, Order> hm = orders.get(date);
        if (hm == null) {
            orders.put(date, hm = new HashMap<>());
        }
        int orderNumber = hm.size() + 1;
        order.setOrderNumber(orderNumber);
        return order;
    }

    @Override
    public void saveOrders() throws FlooringMasteryPersistenceException {
        createFile(); //tells the Order DAO to use it's createFile method to amek an empty text file depending on the date that was asked for in the controller
        writeOrderList();
    }

    @Override
    public Order saveToHashmap(Order order) throws FlooringMasteryPersistenceException {
        String date = getDate();
        Map<Integer, Order> hm = orders.get(date);
        orders.put(date, hm);
        hm.put(order.getOrderNumber(), order);
        return hm.put(order.getOrderNumber(), order);
    }

    @Override
    public String createFile() {
        String date = getDate();
        String fileName = "Order_" + date + ".txt"; //will create a file in this format, with the date being passed in being the formatted date that we received from the view -> controller -> servicelayer -> here
        ORDER_LIST = fileName; //assigning the ORDER_LIST(the file that we use to read and write to/from below) to the fileName String variable formed from the formatted date
        String workingDirectory = System.getProperty("user.dir"); //the start of the file path
        PrintWriter out = null;
        
        try {
            String absoluteFilePath = ""; //assigning the absolute file path to an empty String
            absoluteFilePath = workingDirectory + File.separator + fileName; //assigning the file path to the workingDirectory String from above, adding a double slash(separator), and then adding the file name which is the formatted date in between Order_ and .txt
            System.out.println("Final filepath : " + absoluteFilePath); //prints out to the user the file path so they could find it within the system. Not really necessary
            File file = new File(absoluteFilePath); //creates a file based up thhe file path created
//            if(file.exists() && !file.isDirectory()){
//                out = new PrintWriter(new FileOutputStream(new File(ORDER_LIST), true));
//            }
//            else{
//                file.createNewFile();
//            }
//            out.close();
            if (file.createNewFile()) {
                System.out.println("File is created!"); //let's the user know the file was created(this might be useful just for testing purposes)
            } else {
                System.out.println("File already exists!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName; //returns the file name, not entirely sure this is necessary
    }

    @Override
    public void loadOrderList(String date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        String fileName = "Order_" + date + ".txt";
        ORDER_LIST = fileName;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ORDER_LIST))); //loading from the ORDER_LIST which is the Order_(formatted date).txt file
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load item inventory into memory", e);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]), currentTokens[1], currentTokens[2], new BigDecimal(currentTokens[3]), currentTokens[4], new BigDecimal(currentTokens[5]), new BigDecimal(currentTokens[6]), new BigDecimal(currentTokens[7]), new BigDecimal(currentTokens[8]), new BigDecimal(currentTokens[9]), new BigDecimal(currentTokens[10]), new BigDecimal(currentTokens[11]));
 
            Map<Integer, Order> hm = orders.get(date);
            if (hm == null) {
                orders.put(date, hm = new HashMap<>());
            }
            orders.put(date, hm);
            orders.get(date);
            hm.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }

    private void writeOrderList() throws FlooringMasteryPersistenceException {
        PrintWriter out;
        List<String> dates = allDates();
        for(String i : dates){
            String date = i;
            ORDER_LIST = "Order_" + date + ".txt";
        try {
            out = new PrintWriter(new FileWriter(ORDER_LIST));

        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load that item.", e);
        }
        List<Order> orderList = this.viewOrders(date);
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER + currentOrder.getCustomerName() + DELIMITER + currentOrder.getStateAbv() + DELIMITER + currentOrder.getStateTax() + DELIMITER
                    + currentOrder.getProductName() + DELIMITER + currentOrder.getArea() + DELIMITER + currentOrder.getCostPerSqInch() + DELIMITER + currentOrder.getLaborPerSqInch()
                    + DELIMITER + currentOrder.getTotalMaterialCost() + DELIMITER + currentOrder.getTotalLaborCost() + DELIMITER + currentOrder.getTotalTax() + DELIMITER + currentOrder.getTotal());
            out.flush();
        }
        out.close();
        }
    }

    private int loadOrderCounter() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ORDER_COUNTER)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load item inventory into memory", e);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            ordernumber = Integer.parseInt(currentTokens[0]);
        }
        scanner.close();
        return ordernumber;
    }

    private int writeOrderCounter(int orderNumber) throws FlooringMasteryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_COUNTER));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load that item.", e);
        }
        orderNumber++;
        out.println(orderNumber);
        out.flush();
        out.close();
        return orderNumber;
    }
    
    @Override
    public void loadMap()throws FlooringMasteryPersistenceException{
        List<String> dates = allDates();
        
        for(String i : dates){
            loadOrderList(i);
        }
    }
    
    
    public List<String> allDates(){
        System.getProperty("user.dir"); //getting my directory
        List<String> someDates = new ArrayList<>();
        File file = new File(".");
        File[] arrayOfFiles = file.listFiles();
        for(int i = 0; i < arrayOfFiles.length; i++){
            File aFile = arrayOfFiles[i];
            String fileNames = aFile.getName();
            String myDates = fileNames.replaceAll("[\\D]", "").trim();
            if(myDates.equals("")){
            
            }else{
                someDates.add(myDates);
            }

        }
        return someDates;
    }










}
