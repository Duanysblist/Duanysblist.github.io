/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.SuperpowerDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Superpower;




/**
 *
 * @author Dan's Laptop
 */
public class SuperpowerDaoImpl implements SuperpowerDao{
    
    private static final String SQL_ADD_SUPER_POWER = "insert into Superpower (SuperpowerName) values (?)";
    private static final String SQL_GET_SUPER_POWER = "select * from Superpower where SuperpowerID = ?";
    private static final String SQL_GET_ALL_SUPER_POWERS = "select * from Superpower";
    private static final String SQL_GET_SUPER_POWERS_BY_PERSON_ID 
            = "select s.* from Superpower s join PersonSuperpower ps on s.SuperpowerID = ps.SuperpowerID where ps.PersonID = ?";
    private static final String SQL_UPDATE_SUPER_POWER = "update Superpower set SuperpowerName = ? where SuperpowerID = ?";
    private static final String SQL_DELETE_SUPER_POWER = "delete from Superpower where SuperpowerID = ?";
    
    private static final String SQL_DELETE_PERSON_SUPER_POWER_BY_SUPER_POWER_ID = "delete from PersonSuperpower where SuperpowerID = ?";
    
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Superpower superpower) {
        jdbcTemplate.update(SQL_ADD_SUPER_POWER, superpower.getSuperpowerName());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        superpower.setSuperpowerID(id);
    }

    @Override
    public Superpower getPower(int powerID) {
        return jdbcTemplate.queryForObject(SQL_GET_SUPER_POWER, new SuperpowerMapper(), powerID);
    }

    @Override
    public List<Superpower> getSuperpowerList() {
        return jdbcTemplate.query(SQL_GET_ALL_SUPER_POWERS, new SuperpowerMapper());
    }

    @Override
    public void updatePower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPER_POWER, superpower.getSuperpowerName(), superpower.getSuperpowerID());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removePower(int powerID) {
        deletePersonSuperpowerBySuperpowerID(powerID);
        jdbcTemplate.update(SQL_DELETE_SUPER_POWER, powerID);
    }

    @Override
    public List<Superpower> getPowersByPersonID(int personID) throws PersistenceException {
        try {
            return jdbcTemplate.query(SQL_GET_SUPER_POWERS_BY_PERSON_ID, new SuperpowerMapper(), personID);
        } catch (EmptyResultDataAccessException dataAccessException) {
            return null;
        }
    }
    
    private void deletePersonSuperpowerBySuperpowerID(int superpowerID){
        jdbcTemplate.update(SQL_DELETE_PERSON_SUPER_POWER_BY_SUPER_POWER_ID, superpowerID);
    }
    
    private static final class SuperpowerMapper implements RowMapper<Superpower>{

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerID(rs.getInt("SuperpowerID"));
            superpower.setSuperpowerName(rs.getString("SuperpowerName"));
            return superpower;
        }
        
    }
}
