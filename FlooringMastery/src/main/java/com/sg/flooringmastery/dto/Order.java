/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author acetip
 */
public class Order {
    private String customerName;
    private LocalDateTime date;
    private int orderNumber;
    private String stateAbv;
    private BigDecimal area;
    private String productName;
    private BigDecimal costPerSqInch;
    private BigDecimal laborPerSqInch;
    private String stateName;
    private BigDecimal stateTax;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal total;



    
    public Order(int orderNumber, String customerName, String stateAbv, BigDecimal stateTax, String productName, BigDecimal area, BigDecimal costPerSqInch, BigDecimal laborPerSqInch, BigDecimal totalMaterialCost, BigDecimal totalLaborCost, BigDecimal totalTax, BigDecimal total){
    this.orderNumber = orderNumber;
    this.customerName = customerName;
    this.stateAbv = stateAbv;
    this.stateTax = stateTax;
    this.productName = productName;
    this.area = area;
    this.costPerSqInch = costPerSqInch;
    this.laborPerSqInch = laborPerSqInch;
    this.totalMaterialCost = totalMaterialCost;
    this.totalLaborCost = totalLaborCost;
    this.totalTax = totalTax;
    this.total = total;
}

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStateAbv() {
        return stateAbv;
    }

    public void setStateAbv(String stateAbv) {
        this.stateAbv = stateAbv;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
    
    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }
    
        public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
