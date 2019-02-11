/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import java.util.List;
import javax.inject.Inject;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.services.LocationServiceLayer;

/**
 *
 * @author Dan's Laptop
 */
public class LocationServiceLayerImpl implements LocationServiceLayer{
    
    private LocationDao locationDao;
    
    @Inject
    public LocationServiceLayerImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public void addLocation(Location location) throws PersistenceException {
       locationDao.addLocation(location);
    }

    @Override
    public Location getLocation(int locationID) throws PersistenceException {
        return locationDao.getLocation(locationID);
    }

    @Override
    public List<Location> getLocationsByPersonID(int personID) throws PersistenceException {
        return locationDao.getLocationsByPersonID(personID);
    }

    @Override
    public List<Location> getLocationList() throws PersistenceException {
       return locationDao.getLocationList();
    }

    @Override
    public void updateLocation(Location location) throws PersistenceException {
        locationDao.updateLocation(location);
    }

    @Override
    public void removeLocation(int locationID) throws PersistenceException {
        locationDao.removeLocation(locationID);
    }

    @Override
    public Location getLocationBySightingID(int sightingsID) throws PersistenceException {
        return locationDao.getLocationBySightingID(sightingsID);
    }

}
