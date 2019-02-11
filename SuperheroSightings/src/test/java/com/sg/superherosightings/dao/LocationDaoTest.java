/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;

/**
 *
 * @author Dan's Laptop
 */
public class LocationDaoTest {
    
    PersonDao personDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    
    public LocationDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws PersistenceException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        personDao = ctx.getBean("personDao", PersonDao.class);
        List<Sighting> sightings = sightingDao.getSightingList();
        for(Sighting sighting : sightings){
            sightingDao.removeSighting(sighting.getSightingsID());
        }
        List<Person> people = personDao.getPersonList();
        for(Person person : people){
            personDao.removePerson(person.getPersonID());
        }
        List<Location> locations = locationDao.getLocationList();
        for(Location location : locations){
            locationDao.removeLocation(location.getLocationID());
        }
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
//    @Test
//    public void addLocationTest() throws PersistenceException{
//        Location location = new Location();
//        location.setLocationName("My House");
//        location.setLocationDescription("The best place ever");
//        location.setLocationAddress("1224 Bellmore Avenue");
//        location.setLocationLatitude(new BigDecimal("345.67"));
//        location.setLocationLongitude(new BigDecimal("234.56"));
//        locationDao.addLocation(location);
//        int locationNum = location.getLocationID();
//        Location fromDao = locationDao.getLocation(locationNum);
//        assertEquals(location, fromDao);
//    }
//    
//    @Test
//    public void getLocationListTest() throws PersistenceException{
//        Location location = new Location();
//        location.setLocationName("My House");
//        location.setLocationDescription("The best place ever");
//        location.setLocationAddress("1224 Bellmore Avenue");
//        location.setLocationLatitude(new BigDecimal("345.67"));
//        location.setLocationLongitude(new BigDecimal("234.56"));
//        locationDao.addLocation(location);
//        Location locationTwo = new Location();
//        locationTwo.setLocationName("My House");
//        locationTwo.setLocationDescription("The best place ever");
//        locationTwo.setLocationAddress("1224 Bellmore Avenue");
//        locationTwo.setLocationLatitude(new BigDecimal("345.67"));
//        locationTwo.setLocationLongitude(new BigDecimal("234.56"));
//        locationDao.addLocation(locationTwo);
//        int expected = 2;
//        int result = locationDao.getLocationList().size();
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void removeLocationTest() throws PersistenceException{
//        Location location = new Location();
//        location.setLocationName("My House");
//        location.setLocationDescription("The best place ever");
//        location.setLocationAddress("1224 Bellmore Avenue");
//        location.setLocationLatitude(new BigDecimal("345.67"));
//        location.setLocationLongitude(new BigDecimal("234.56"));
//        locationDao.addLocation(location);
//        int listSize = locationDao.getLocationList().size();
//        int expected = 1;
//        assertEquals(listSize, expected);
//        locationDao.removeLocation(location.getLocationID());
//        int nextSize = locationDao.getLocationList().size();
//        int nextExpected = 0;
//        assertEquals(nextSize, nextExpected);
//    }
}
