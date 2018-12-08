package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.Group;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import dto.factory.GroupFactory;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Claire
 */
public class GroupDAOImpl implements DAOInterface<Group> {

    /**
     * strings for DB query/insert
     */
    private static final String GET_ALL_GROUPS = "SELECT id, name FROM Mapmaker.Group ORDER BY id";
    private static final String INSERT_GROUPS = "INSERT INTO MapMaker.Group (name) VALUES (?)";
    private static final String DELETE_GROUP = "DELETE FROM Mapmaker.Group WHERE id = ?";
    private static final String DELETE_GROUPS = "DELETE FROM Mapmaker.Group WHERE (id) IN ";
    private static final String UPDATE_GROUPS = "UPDATE Mapmaker.Group SET name = ? WHERE id = ?";
    private static final String GET_BY_ID_GROUPS = "SELECT id, name FROM Mapmaker.Group WHERE id = ?";

    private final Factory<Group> factory = DTOFactoryCreator.createFactory(Group.class);

    /**
     * 
     * @return all the groups in the db
     */
    @Override
    public List<Group> getAll() {
        List<Group> groups = Collections.emptyList();
        Group group;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_GROUPS);
                ResultSet rs = pstmt.executeQuery();) {
            //groups = factory.createListFromResultSet(rs);
            groups = new ArrayList<>(100);
            while(rs.next()){
                group = new Group();
                group.setId(rs.getInt(Group.ID));
                group.setName(rs.getString(Group.NAME));
                
                groups.add(group);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    /**
     * 
     * @param group to add to the db
     */
    @Override
    public void add(Group group) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_GROUPS);) {
           
            pstmt.setString(1, group.getName());
            
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * removes one student
     * @param id of student to delete
     */
    @Override
    public void delete(String id) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_GROUP);) {
            pstmt.setInt(1, Integer.valueOf(id));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param str array of IDs to delete
     */
    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_GROUPS);
        query.append("(");
        String delimiter = "";
        for (int i = 0; i < str.length; i++) {
            query.append(delimiter).append("(?)");
            delimiter = ",";
        }
        query.append(")");
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query.toString());) {
            for (int i = 0; i < str.length; i++) {
                pstmt.setInt(i + 1, Integer.valueOf(str[i]));
            }
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    /**
     * 
     * @param id to query for
     * @return group with given ID, or null if no such group
     */
    
    @Override
    public Group getById(String id) {
        Group s = null;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_GROUPS);) {
            pstmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = pstmt.executeQuery();
            
            //ResultSet rs = pstmt.executeQuery()
            if (rs.next()) {
                GroupFactory factory = new GroupFactory();
                s= factory.createFromResultSet(rs, rs.getInt(Group.ID));
                 
                }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return s;
    }
    
    
    
}
