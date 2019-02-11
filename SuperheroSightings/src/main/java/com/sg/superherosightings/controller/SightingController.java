/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
@Controller
public class SightingController {
    SightingServiceLayer sightingService;
    LocationServiceLayer locationService;
    PersonServiceLayer personService;
    
    @Inject
    public SightingController(SightingServiceLayer sightingService, LocationServiceLayer locationService, PersonServiceLayer personService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.personService = personService;
    }
    
    public SightingController(){
        
    }
    
    @RequestMapping(value="sighting", method=RequestMethod.GET)
    public String allSightings(Model model) throws PersistenceException{
        List<Sighting> sightings = sightingService.getSightingList();
        model.addAttribute("sightings", sightings);
        return "/sighting";
    }
    
    @RequestMapping(value="reportSighting", method=RequestMethod.GET)
    public String reportSighting(Model model) throws PersistenceException{
        List<Person> people = personService.getPersonList();
        List<Location> locations = locationService.getLocationList();
        LocalDate today = LocalDate.now();
        model.addAttribute("people", people);
        model.addAttribute("locations", locations);
        model.addAttribute("today", today);
        return "report";
    }
    
    @RequestMapping(value="addSighting", method=RequestMethod.POST)
    public String addSighting(int personID, int locationID, String sightingDate) throws PersistenceException{
        LocalDate date;
        date = LocalDate.parse(sightingDate);
        Person person = new Person();
        person.setPersonID(personID);
        Location location = new Location();
        location.setLocationID(locationID);
        Sighting sighting = new Sighting();
        sighting.setSightingsDate(date);
        sighting.setPerson(person);
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        return "redirect:/sighting";
    }
    
    @RequestMapping(value="editSighting/{sightingID}", method=RequestMethod.GET)
    public String editSighting(@PathVariable("sightingID") int sightingID, Model model) throws PersistenceException{
        Sighting sighting = sightingService.getSightingByID(sightingID);
        List<Person> people = personService.getPersonList();
        List<Location> locations = locationService.getLocationList();
        model.addAttribute("people", people);
        model.addAttribute("locations", locations);
        model.addAttribute("sightingToEdit", sighting);
        return "/editSighting";
    }
    
    @RequestMapping(value="updateSighting", method=RequestMethod.POST)
    public String updateSighting(HttpServletRequest request, Model model) throws PersistenceException{
        Sighting sighting = new Sighting();
        sighting.setSightingsID(Integer.parseInt(request.getParameter("sightingID")));
        sighting.setSightingsDate(LocalDate.parse(request.getParameter("sightingDate")));
        sighting.setLocationID(Integer.parseInt(request.getParameter("locationID")));
        sighting.setPersonID(Integer.parseInt(request.getParameter("personID")));
        sightingService.updateSighting(sighting);
        return "redirect:/sighting";
    }
    
    @RequestMapping(value="deleteSighting/{sightingID}", method=RequestMethod.GET)
    public String deleteSighting(@PathVariable("sightingID") int id) throws PersistenceException{
        sightingService.removeSighting(id);
        return "redirect:/sighting";
    }
    
    @RequestMapping(value="/getSightingDetails/{sightingsID}", method=RequestMethod.GET)
    public String getSightingDetailsByID(@PathVariable("sightingsID") int id, Model model) throws PersistenceException{
        Sighting sighting = sightingService.getSightingByID(id);
        model.addAttribute("sighting", sighting);
        Person personSighted = sighting.getPerson();
        model.addAttribute("personSighted", personSighted);
        Location locationWhereSightingOccured = sighting.getLocation();
        model.addAttribute("locationWhereSightingOccured", locationWhereSightingOccured);
        return "/sightingDetails";
    }
    
}
