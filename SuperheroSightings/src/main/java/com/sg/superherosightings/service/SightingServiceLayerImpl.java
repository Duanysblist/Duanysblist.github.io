/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.services.SightingServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
public class SightingServiceLayerImpl implements SightingServiceLayer{

    SightingDao sightingDao;
    LocationDao locationDao;
    PersonDao personDao;

    @Inject
    public SightingServiceLayerImpl(SightingDao sightingDao, LocationDao locationDao, PersonDao personDao) {
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.personDao = personDao;
    }
    
    
    @Override
    public void addSighting(Sighting sighting) throws PersistenceException {
        sightingDao.addSighting(sighting);
    }

    @Override
    public Sighting getSightingByID(int sightingID) throws PersistenceException {
       Sighting sighting = sightingDao.getSightingByID(sightingID);
       Person person = personDao.getPersonBySightingID(sighting.getSightingsID());
       Location location = locationDao.getLocationBySightingID(sighting.getSightingsID());
       sighting.setPerson(person);
       sighting.setLocation(location);
       return sighting;
    }

    @Override
    public List<Sighting> getSightingByLocation(int locationID) throws PersistenceException {
        
        List<Sighting> sightings = sightingDao.getSightingByLocation(locationID);
        List<Sighting> newSightings = new ArrayList<>();
        for(Sighting sighting : sightings){
            Person person = personDao.getPersonBySightingID(sighting.getSightingsID());
            Location location = locationDao.getLocationBySightingID(sighting.getSightingsID());
            sighting.setPerson(person);
            sighting.setLocation(location);
            newSightings.add(sighting);
        }
        return newSightings;
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate sightingsDate) throws PersistenceException {
        return sightingDao.getSightingByDate(sightingsDate);
    }

    @Override
    public List<Sighting> getSightingList() throws PersistenceException {
        List<Sighting> sightings = sightingDao.getSightingList();
        List<Sighting> newSightings = new ArrayList<>();
        for(Sighting sighting : sightings){
            Person person = personDao.getPersonBySightingID(sighting.getSightingsID());
            Location location = locationDao.getLocationBySightingID(sighting.getSightingsID());
            sighting.setPerson(person);
            sighting.setLocation(location);
            newSightings.add(sighting);
        }
        return newSightings;
    }

    @Override
    public void updateSighting(Sighting sighting) throws PersistenceException {
       Person person = personDao.getPerson(sighting.getPersonID());
       Location location = locationDao.getLocation(sighting.getLocationID());
       sighting.setPerson(person);
       sighting.setLocation(location);
       sightingDao.updateSighting(sighting);
    }

    @Override
    public void removeSighting(int sightingID) throws PersistenceException {
        sightingDao.removeSighting(sightingID);
    }

    @Override
    public List<Sighting> getFirstTenSightings() throws PersistenceException {
        List<Sighting> sightings = sightingDao.getFirstTenSightings();
        List<Sighting> newSightings = new ArrayList<>();
        for(Sighting sighting : sightings){
            Person person = personDao.getPersonBySightingID(sighting.getSightingsID());
            Location location = locationDao.getLocationBySightingID(sighting.getSightingsID());
            sighting.setPerson(person);
            sighting.setLocation(location);
            newSightings.add(sighting);
        }
        return newSightings;
    }

    @Override
    public List<Sighting> getSightingByPerson(int personID) throws PersistenceException {
        return sightingDao.getSightingByPerson(personID);
    }

}
