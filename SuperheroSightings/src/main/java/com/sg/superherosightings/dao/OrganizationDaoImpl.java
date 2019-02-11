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
import sg.dontdiejustkode.superherosightingsgroupwork.dao.OrganizationDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.PersistenceException;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Organization;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.Person;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;



/**
 *
 * @author Dan's Laptop
 */
public class OrganizationDaoImpl implements OrganizationDao{
    
    private static final String SQL_ADD_ORGANIZATION
            = "insert into org "
            + "(OrgName, OrgDescription, OrgContactInfo, IsHeroOrg) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_DELETE_ORGANIZATION
            = "delete from org where OrganizationID = ?";
    private static final String SQL_SELECT_ORGANIZATION
            = "select * from org where OrganizationID = ?";
    private static final String SQL_UPDATE_ORGANIZATION
            = "update org set "
            + "OrgName = ?, OrgDescription = ?, OrgContactInfo = ?, IsHeroOrg = ? "
            + "where OrganizationID = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from org";
    private static final String SQL_SELECT_ORGANIZATION_BY_PERSON_ID
            = "select o.* from Org o join PersonOrganization po on o.OrganizationID = po.OrganizationID where po.PersonID = ?";
    private static final String SQL_DELETE_PERSON_ORGANIZATION_BY_ORGANIZATION_ID
            = "delete from PersonOrganization where OrganizationID = ?";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
  
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_ADD_ORGANIZATION,
                organization.getOrgName(),
                organization.getOrgDescription(),
                organization.getOrgContactInfo(),
                organization.getIsHeroOrg());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        organization.setOrganizationID(id);
    }

    @Override
    public Organization getOrganization(int organizationID) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationID);
    }

    @Override
    public List<Organization> getOrganizationList() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrgName(),
                organization.getOrgDescription(),
                organization.getOrgContactInfo(),
                organization.getIsHeroOrg(),
                organization.getOrganizationID());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeOrganization(int organizationID) {
        deletePersonOrganizationByOrganizationID(organizationID);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationID);
    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personID) throws PersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_PERSON_ID, new OrganizationMapper(), personID);
    }
    
    private void deletePersonOrganizationByOrganizationID(int organizationID){
        jdbcTemplate.update(SQL_DELETE_PERSON_ORGANIZATION_BY_ORGANIZATION_ID, organizationID);
    }

    @Override
    public List<User> getAllAdminsForOrganization(int organizationID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAllOrganizationsForAdmin(int userID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization>{

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            organization.setOrgName(rs.getString("OrgName"));
            organization.setOrgDescription(rs.getString("OrgDescription"));
            organization.setOrgContactInfo(rs.getString("OrgContactInfo"));
            organization.setIsHeroOrg(rs.getBoolean("IsHeroOrg"));
            return organization;
        }
        
    }
}
