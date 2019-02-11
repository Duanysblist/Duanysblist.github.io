/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
@Controller
public class PersonController {
    PersonServiceLayer personService;
    SightingServiceLayer sightingService;
    SuperpowerServiceLayer superpowerService;
    OrganizationServiceLayer organizationService;
    LocationServiceLayer locationService;
    
    @Inject
    public PersonController(PersonServiceLayer personService, SightingServiceLayer sightingService, SuperpowerServiceLayer superpowerService, OrganizationServiceLayer organizationService, LocationServiceLayer locationService){
        this.personService = personService;
        this.sightingService = sightingService;
        this.superpowerService = superpowerService;
        this.organizationService = organizationService;
        this.locationService = locationService;
    }
    
    public PersonController(){
        
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) throws PersistenceException{
        List<Sighting> sightings = sightingService.getFirstTenSightings();
        model.addAttribute("sightings", sightings);
        return "/index";
    }
    
    @RequestMapping(value="/person", method=RequestMethod.GET)
    public String showPeople(Model model) throws PersistenceException{
        List<Person> people = personService.getPersonList();
        List<Superpower> superpowers = superpowerService.getSuperpowerList();
        List<Organization> organizations = organizationService.getOrganizationList();
        model.addAttribute("people", people);
        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        return "person";
    }
    
   @RequestMapping(value="addPerson", method=RequestMethod.POST)
    public String addPerson(HttpServletRequest request, Model model) throws PersistenceException{
        List<Superpower> allPersonsSuperpowers = new ArrayList<>();
        String[] powerIDsFromJSP = request.getParameterValues("superpowerIDs");
        for(String currentString : powerIDsFromJSP){
            int currentPowerInt = Integer.parseInt(currentString);
            Superpower currentSuperpower = superpowerService.getPower(currentPowerInt);
            allPersonsSuperpowers.add(currentSuperpower);
            
        }
        List<Organization> allPersonsOrganizations = new ArrayList<>();
        String[] orgIDsFromJSP = request.getParameterValues("organizationIDs");
        for(String currentString : orgIDsFromJSP){
            int currentOrgInt = Integer.parseInt(currentString);
            Organization currentOrganization = organizationService.getOrganization(currentOrgInt);
            allPersonsOrganizations.add(currentOrganization);
        }
        String thePersonName = request.getParameter("personName");
        String thePersonDescription = request.getParameter("personDescription");
        String stringIsHero = request.getParameter("isHero");
        Boolean theIsHero = Boolean.parseBoolean(stringIsHero);
        
        Person person = new Person();
        person.setPersonName(thePersonName);
        person.setPersonDescription(thePersonDescription);
        person.setIsHero(theIsHero);
        person.setListOfSuperpowers(allPersonsSuperpowers);
        person.setListofOrganizations(allPersonsOrganizations);
        

        personService.addPerson(person);
        
//        for(Superpower DaPowah : allPersonsSuperpowers){
//            int DaPowahID = DaPowah.getSuperpowerID();
//            personService.addPersonSuperpower(person.getPersonID(), DaPowahID);
//        }
//        for(Organization DaOrg : allPersonsOrganizations){
//            int DaOrgID = DaOrg.getOrganizationID();
//            personService.addPersonOrganization(person.getPersonID(), DaOrgID);
//        }

        return "redirect:/person";
    }
    
    @RequestMapping(value="/editPerson/{personID}", method=RequestMethod.GET)
    public String editPerson(@PathVariable("personID") int id, Model model) throws PersistenceException{
        Person person = personService.getPerson(id);
        List<Superpower> superpowers = superpowerService.getSuperpowerList();
        List<Organization> organizations = organizationService.getOrganizationList();
        model.addAttribute("person", person);
        model.addAttribute("organizations", organizations);
        model.addAttribute("superpowers", superpowers);
        return "editPerson";
    }
    
    @RequestMapping(value="/updatePerson", method=RequestMethod.POST)
    public String updatePerson(@ModelAttribute("person") Person person, HttpServletRequest request) throws PersistenceException{
        Person pastPerson = personService.getPerson(person.getPersonID());
        pastPerson.setPersonID(person.getPersonID());
        pastPerson.setPersonName(person.getPersonName());
        pastPerson.setPersonDescription(person.getPersonDescription());
        pastPerson.setIsHero(person.getIsHero());
        
        List<Organization> pastPeopleOrgs = organizationService.getOrganizationsByPersonID(pastPerson.getPersonID());
        for(Organization oldOrgs : pastPeopleOrgs){
           personService.removePersonOrganization(pastPerson.getPersonID(), oldOrgs.getOrganizationID());
        }
        
        List<Superpower> pastPeoplePowers = superpowerService.getPowersByPersonID(pastPerson.getPersonID());
        for(Superpower oldPowers : pastPeoplePowers){
            personService.removePersonSuperpower(pastPerson.getPersonID(), oldPowers.getSuperpowerID());
        }
        
        
        List<Organization> personsOrgs = new ArrayList<>();
        String[] orgList = request.getParameterValues("listOfOrganizationIDs");
        for(String currentString : orgList){
            int orgID = Integer.parseInt(currentString);
            Organization currentOrganization = organizationService.getOrganization(orgID);
            personsOrgs.add(currentOrganization);
        }
        
        pastPerson.setListofOrganizations(personsOrgs);

        List<Superpower> personsPowers = new ArrayList<>();
        String[] powerList = request.getParameterValues("listOfSuperpowerIDs");
        for(String currentString : powerList){
            int powerID = Integer.parseInt(currentString);
            Superpower currentSuperpower = superpowerService.getPower(powerID);
            personsPowers.add(currentSuperpower);
        }
        
        pastPerson.setListOfSuperpowers(personsPowers);
        
        personService.updatePerson(pastPerson);
        return "redirect:/person";
    }
    
    @RequestMapping(value="/deletePerson/{personID}", method=RequestMethod.GET)
    public String deletePerson(@PathVariable("personID") int id) throws PersistenceException{
        List<Superpower> superpowers = superpowerService.getPowersByPersonID(id);
        for(Superpower superpower : superpowers){
            int sID = superpower.getSuperpowerID();
            personService.removePersonSuperpower(id, sID);
        }
        List<Organization> organizations = organizationService.getOrganizationsByPersonID(id);
        for(Organization organization : organizations){
            int oID = organization.getOrganizationID();
            personService.removePersonOrganization(id, oID);
        }
        personService.removePerson(id);
        return "redirect:/person";
    }
    
    @RequestMapping(value="/getPersonDetails/{personID}", method=RequestMethod.GET)
    public String getPersonDetailsByID(@PathVariable("personID") int id, Model model) throws PersistenceException{
       Person person = personService.getPerson(id);
       model.addAttribute("person", person);
       List<Superpower> superpowers = person.getListOfSuperpowers();
       model.addAttribute("superpowers", superpowers);
       List<Organization> organizations = person.getListofOrganizations();
       model.addAttribute("organizations", organizations);
       List<Location> locations = locationService.getLocationsByPersonID(id);
       model.addAttribute("locations", locations);
       return "/personDetails";
    }
    
            
}
