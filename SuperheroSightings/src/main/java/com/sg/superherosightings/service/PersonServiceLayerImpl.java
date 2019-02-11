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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.PersonServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
public class PersonServiceLayerImpl implements PersonServiceLayer{
    
    private PersonDao personDao;
    private SightingDao sightingDao;
    private SuperpowerDao superpowerDao;
    private OrganizationDao organizationDao;

    @Inject
    public PersonServiceLayerImpl(PersonDao personDao, SightingDao sightingDao, SuperpowerDao superpowerDao, OrganizationDao organizationDao) {
        this.personDao = personDao;
        this.sightingDao = sightingDao;
        this.superpowerDao = superpowerDao;
        this.organizationDao = organizationDao;
    }
    
    @Override
    public void addPerson(Person person) throws PersistenceException {
        personDao.addPerson(person);
    }

    @Override
    public void addPersonOrganization(int personID, int organizationID) throws PersistenceException {
        personDao.addPersonOrganization(personID, organizationID);
    }

    @Override
    public void addPersonSuperpower(int personID, int powerID) throws PersistenceException {
        personDao.addPersonSuperpower(personID, powerID);
    }

    @Override
    public Person getPerson(int personID) throws PersistenceException {
        Person person = personDao.getPerson(personID);
        List<Superpower> daSuperList = superpowerDao.getPowersByPersonID(personID);
        List<Organization> daOrgList = organizationDao.getOrganizationsByPersonID(personID);
        person.setListOfSuperpowers(daSuperList);
        person.setListofOrganizations(daOrgList);
        return person;
    }

    @Override
    public List<Person> getPersonList() throws PersistenceException {
       return personDao.getPersonList();
    }

    @Override
    public List<Person> getPeopleByLocationID(int locationID) throws PersistenceException {
        return personDao.getPeopleByLocationID(locationID);
    }

    @Override
    public List<Person> getPeopleByOrganizationID(int organizationID) {       
        return personDao.getPeopleByOrganizationID(organizationID);
    }

    @Override
    public void updatePerson(Person person) throws PersistenceException {
        personDao.updatePerson(person);
    }

    @Override
    public void removePerson(int personID) throws PersistenceException {
        personDao.removePerson(personID);
    }

    @Override
    public void removePersonOrganization(int personID, int organizationID) throws PersistenceException {
        personDao.removePersonOrganization(personID, organizationID);
    }

    @Override
    public void removePersonSuperpower(int personID, int powerID) throws PersistenceException {
        personDao.removePersonSuperpower(personID, powerID);
    }

    @Override
    public List<Person> getPeopleBySuperpowerID(int superpowerID) {
        return personDao.getPeopleBySuperpowerID(superpowerID);
    }

    @Override
    public Person getPersonBySightingID(int sightingsID) throws PersistenceException {
        return personDao.getPersonBySightingID(sightingsID);
    }


}
