/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Dan's Laptop
 */
public class VendingMachineDaoJdbcTemplateImpl implements VendingMachineDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_SELECT_ALL_ITEMS
    = "select * from items";
    
    
    private static final String SQL_VEND_ITEM
    = "update items set item_name = ?, item_cost = ?, inventory = ? where item_id =  ?";
    
    private static final String SQL_SELECT_ITEM_BY_ID
    = "select * from items where item_id = ?";
    
    private static final class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        Item it = new Item();
        it.setItemName(rs.getString("item_name"));
        it.setItemCost(rs.getBigDecimal("item_cost"));
        it.setItemInventory(rs.getInt("inventory"));
        it.setItemId(rs.getInt("item_id"));
        return it;
        }
    }
    
    

    @Override
    public void vendItem(Item item) {
        jdbcTemplate.update(SQL_VEND_ITEM,
                item.getItemName(),
                item.getItemCost(),
                item.getItemInventory(),
                item.getItemId());
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());
    }

    @Override
    public Item getItemById(int itemId) {
         try {
        return jdbcTemplate.queryForObject(SQL_SELECT_ITEM_BY_ID, 
                                           new ItemMapper(), 
                                           itemId);
    } catch (EmptyResultDataAccessException ex) {
        return null;
    }
    }
    

    

    
}
