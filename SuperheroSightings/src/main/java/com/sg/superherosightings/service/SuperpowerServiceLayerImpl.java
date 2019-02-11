/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.List;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SuperpowerServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
public class SuperpowerServiceLayerImpl implements SuperpowerServiceLayer{
    
    SuperpowerDao superpowerDao;
    PersonDao personDao;

    @Inject
    public SuperpowerServiceLayerImpl(SuperpowerDao superpowerDao, PersonDao personDao) {
        this.superpowerDao = superpowerDao;
        this.personDao = personDao;
    }

    @Override
    public void addPower(Superpower superpower) throws PersistenceException {
        superpowerDao.addPower(superpower);
    }

    @Override
    public Superpower getPower(int powerID) throws PersistenceException {
        Superpower superpower = superpowerDao.getPower(powerID);
        List<Person> listOfPeopleWithThisSuperpower = personDao.getPeopleBySuperpowerID(powerID);
        superpower.setPeopleWithSuperpower(listOfPeopleWithThisSuperpower);
        return superpower;
    }

    @Override
    public List<Superpower> getSuperpowerList() throws PersistenceException {
        return superpowerDao.getSuperpowerList();
    }

    @Override
    public void updatePower(Superpower superpower) throws PersistenceException {
        superpowerDao.updatePower(superpower);
    }

    @Override
    public void removePower(int powerID) throws PersistenceException {
        superpowerDao.removePower(powerID);
    }

    @Override
    public List<Superpower> getPowersByPersonID(int personID) throws PersistenceException {
        return superpowerDao.getPowersByPersonID(personID);
    }

    
}
