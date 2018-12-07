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
 *
 * @author Claire
 */
public class GroupFactory extends  AbstractFactory<Group>{

    @Override
    public Group createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setId(rs.getInt(Group.ID))
                .setName(rs.getString(Group.NAME))
                ;
        return builder.get();
    }


    @Override
    public List createListFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
    
    
    @Override
    public Group createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        GroupBuilder builder = new GroupBuilder();
        builder.setId(Integer.valueOf(map.get(Group.ID)[0]))
                .setName(map.get(Group.NAME)[0]);
        return builder.get();
    }
    
}

    
    
    

