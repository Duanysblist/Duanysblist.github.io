/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersonDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;


/**
 *
 * @author Dan's Laptop
 */
public class PersonDaoImpl implements PersonDao{
    
    private static final String SQL_ADD_PERSON
            = "insert into person "
            + "(PersonName, PersonDescription, isHero) "
            + "values (?, ?, ?)";
    private static final String SQL_DELETE_PERSON
            = "delete from person where PersonID = ?";
    private static final String SQL_SELECT_PERSON
            = "select * from person where PersonID = ?";
    private static final String SQL_UPDATE_PERSON
            = "update person set "
            + "PersonName = ?, PersonDescription = ?, isHero = ? "
            + "where PersonID = ?";
    private static final String SQL_SELECT_ALL_PEOPLE
            = "select * from person";
    private static final String SQL_SELECT_PERSON_BY_LOCATION_ID //I dont think this should be here or exist. Get sighting by location ID, but not a person
            = "select * from person where LocationID = ?";
    private static final String SQL_SELECT_PERSON_BY_ORGANIZATION_ID
            = "select p.* from Person p join PersonOrganization po on p.PersonID = po.PersonID where po.OrganizationID = ?";
    private static final String SQL_SELECT_PERSON_BY_SUPERPOWER_ID
            = "select p.* from Person p join PersonSuperpower ps on p.PersonID = ps.PersonID where SuperpowerID = ?";
    private static final String SQL_SELECT_PERSON_BY_SIGHTING_ID
            = "select p.* from Person p join Sightings s on p.PersonID = s.PersonID where s.SightingsID = ?";
    
    private static final String SQL_ADD_PERSON_SUPER_POWER = "insert into PersonSuperpower (PersonID, SuperpowerID) values (?,?)";
    private static final String SQL_DELETE_PERSON_SUPER_POWER = "delete from PersonSuperpower where PersonID = ? and SuperpowerID = ?";
    private static final String SQL_DELETE_PERSON_SUPER_POWER_BY_PERSON_ID = "delete from PersonSuperpower where PersonID = ?";
    
    private static final String SQL_ADD_PERSON_ORGANIZATION = "insert into PersonOrganization (PersonID, OrganizationID) values (?,?)";
    private static final String SQL_DELETE_PERSON_ORGANIZATION = "delete from PersonOrganization where PersonID = ? and OrganizationID = ?";
    private static final String SQL_DELETE_PERSON_ORGANIZATION_BY_PERSON_ID = "delete from PersonOrganization where PersonID = ?";
    
    private static final String SQL_DELETE_SIGHTING_BY_PERSON_ID = "delete from Sightings where PersonID = ?";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPerson(Person person) throws PersistenceException {
        jdbcTemplate.update(SQL_ADD_PERSON,
                person.getPersonName(),
                person.getPersonDescription(),
                person.getIsHero());
        
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        person.setPersonID(newId);
        
       List<Superpower> powersToAddToBridge = person.getListOfSuperpowers();
       for(Superpower iHaveThePower : powersToAddToBridge){
           int currentSuperpowerID = iHaveThePower.getSuperpowerID();
           addPersonSuperpower(person.getPersonID(), currentSuperpowerID);
       }
       List<Organization> orgsToAddToBridge = person.getListofOrganizations();
       for(Organization weHaveTheOrgs : orgsToAddToBridge){
           int currentOrganizationID = weHaveTheOrgs.getOrganizationID();
           addPersonOrganization(person.getPersonID(), currentOrganizationID);
       }

    }

    @Override
    public Person getPerson(int personID) {
       return jdbcTemplate.queryForObject(SQL_SELECT_PERSON, new PersonMapper(),personID);
    }

    @Override
    public List<Person> getPersonList() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PEOPLE, new PersonMapper());
    }

    @Override
    public void updatePerson(Person person) throws PersistenceException {
        jdbcTemplate.update(SQL_UPDATE_PERSON,
                person.getPersonName(),
                person.getPersonDescription(),
                person.getIsHero(),             
                person.getPersonID());
        
       List<Superpower> powersToAddToBridge = person.getListOfSuperpowers();
       for(Superpower iHaveThePower : powersToAddToBridge){
           int currentSuperpowerID = iHaveThePower.getSuperpowerID();
           addPersonSuperpower(person.getPersonID(), currentSuperpowerID);
       }
       List<Organization> orgsToAddToBridge = person.getListofOrganizations();
       for(Organization weHaveTheOrgs : orgsToAddToBridge){
           int currentOrganizationID = weHaveTheOrgs.getOrganizationID();
           addPersonOrganization(person.getPersonID(), currentOrganizationID);
       }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removePerson(int personID) {
        deletePersonOrganizationByPersonID(personID);
        deleteSightingByPersonID(personID);
        deletePersonSuperPowerByPersonID(personID);
        jdbcTemplate.update(SQL_DELETE_PERSON, personID);
    }

    @Override
    public void addPersonOrganization(int personID, int organizationID) throws PersistenceException {
        jdbcTemplate.update(SQL_ADD_PERSON_ORGANIZATION, personID, organizationID);
    }

    @Override
    public void addPersonSuperpower(int personID, int powerID) throws PersistenceException {
        jdbcTemplate.update(SQL_ADD_PERSON_SUPER_POWER, personID, powerID);
    }

    @Override
    public List<Person> getPeopleByLocationID(int locationID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATION_ID, new PersonMapper(), locationID);
    }

    @Override
    public List<Person> getPeopleByOrganizationID(int organizationID) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_ORGANIZATION_ID, new PersonMapper(), organizationID);
    }

    @Override
    public void removePersonOrganization(int personID, int organizationID) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_PERSON_ORGANIZATION, personID, organizationID);
    }
    
    private void deletePersonOrganizationByPersonID(int personID){
        jdbcTemplate.update(SQL_DELETE_PERSON_ORGANIZATION_BY_PERSON_ID, personID);
    }

    @Override
    public void removePersonSuperpower(int personID, int powerID) throws PersistenceException {
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPER_POWER, personID, powerID);
    }
    
    private void deletePersonSuperPowerByPersonID(int personID) {
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPER_POWER_BY_PERSON_ID, personID);
    }
    
    private void deleteSightingByPersonID(int personID){
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_PERSON_ID, personID);
    }

    @Override
    public List<Person> getPeopleBySuperpowerID(int superpowerID) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_SUPERPOWER_ID, new PersonMapper(), superpowerID);
    }

    @Override
    public Person getPersonBySightingID(int sightingsID) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_PERSON_BY_SIGHTING_ID, new PersonMapper(), sightingsID);
    }
    
    private static final class PersonMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int i) throws SQLException {
            Person person = new Person();
            person.setPersonID(rs.getInt("personID"));
            person.setPersonName(rs.getString("personName"));
            person.setPersonDescription(rs.getString("personDescription"));
            person.setIsHero(rs.getBoolean("isHero"));
            return person;
        }
        
    }
    
}
