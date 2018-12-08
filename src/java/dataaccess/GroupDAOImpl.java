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

    private static final String GET_ALL_GROUPS = "SELECT id, name FROM Group ORDER BY id";
    private static final String INSERT_GROUPS = "INSERT INTO Group (id, name) VALUES(?, ?)";
    private static final String DELETE_GROUP = "DELETE FROM Group WHERE id = ?";
    private static final String DELETE_GROUPS = "DELETE FROM Group WHERE (id) IN ";
    private static final String UPDATE_GROUPS = "UPDATE Group SET name = ? WHERE id = ?";
    private static final String GET_BY_ID_GROUPS = "SELECT id, name FROM Group WHERE id = ?";

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
            pstmt.setInt(1, group.getId());
            pstmt.setString(2, group.getName());
            
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

  
    
    @Override
    public Group getById(int id) {
        Group s = null;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_GROUPS);) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            //ResultSet rs = pstmt.executeQuery()
            if (rs.next()) {
                GroupFactory factory = new GroupFactory();
                s= factory.createFromResultSet(rs);
                 
                }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return s;
    }
    
    
    
}
