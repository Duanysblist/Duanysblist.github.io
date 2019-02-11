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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.LocationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Location;



/**
 *
 * @author Dan's Laptop
 */
public class LocationDaoImpl implements LocationDao{
    
    private static final String SQL_ADD_LOCATION
            = "insert into location "
            + "(LocationName, LocationDescription, LocationAddress, LocationLatitude, LocationLongitude) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_LOCATION
            = "delete from location where LocationID = ?";
    private static final String SQL_SELECT_LOCATION
            = "select * from location where LocationID = ?";
    private static final String SQL_UPDATE_LOCATION
            = "update location set "
            + "LocationName = ?, LocationDescription = ?, LocationAddress = ?, LocationLatitude = ?, LocationLongitude = ? " 
            + "where LocationID = ?";
    private static final String SQL_SELECT_ALL_LOCATIONS = "select * from Location order by Location.LocationName";
    private static final String SQL_SELECT_LOCATION_BY_PERSON_ID
            = "select distinct l.* from Location l join Sightings s on l.LocationID = s.LocationID where s.PersonID = ?";
    
    private static final String SQL_DELETE_SIGHTING_BY_LOCATION_ID = "delete from Sightings where LocationID = ?";
    
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select l.* from Location l join Sightings s on l.LocationID = s.LocationID where s.SightingsID = ?";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_ADD_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationLatitude(),
                location.getLocationLongitude());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        location.setLocationID(id);
    }

    @Override
    public Location getLocation(int locationID) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationID);
    }

    @Override
    public List<Location> getLocationList() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationAddress(),
                location.getLocationLatitude(),
                location.getLocationLongitude(),
                location.getLocationID());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeLocation(int locationID) {
        deleteSightingByLocationID(locationID);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationID);
    }

    @Override
    public List<Location> getLocationsByPersonID(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_LOCATION_BY_PERSON_ID, new LocationMapper(), personID);
    }
    
    private void deleteSightingByLocationID(int locationID) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION_ID, locationID);
    }

    @Override
    public Location getLocationBySightingID(int sightingsID) throws PersistenceException {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingsID);
        
    }
    
    private static final class LocationMapper implements RowMapper<Location>{

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setLocationAddress(rs.getString("LocationAddress"));
            location.setLocationLatitude(rs.getBigDecimal("LocationLatitude"));
            location.setLocationLongitude(rs.getBigDecimal("LocationLongitude"));
            return location;
        }
        
    }

}
