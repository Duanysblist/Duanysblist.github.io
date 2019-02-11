/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author acetip
 */
public class UserIOConsoleImpl implements UserIO{

    @Override
    public void print(String msg) {
        System.out.println(msg); //printing out a message to the user
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        try{
        Scanner sc = new Scanner(System.in); //putting in scanner
        print(prompt); //printing a message to the user
        String userInput = sc.nextLine(); //takes in the String a user puts in
        BigDecimal result = new BigDecimal(userInput).setScale(2, HALF_UP); //converting the String the user put in into a Big Decimal formatted to have two decimal spaces and round up
        return result; //return the BigDecimal
        }catch(Exception e){ //if the user does not input a String that can be converted to a big decimal, this exception will be thrown
            print("That is not a number. Please enter a number (e.g. 2, 4, 8 etc.)");
            return readBigDecimal(prompt); //returns the user to the beginning of the method to start again
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        try{
        Scanner sc = new Scanner(System.in);
        print(prompt);
        int result = 0; //initialize the result (Look up why this is neccessary)***
        String userInput = sc.nextLine();
        result = Integer.parseInt(userInput); //taking the user input and converting it to an integer
        if(result >= min && result <= max){ //if the number the user put in is greater than the minimum or less than the maximum, return the result
            return result;
        }else{
            print("Please make a selection from choices listed or leave. Your choice..."); //if the number is outside of that range, read this out to the user and have them input a different number
            return readInt(prompt, min, max);
        }
        }catch(Exception e){
            print("Please select from the on the above NUMBERED choices"); //if the user does not put in a number, then catch it with is exception, read this out to them, and return them to the beginning of the method to input a number
            return readInt(prompt, min, max);
        }
    }

    @Override
    public int readInt(String prompt) { //put in catches or if/else statements here ***
        int result = 0;
        try{
        Scanner sc = new Scanner(System.in); 
        print(prompt);
        String userInput = sc.nextLine(); //user inputs a number
        if(userInput == null || "".equals(userInput)){
            print("Please enter an actual number, or a number in the correct format.");
            return readInt(prompt);
        }
        result = Integer.parseInt(userInput); //takes the String number and converts it to an integer
        return result; //return the number
        }catch(NumberFormatException e){
            print("Error, you must enter a number. That number must be an actual order number according to our files. Thanks.");
            return readInt(prompt);
        }
    }

    @Override
    public String readLocalDate(String prompt) {  //is this the correct way of formatting LocalDateTime? ***
        Scanner sc = new Scanner(System.in);
        print(prompt);
        String userInput = sc.nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy"); //how does this actually format the Date/Time? ***
//        LocalDate dateTime = LocalDate.parse(userInput, formatter);
        return userInput; //using this for finding an order based up the date the user inputs
        
        
    }

    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        print(prompt);
        return sc.nextLine(); //input and return a String from the user
    }
    
}
