package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.Student;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import dto.factory.StudentFactory;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Claire
 */
public class StudentDAOImpl implements FSGMDAOInterface<Student> {

    private static final String GET_ALL_STUDENTS = "SELECT id, first_name, last_name FROM Student ORDER BY id";
    private static final String INSERT_STUDENTS = "INSERT INTO Student (id, first_name, last_name) VALUES(?, ?, ?)";
    private static final String DELETE_STUDENT = "DELETE FROM Student WHERE id = ?";
    private static final String DELETE_STUDENTS = "DELETE FROM Student WHERE (id) IN ";
    private static final String UPDATE_STUDENTS = "UPDATE Student SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String GET_BY_ID_STUDENTS = "SELECT id, first_name, last_name FROM Student WHERE id = ?";

    private final Factory<Student> factory = DTOFactoryCreator.createFactory(Student.class);

    @Override
    public List<Student> getAll() {
        List<Student> students = Collections.emptyList();
        Student student;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_STUDENTS);
                ResultSet rs = pstmt.executeQuery();) {
            //students = factory.createListFromResultSet(rs);
            students = new ArrayList<>(100);
            while(rs.next()){
                student = new Student();
                student.setId(rs.getInt(Student.ID));
                student.setfName(rs.getString(Student.FIRST_NAME));
                student.setlName(rs.getString(Student.LAST_NAME));
                students.add(student);
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public void add(Student student) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_STUDENTS);) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getfName());
            pstmt.setString(3, student.getlName());
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String code) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_STUDENT);) {
            pstmt.setInt(1, Integer.valueOf(code));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(String[] str) {
        StringBuilder query = new StringBuilder(DELETE_STUDENTS);
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
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Student getById(int id) {
        Student s = null;
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_BY_ID_STUDENTS);) {
            pstmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = pstmt.executeQuery();
            
            //ResultSet rs = pstmt.executeQuery()
            if (rs.next()) {
                StudentFactory factory = new StudentFactory();
                s= factory.createFromResultSet(rs);
                 
                }
            
            
            /*Student s = new Student();
            s.setId(rs.getInt(Student.ID));
            s.setfName(rs.getString(Student.FIRST_NAME));
            s.setlName(rs.getString(Student.LAST_NAME));
            return s;
            */
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return s;
    }
}
