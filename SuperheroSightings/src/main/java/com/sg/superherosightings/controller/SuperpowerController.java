/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
@Controller
public class SuperpowerController {
    SuperpowerServiceLayer superpowerService;

    @Inject
    public SuperpowerController(SuperpowerServiceLayer superpowerService) {
        this.superpowerService = superpowerService;
    }
    
    public SuperpowerController(){
        
    }
    
    @RequestMapping(value="addPower", method=RequestMethod.POST)
    public String addPower(String powerName) throws PersistenceException{
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName(powerName);
        superpowerService.addPower(superpower);
        return "redirect:/superpower";
    }
    
    @RequestMapping(value="superpower", method=RequestMethod.GET)
    public String listPowers(Model model) throws PersistenceException{
        List<Superpower> superpowers = superpowerService.getSuperpowerList();
        model.addAttribute("superpowers", superpowers);
        return "superpower";
    }
    
    @RequestMapping(value="editPower/{powerID}", method=RequestMethod.GET)
    public String editPower(@PathVariable("powerID") int id, Model model) throws PersistenceException{
        Superpower superpower = superpowerService.getPower(id);
        model.addAttribute("superpowerToEdit", superpower);
        return "/editSuperpower";
    }
    
    @RequestMapping(value="updatePower", method=RequestMethod.POST)
    public String updatePower(int superpowerID, String superpowerName) throws PersistenceException{
        Superpower superpower = new Superpower();
        superpower.setSuperpowerID(superpowerID);
        superpower.setSuperpowerName(superpowerName);
        superpowerService.updatePower(superpower);
        return "redirect:/superpower";
    }
    
    @RequestMapping(value="deletePower/{powerID}", method=RequestMethod.GET)
    public String deletePower(@PathVariable("powerID") int id) throws PersistenceException{
        superpowerService.removePower(id);
        return "redirect:/superpower";
    }
    
    @RequestMapping(value="/listSuperpowersById/{powerID}", method=RequestMethod.GET)
    public String listSuperpowersById(@PathVariable("powerID") int id, Model model) throws PersistenceException{
        List<Superpower> powers = superpowerService.getPowersByPersonID(id);
        model.addAttribute("powers", powers);
        return "/person";
    }
    
    @RequestMapping(value="/getSuperpowerDetails/{superpowerID}", method=RequestMethod.GET)
    public String getSuperpowerDetailsByID(@PathVariable("superpowerID") int id, Model model) throws PersistenceException{
        Superpower superpower = superpowerService.getPower(id);
        model.addAttribute("superpower", superpower);
        List<Person> listOfPeopleWithThisSuperpower = superpower.getPeopleWithSuperpower();
        model.addAttribute("listOfPeopleWithThisSuperpower", listOfPeopleWithThisSuperpower);
        return "/superpowerDetails";
    }
}
