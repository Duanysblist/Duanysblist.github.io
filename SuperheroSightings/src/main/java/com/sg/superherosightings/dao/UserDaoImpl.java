/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sg.dontdiejustkode.superherosightingsgroupwork.dao.UserDao;
import sg.dontdiejustkode.superherosightingsgroupwork.dto.User;

/**
 *
 * @author Dan's Laptop
 */
public class UserDaoImpl implements UserDao{
    
    private static final String SQL_INSERT_USER
        = "insert into users (userName, userPassword, isAdmin) values (?, ?, 1)";
    private static final String SQL_INSERT_AUTHORITY
        = "insert into authorities (username, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
        = "delete from users where userName = ?";
    private static final String SQL_DELETE_AUTHORITIES
        = "delete from authorities where username = ?";
    private static final String SQL_GET_ALL_USERS
        = "select * from users";
    private static final String SQL_GET_USER
        = "select * from users where Username = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User newUser) {
                jdbcTemplate.update(SQL_INSERT_USER, 
                            newUser.getUserName(), 
                            newUser.getUserPassword());
        newUser.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                                   Integer.class));

        // now insert user's roles
        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                                newUser.getUserName(), 
                                authority);
        }

        return newUser;
    }

    @Override
    public User getUser(String userName) {
        return jdbcTemplate.queryForObject(SQL_GET_USER, new UserMapper(), userName);
    }

    @Override
    public List<User> getUserList() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    public void editUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String userName) {
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, userName);
        // second delete the user
        jdbcTemplate.update(SQL_DELETE_USER, userName);
    }
    
    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userID"));
            user.setUserName(rs.getString("userName"));
            user.setUserPassword(rs.getString("userPassword"));
            return user;
        }
    }
    
}
