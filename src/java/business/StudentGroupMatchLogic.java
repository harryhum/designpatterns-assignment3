package business;

import dataaccess.StudentGroupMatchDAO;
import dataaccess.StudentGroupMatchDAOImpl;
import java.util.List;
import dto.StudentGroupMatch;
import dto.factory.StudentGroupMatchFactory;
import java.util.Map;

/**
 * Business logic class for StudentGroupMatch
 * 
 * @author Harry Hum
 */
public class StudentGroupMatchLogic {

    /**
     * StudentGroupMatchDAO object
     */
    private StudentGroupMatchDAO studentGroupMatchDAO = null;

    /**
     * Constructor initializes StudentGroupMatchDAO object
     */
    public StudentGroupMatchLogic() {
        studentGroupMatchDAO = new StudentGroupMatchDAOImpl();
    }

    /**
     * Gets all StudentGroupMatch from the DAO
     * @return a list of StudentGroupMatch
     */
    public List<StudentGroupMatch> getAllMatches() {
        return studentGroupMatchDAO.getAllMatches();
    }

    /**
     * Adds a StudentGroupMatch through the DAO
     * @param match - a map of the arguments passed by the servlet POST request 
     */
    public void addMatch(Map<String, String[]> match) {
        StudentGroupMatchFactory factory = new StudentGroupMatchFactory();
        studentGroupMatchDAO.addMatch(factory.createFromMap(match));
    }
    
    /**
     * Gets the StudentGroupMatch which corresponds to the given student id
     * @param id - student id
     * @return a StudentGroupMatch object
     */
    public StudentGroupMatch getMatchByStudentID(int id) {
        return studentGroupMatchDAO.getMatchByStudentID(id);
    }

}
