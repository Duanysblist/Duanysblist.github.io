/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author acetip
 */
public class NameValidationException extends Exception{

    public NameValidationException(String message) {
        super(message);
    }

    public NameValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
