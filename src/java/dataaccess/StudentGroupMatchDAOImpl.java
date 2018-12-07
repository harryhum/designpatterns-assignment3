package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.StudentGroupMatch;
import java.sql.Date;
import javax.naming.NamingException;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;


/**
 * Implementation of StudentGroupMatchDAO
 * 
 * @author Harry Hum
 */
public class StudentGroupMatchDAOImpl implements DAOInterface<StudentGroupMatch> {
    
    /**
     * StudentGroupMatch factory 
     */
    private final Factory<StudentGroupMatch> factory = DTOFactoryCreator.createFactory(StudentGroupMatch.class);

    /**
     * SQL query strings
     */
    private static final String GET_ALL_MATCHES = "SELECT * FROM StudentGroupMatch";
    private static final String INSERT_MATCHES = "INSERT INTO StudentGroupMatch (group_id, student_id, date) VALUES(?, ?, ?)";
    private static final String DELETE_MATCHES = "DELETE FROM StudentGroupMatch WHERE group_id = ? AND student_id = ?";
    private static final String UPDATE_STUDENT_GROUP = "UPDATE StudentGroupMatch SET group_id WHERE student_id = ?";
    private static final String GET_BY_ID_STUDENT = "SELECT * FROM StudentGroupMatch WHERE student_id = ?";
    private static final String GET_BY_ID_GROUP = "SELECT * FROM StudentGroupMatch WHERE group_id = ?";

    /**
     * Gets all matches in the database
     * @return list of StudentGroupMatch
     */
    @Override
    public List<StudentGroupMatch> getAllMatches() {
        List<StudentGroupMatch> matches = Collections.emptyList();
        StudentGroupMatch match;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_MATCHES);
                ResultSet rs = pstmt.executeQuery();) {
            matches = new ArrayList<>(100);            
            while (rs.next()) {
                match = factory.createFromResultSet(rs);
                matches.add(match);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matches;
    }

    /**
     * Adds a match to the database
     * @param match 
     */
    @Override
    public void addMatch(StudentGroupMatch match) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_MATCHES);) {
            pstmt.setInt(1, match.getGroupID());
            pstmt.setInt(2, match.getStudentID());
            pstmt.setDate(3, (Date) match.getDate());
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets a match by student id
     * @param studentID
     * @return StudentGroupMatch object
     */
    @Override
    public StudentGroupMatch getMatchByStudentID(int studentID) {
        StudentGroupMatch match = null;
        try (Connection con = DataSource.createConnection()){
                try (PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_STUDENT)) {
                    pstmt.setInt(1, studentID);
                    try(ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            match = factory.createFromResultSet(rs);
                    }
                }            
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return match;
    }

    /**
     * Unimplemented method
     * @param match 
     */
    @Override
    public void updateMatch(StudentGroupMatch match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Delete match from database
     * @param match 
     */
    @Override
    public void deleteMatch(StudentGroupMatch match) {
        try (Connection con = DataSource.createConnection()) {
            PreparedStatement pstmt = con.prepareStatement(DELETE_MATCHES); 
            pstmt.setInt(1, match.getGroupID());
            pstmt.setInt(2, match.getStudentID());
            pstmt.executeQuery();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
