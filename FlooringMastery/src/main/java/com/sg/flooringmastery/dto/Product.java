/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author acetip
 */
public class Product {
    private String name;
    private BigDecimal costPerSqInch;
    private BigDecimal laborPerSqInch;
    
    public Product(String name, BigDecimal costPerSqInch, BigDecimal laborPerSqInch){ //cosntructor for Product. Will find the product by it's name.
        this.name = name;
        this.costPerSqInch = costPerSqInch;
        this.laborPerSqInch = laborPerSqInch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCostPerSqInch() {
        return costPerSqInch;
    }

    public void setCostPerSqInch(BigDecimal costPerSqInch) {
        this.costPerSqInch = costPerSqInch;
    }

    public BigDecimal getLaborPerSqInch() {
        return laborPerSqInch;
    }

    public void setLaborPerSqInch(BigDecimal laborPerSqInch) {
        this.laborPerSqInch = laborPerSqInch;
    }
    
}
