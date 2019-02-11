/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.List;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;
import sg.dontdiejustkode.superherosightingsgroupwork.services.OrganizationServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
public class OrganizationServiceLayerImpl implements OrganizationServiceLayer{
    
    private OrganizationDao organizationDao;
    private PersonDao personDao;
    
    
    @Inject
    public OrganizationServiceLayerImpl(OrganizationDao organizationDao, PersonDao personDao) {
        this.organizationDao = organizationDao;
        this.personDao = personDao;
    }
    
    @Override
    public void addOrganization(Organization organization) throws PersistenceException {
        organizationDao.addOrganization(organization);
    }

    @Override
    public Organization getOrganization(int organizationID) throws PersistenceException {
        Organization organization = organizationDao.getOrganization(organizationID);
        List<Person> daPersonList = personDao.getPeopleByOrganizationID(organizationID);
        organization.setOrganizationMembers(daPersonList);
        return organization;
    }

    @Override
    public List<Organization> getOrganizationList() throws PersistenceException {
        return organizationDao.getOrganizationList();
    }

    @Override
    public void updateOrganization(Organization organization) throws PersistenceException {
        organizationDao.updateOrganization(organization);
    }

    @Override
    public void removeOrganization(int OrganizationID) throws PersistenceException {
        organizationDao.removeOrganization(OrganizationID);
    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personID) throws PersistenceException {
        return organizationDao.getOrganizationsByPersonID(personID);
    }

    @Override
    public List<User> getAllAdminsForOrganization(int organizationID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizationsForAdmin(int userID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
