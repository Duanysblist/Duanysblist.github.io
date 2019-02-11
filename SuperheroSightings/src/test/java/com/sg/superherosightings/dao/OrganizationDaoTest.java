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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;

/**
 *
 * @author Dan's Laptop
 */
public class OrganizationDaoTest {
    
    OrganizationDao organizationDao;
    PersonDao personDao;
    
    
    public OrganizationDaoTest() {
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
        personDao = ctx.getBean("personDao", PersonDao.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        List<Person> people = personDao.getPersonList();
        for(Person person : people){
            personDao.removePerson(person.getPersonID());
        }
        List<Organization> organizations = organizationDao.getOrganizationList();
        for(Organization organization : organizations){
            organizationDao.removeOrganization(organization.getOrganizationID());
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
//    public void addOrganizationTest() throws PersistenceException{
//        Organization organization = new Organization();
//        organization.setOrgName("Wumbo");
//        organization.setOrgDescription("Wumbology, the study of Wumbo");
//        organization.setOrgContactInfo("Get lost.");
//        organization.setIsHeroOrg(Boolean.FALSE);
//        organizationDao.addOrganization(organization);
//        int orgNum = organization.getOrganizationID();
//        Organization result = organizationDao.getOrganization(orgNum);
//        assertEquals(organization, result);
//    }
//    
//    @Test
//    public void getOrganizationListTest() throws PersistenceException{
//        Organization organization = new Organization();
//        organization.setOrgName("Wumbo");
//        organization.setOrgDescription("Wumbology, the study of Wumbo");
//        organization.setOrgContactInfo("Get lost.");
//        organization.setIsHeroOrg(Boolean.FALSE);
//        organizationDao.addOrganization(organization);
//        Organization organizationTwo = new Organization();
//        organizationTwo.setOrgName("Wumbo");
//        organizationTwo.setOrgDescription("Wumbology, the study of Wumbo");
//        organizationTwo.setOrgContactInfo("Get lost.");
//        organizationTwo.setIsHeroOrg(Boolean.FALSE);
//        organizationDao.addOrganization(organizationTwo);
//        int expected = 2;
//        int result = organizationDao.getOrganizationList().size();
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void removeOrganizationTest() throws PersistenceException{
//        Organization organization = new Organization();
//        organization.setOrgName("Wumbo");
//        organization.setOrgDescription("Wumbology, the study of Wumbo");
//        organization.setOrgContactInfo("Get lost.");
//        organization.setIsHeroOrg(Boolean.FALSE);
//        organizationDao.addOrganization(organization);
//        int listSize = organizationDao.getOrganizationList().size();
//        int expected = 1;
//        assertEquals(listSize, expected);
//        organizationDao.removeOrganization(organization.getOrganizationID());
//        int nextSize = organizationDao.getOrganizationList().size();
//        int nextExpected = 0;
//        assertEquals(nextSize, nextExpected);
//                
//    }
    
    
}
