/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SightingDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Sighting;



/**
 *
 * @author Dan's Laptop
 */
public class SightingsDaoImpl implements SightingDao{
    
    private static final String SQL_ADD_SIGHTING 
            = "insert into Sightings (LocationID, SightingsDate, PersonID) values (?,?,?)";
    private static final String SQL_GET_SIGHTING 
            = "select Person.*, Location.*, Sightings.SightingsID, Sightings.SightingsDate from Location "
            + "inner join Sightings on Location.LocationID = Sightings.LocationID "
            + "inner join Person on Person.PersonID = Sightings.PersonID where Sightings.SightingsID = ?";
    private static final String SQL_GET_ALL_SIGHTINGS
            = "select Person.*, Location.*, Sightings.SightingsID, Sightings.SightingsDate from Location "
            + "inner join Sightings on Location.LocationID = Sightings.LocationID "
            + "inner join Person on Person.PersonID = Sightings.PersonID";
    private static final String SQL_GET_FIRST_TEN_SIGHTINGS
            = "select Person.*, Location.*, Sightings.SightingsID, Sightings.SightingsDate from Location "
            + "inner join Sightings on Location.LocationID = Sightings.LocationID "
            + "inner join Person on Person.PersonID = Sightings.PersonID order by Sightings.SightingsDate desc limit 0,10";
    private static final String SQL_GET_SIGHTINGS_BY_DATE
            = "select * from Sightings where SightingsDate = ?";
    private static final String SQL_GET_SIGHTINGS_BY_LOCATION
            =  "select * from Sightings where LocationID = ?";
    //Select all person and location related columns as well as SightingsID and SightingsDate in the Location table
    //Location ID in the Location table is the same value a the LocationID in the sightings table
    //Person Id on the Person table is the same value as PersonID on the sightings table
    private static final String SQL_GET_SIGHTINGS_BY_PERSON
            = "select s.SightingsID, s.LocationID, s.SightingsDate from Sightings s join Person p where s.PersonID = p.PersonID";
    private static final String SQL_UPDATE_SIGHTING
            = "update Sightings set LocationID = ?, SightingsDate = ?, PersonID = ? where SightingsID = ?";
    private static final String SQL_DELETE_SIGHTING
            = "delete from Sightings where SightingsID = ?";
    private static final String SQL_SELECT_PERSON_ID_FROM_SIGHTINGS_BY_LOCATION_ID
            = "select s.PersonID from Sightings s where LocationID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_ADD_SIGHTING,
                sighting.getLocation().getLocationID(),
                sighting.getSightingsDate().toString(),
                sighting.getPerson().getPersonID());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        sighting.setSightingsID(id);
    }

    @Override
    public List<Sighting> getSightingList() {
        return jdbcTemplate.query(SQL_GET_ALL_SIGHTINGS, new SightingsMapper());
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, sighting.getLocation().getLocationID(), sighting.getSightingsDate().toString(), sighting.getPerson().getPersonID(), sighting.getSightingsID());
    }

    @Override
    public void removeSighting(int sightingID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingID);
    }

    @Override
    public Sighting getSightingByID(int sightingID) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_GET_SIGHTING, new SightingsMapper(), sightingID);
    }

    @Override
    public List<Sighting> getSightingByLocation(int locationID) throws PersistenceException {
        return jdbcTemplate.query(SQL_GET_SIGHTINGS_BY_LOCATION, new SightingsMapper(), locationID);
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate date) throws PersistenceException {
        return jdbcTemplate.query(SQL_GET_SIGHTINGS_BY_DATE, new SightingsMapper(), date.toString());
    }

    @Override
    public List<Sighting> getFirstTenSightings() throws PersistenceException {
        return jdbcTemplate.query(SQL_GET_FIRST_TEN_SIGHTINGS, new SightingsMapper());
    }

    @Override
    public List<Sighting> getSightingByPerson(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_GET_SIGHTINGS_BY_PERSON, new SightingsMapper());
    }
    
    
    private static final class SightingsMapper implements RowMapper<Sighting>{

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Person person = new Person();
            person.setPersonID(rs.getInt("PersonID"));
//            person.setPersonName(rs.getString("PersonName"));
//            person.setPersonDescription(rs.getString("PersonDescription"));
//            person.setIsHero(rs.getBoolean("isHero"));
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
//            location.setLocationName(rs.getString("LocationName"));
//            location.setLocationDescription(rs.getString("LocationDescription"));
//            location.setLocationAddress(rs.getString("LocationAddress"));
//            location.setLocationLatitude(rs.getBigDecimal("LocationLatitude"));
//            location.setLocationLongitude(rs.getBigDecimal("LocationLongitude"));
            Sighting sighting = new Sighting();
            sighting.setSightingsID(rs.getInt("SightingsID"));
            sighting.setPerson(person);
            sighting.setLocation(location);
            sighting.setSightingsDate(LocalDate.parse(rs.getDate("SightingsDate").toString()));
            return sighting;
            
        }
        
    }
}
