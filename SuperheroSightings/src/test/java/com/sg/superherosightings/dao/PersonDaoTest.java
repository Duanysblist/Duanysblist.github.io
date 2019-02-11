/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;

/**
 *
 * @author Dan's Laptop
 */
public class PersonDaoTest {
    
    PersonDao personDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;
    SuperpowerDao powerDao;
    
    public PersonDaoTest() {
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
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        powerDao = ctx.getBean("superpowerDao",SuperpowerDao.class);
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
        List<Organization> organizations = organizationDao.getOrganizationList();
        for(Organization organization : organizations){
            organizationDao.removeOrganization(organization.getOrganizationID());
        }
        List<Superpower> powers = powerDao.getSuperpowerList();
        for(Superpower power : powers){
            organizationDao.removeOrganization(power.getSuperpowerID());
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
//    public void addPersonTest() throws PersistenceException{
//        Person person = new Person();
//        person.setPersonName("Catwoman");
//        person.setPersonDescription("Also known as Halle Berry.");
//        person.setIsHero(Boolean.TRUE);
//        personDao.addPerson(person);
//        Person result = personDao.getPerson(person.getPersonID());
//        assertEquals(person, result);
//    }
//    
//    @Test
//    public void getPeopleListTest() throws PersistenceException{
//        Person personOne = new Person();
//        personOne.setPersonName("Number 1");
//        personOne.setPersonDescription("Also known as Halle Berry.");
//        personOne.setIsHero(Boolean.TRUE);
//        personDao.addPerson(personOne);
//        Person personTwo = new Person();
//        personTwo.setPersonName("Number 2");
//        personTwo.setPersonDescription("Also known as Halle Berry.");
//        personTwo.setIsHero(Boolean.TRUE);
//        personDao.addPerson(personTwo);
//        int expectation = 2;
//        int literal = personDao.getPersonList().size();
//        assertEquals(expectation, literal);     
//    }
//    
//    @Test
//    public void removePersonTest() throws PersistenceException{
//        Person person = new Person();
//        person.setPersonName("Catwoman");
//        person.setPersonDescription("Also known as Halle Berry.");
//        person.setIsHero(Boolean.TRUE);
//        personDao.addPerson(person);
//        int listSize = personDao.getPersonList().size();
//        int expected = 1;
//        assertEquals(listSize, expected);
//        personDao.removePerson(person.getPersonID());
//        int nextSize = personDao.getPersonList().size();
//        int nextExpected = 0;
//        assertEquals(nextSize, nextExpected);
//        
//    }
//    
//    @Test
//    public void addPersonSuperpower() throws PersistenceException{
//        Person person = new Person();
//        person.setPersonName("Catwoman");
//        person.setPersonDescription("Also known as Halle Berry.");
//        person.setIsHero(Boolean.TRUE);
//        personDao.addPerson(person);
//        Person result = personDao.getPerson(person.getPersonID());
//        assertEquals(person, result);
////        List<Superpower> powers;
//        Superpower thePower = new Superpower();
//        thePower.setSuperpowerName("Strength");
//        powerDao.addPower(thePower);
//        Superpower expected = powerDao.getPower(thePower.getSuperpowerID());
//        assertEquals(thePower, expected);
//        personDao.addPersonSuperpower(person.getPersonID(), thePower.getSuperpowerID());
//        
//    }
}
