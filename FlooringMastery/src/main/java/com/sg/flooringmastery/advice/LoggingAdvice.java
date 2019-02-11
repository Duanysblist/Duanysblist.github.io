/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.service.FlooringMasteryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author acetip
 */
public class LoggingAdvice {
    
    private FlooringMasteryAuditDao auditdao;
    
    public LoggingAdvice(FlooringMasteryAuditDao auditdao){
        this.auditdao = auditdao;
    }
    
    public void createAuditOrderNumberEntry(JoinPoint jp){
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for(Object currentArg : args){
            auditEntry += currentArg;
        }
        try{
            auditdao.writeAuditEntry(auditEntry);
        }catch(FlooringMasteryPersistenceException e){
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createAuditExceptionEntry(JoinPoint jp, Exception ex){
        Object[] args = jp.getArgs();
        String auditEntry = ex.getClass().getSimpleName() + ": ";
        for(Object currentArg : args){
            auditEntry += currentArg;
        }
        try{
            auditdao.writeAuditEntry(auditEntry);
        }catch(FlooringMasteryPersistenceException e){
            System.err.println("Error: Could not create audit exception entry in LoggingAdvice.");
        }
    }
}
