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
import dto.factory.StudentGroupMatchFactory;
/**
 *
 * @author Shawn
 */
public class StudentGroupMatchDAOImpl implements StudentGroupMatchDAO {

    private static final String GET_ALL_MATCHES = "SELECT * FROM StudentGroupMatch";
    private static final String INSERT_MATCHES = "INSERT INTO StudentGroupMatch (group_id, student_id, date) VALUES(?, ?, ?)";
    private static final String DELETE_MATCHES = "DELETE FROM StudentGroupMatch WHERE group_id = ? AND student_id = ?";
    private static final String UPDATE_STUDENT_GROUP = "UPDATE StudentGroupMatch SET group_id WHERE student_id = ?";
    private static final String GET_BY_ID_STUDENT = "SELECT * FROM StudentGroupMatch WHERE student_id = ?";
    private static final String GET_BY_ID_GROUP = "SELECT * FROM StudentGroupMatch WHERE group_id = ?";

    @Override
    public List<StudentGroupMatch> getAllMatches() {
        List<StudentGroupMatch> matches = Collections.emptyList();
        StudentGroupMatch match;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_MATCHES);
                ResultSet rs = pstmt.executeQuery();) {
            matches = new ArrayList<>(100);            
            while (rs.next()) {
                StudentGroupMatchFactory factory = new StudentGroupMatchFactory();
                match = factory.createFromResultSet(rs);
                matches.add(match);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matches;
    }

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

    @Override
    public List<StudentGroupMatch> getMatchesByGroupID(int groupID) {
        List<StudentGroupMatch> matches = Collections.emptyList();
        StudentGroupMatch match;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_GROUP);
                ResultSet rs = pstmt.executeQuery();) {
            matches = new ArrayList<>(100);
            while (rs.next()) {
                StudentGroupMatchFactory factory = new StudentGroupMatchFactory();
                match = factory.createFromResultSet(rs);
                matches.add(match);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matches;
    }

    @Override
    public StudentGroupMatch getMatchByStudentID(int studentID) {
        StudentGroupMatch match = null;
        try (Connection con = DataSource.createConnection()){
                try (PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_STUDENT)) {
                    pstmt.setInt(1, studentID);
                    try(ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            StudentGroupMatchFactory factory = new StudentGroupMatchFactory();
                            match = factory.createFromResultSet(rs);
                    }
                }            
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentGroupMatchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return match;
    }

    @Override
    public void updateMatch(StudentGroupMatch match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMatch(StudentGroupMatch match) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
