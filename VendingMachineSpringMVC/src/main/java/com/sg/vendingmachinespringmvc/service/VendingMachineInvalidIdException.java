/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author Dan's Laptop
 */
public class VendingMachineInvalidIdException extends Exception{

    public VendingMachineInvalidIdException(String message) {
        super(message);
    }

    public VendingMachineInvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
