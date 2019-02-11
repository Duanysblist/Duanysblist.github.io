/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author Dan's Laptop
 */
public class OrderNumberValidationException extends Exception{
    
    public OrderNumberValidationException(String message) {
        super(message);
    }

    public OrderNumberValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
