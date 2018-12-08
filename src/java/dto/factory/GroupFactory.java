/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.factory;

import dto.Group;
import dto.builder.GroupBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * hides creation of groups via the builder
 * @author Claire
 */
public class GroupFactory extends  AbstractFactory<Group>{

    /**
     * 
     * @param rs from which to pull info for group
     * @return created group
     * @throws SQLException 
     */
    @Override
    public Group createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setName(rs.getString(Group.NAME))//.setId(rs.getInt(Group.ID))
                ;
        return builder.get();
    }
    
    
        public Group createFromResultSet(ResultSet rs, int id) throws SQLException {
            if (rs == null) {
                return null;
            }
            GroupBuilder builder = new GroupBuilder();
            builder.setName(rs.getString(Group.NAME)).setId(rs.getInt(Group.ID))
                    ;
            return builder.get();
        }


    @Override
    public List createListFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
    /**
     * 
     * @param map from which to pull info for group
     * @return created group
     */
    @Override
    public Group createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setName(map.get(Group.NAME)[0]);
                //setId(Integer.valueOf(map.get(Group.ID)[0]))
                
        return builder.get();
    }
    
}

    
    
    

