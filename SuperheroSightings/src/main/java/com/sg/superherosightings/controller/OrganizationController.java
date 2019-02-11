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
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
@Controller
public class OrganizationController {
    OrganizationServiceLayer organizationService;
    PersonServiceLayer personService;
    
    @Inject
    public OrganizationController(OrganizationServiceLayer organizationService) {
        this.organizationService = organizationService;
        this.personService = personService;
    }
    
    public OrganizationController(){
        
    }

    @RequestMapping(value="organization", method=RequestMethod.GET)
    public String allOrganizations(Model model) throws PersistenceException{
        List<Organization> organizations = organizationService.getOrganizationList();
        model.addAttribute("organizations", organizations);
        return "/organization";
    }
    
    @RequestMapping(value="addOrganization", method=RequestMethod.POST)
    public String addOrganization(String orgName, String orgDescription, String orgContactInfo, boolean isHeroOrg) throws PersistenceException{
        Organization organization = new Organization();
        organization.setOrgName(orgName);
        organization.setOrgDescription(orgDescription);
        organization.setOrgContactInfo(orgContactInfo);
        organization.setIsHeroOrg(isHeroOrg);
        organizationService.addOrganization(organization);
        return "redirect:/organization";
    }
    
    @RequestMapping(value="editOrganization/{organizationID}", method=RequestMethod.GET)
    public String editOrganization(@PathVariable("organizationID") int id, Model model) throws PersistenceException{
        Organization organization = organizationService.getOrganization(id);
        model.addAttribute("orgToEdit", organization);
        return "/editOrganization";
    }
    
    @RequestMapping(value="updateOrganization", method=RequestMethod.POST)
    public String updateOrganization(int organizationID, String orgName, String orgDescription, String orgContactInfo, boolean isHeroOrg) throws PersistenceException{
        Organization organization = new Organization();
        organization.setOrganizationID(organizationID);
        organization.setOrgName(orgName);
        organization.setOrgDescription(orgDescription);
        organization.setOrgContactInfo(orgContactInfo);
        organization.setIsHeroOrg(isHeroOrg);
        organizationService.updateOrganization(organization);
        return "redirect:/organization";
    }
    
    @RequestMapping(value="deleteOrganization/{organizationID}", method=RequestMethod.GET)
    public String deleteOrganization(@PathVariable("organizationID") int id) throws PersistenceException{
        organizationService.removeOrganization(id);
        return "redirect:/organization";
    }
    
    @RequestMapping(value="/getOrganizationDetails/{organizationID}", method=RequestMethod.GET)
    public String getOrganizationDetailsByID(@PathVariable("organizationID") int id, Model model) throws PersistenceException{
        Organization organization = organizationService.getOrganization(id);
        model.addAttribute("organization", organization);
        List<Person> organizationMembers = organization.getOrganizationMembers();
        model.addAttribute("organizationMembers", organizationMembers);
        return "/organizationDetails";
    }
    
    @RequestMapping(value="/listPeopleByOrgID/{organizationID)", method=RequestMethod.GET)
    public String listPeopleByOrganizationID(@PathVariable("organizationID") int id, Model model){
        List<Person> peopleInOrganization = personService.getPeopleByOrganizationID(id);
        model.addAttribute("peopleInOrganization", peopleInOrganization);
        return "/organization";
    }
}
