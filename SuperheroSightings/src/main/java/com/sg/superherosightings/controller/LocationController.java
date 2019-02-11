/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
@Controller
public class LocationController {
    LocationServiceLayer locationService;
    SightingServiceLayer sightingService;
    
    @Inject
    public LocationController(LocationServiceLayer locationService, SightingServiceLayer sightingService) {
        this.locationService = locationService;
        this.sightingService = sightingService;
    }
    
    public LocationController(){
        
    }

    @RequestMapping(value="location", method=RequestMethod.GET)
    public String allLocations(Model model) throws PersistenceException{
        List<Location> locations = locationService.getLocationList();
        model.addAttribute("locations", locations);
        return "location";
    }
    
    @RequestMapping(value="addLocation", method=RequestMethod.POST)
    public String addLocation(HttpServletRequest request, String locationName, String locationDescription, String locationAddress, BigDecimal locationLatitude, BigDecimal locationLongitude) throws PersistenceException{
        String Vishnu = request.getParameter("locationAddress");
        Location location = new Location();
        location.setLocationName(locationName);
        location.setLocationDescription(locationDescription);
        location.setLocationAddress(request.getParameter("locationAddress"));
        location.setLocationLatitude(locationLatitude);
        location.setLocationLongitude(locationLongitude);
        locationService.addLocation(location);
        return "redirect:/location";
    }
    
    @RequestMapping(value="editLocation/{locationID}", method=RequestMethod.GET)
    public String editLocation(@PathVariable("locationID") int id, Model model) throws PersistenceException{
        Location location = locationService.getLocation(id);
        model.addAttribute("locationToEdit", location);
        return "/editLocation";
    }
    
    @RequestMapping(value="updateLocation", method=RequestMethod.POST)
    public String updateLocation(int locationID, String locationName, String locationDescription, String locationAddress, BigDecimal locationLatitude, BigDecimal locationLongitude) throws PersistenceException{
        Location location = new Location();
        location.setLocationID(locationID);
        location.setLocationName(locationName);
        location.setLocationDescription(locationDescription);
        location.setLocationAddress(locationAddress);
        location.setLocationLatitude(locationLatitude);
        location.setLocationLongitude(locationLongitude);
        locationService.updateLocation(location);
        return "redirect:/location";
    }
    
    @RequestMapping(value="deleteLocation/{locationID}", method=RequestMethod.GET)
    public String deleteLocation(@PathVariable("locationID") int id) throws PersistenceException{
        locationService.removeLocation(id);
        return "redirect:/location";
    }
    
    @RequestMapping(value="/getLocationDetails/{locationID}", method=RequestMethod.GET)
    public String getLocationDetailsByID(@PathVariable("locationID") int id, Model model) throws PersistenceException{
        Location location = locationService.getLocation(id);
        model.addAttribute("location", location);
        List<Sighting> sightings = sightingService.getSightingByLocation(id);
        //This is really just getting back some integers and a Date, but it is trying to form it into a row mapper without the other information
        model.addAttribute("sightings", sightings);
        return "/locationDetails";
    }
}
