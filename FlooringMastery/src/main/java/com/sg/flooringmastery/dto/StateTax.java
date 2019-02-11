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
public class StateTax {
    private String stateName;
    private String stateAbv;
    private BigDecimal stateTax;

    public StateTax(String stateAbv, BigDecimal stateTax){ //constructor for the state tax. Parameters reflect what is in the file itself
        this.stateAbv = stateAbv;
        this.stateTax = stateTax;
    }
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbv() {
        return stateAbv;
    }

    public void setStateAbv(String stateAbv) {
        this.stateAbv = stateAbv;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }
    
}
