package business;

import dataaccess.StudentGroupMatchDAOImpl;
import java.util.List;
import dto.StudentGroupMatch;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import java.util.Map;

/**
 * Business logic class for StudentGroupMatch
 * 
 * @author Harry Hum
 */
public class StudentGroupMatchLogic {
    
    /**
     * StudentGroupMatch factory 
     */
    private final Factory<StudentGroupMatch> factory = DTOFactoryCreator.createFactory(StudentGroupMatch.class);

    /**
     * StudentGroupMatchDAO object
     */
    private StudentGroupMatchDAOImpl studentGroupMatchDAO = null;

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
    public void addMatch(Map<String, String[]> match) throws ValidationException {
        StudentGroupMatch m = factory.createFromMap(match);
        validateId(m);
        studentGroupMatchDAO.addMatch(m);
    }
    
    /**
     * Gets the StudentGroupMatch which corresponds to the given student id
     * @param id - student id
     * @return a StudentGroupMatch object
     */
    public StudentGroupMatch getMatchByStudentID(int id) {
        return studentGroupMatchDAO.getMatchByStudentID(id);
    }

    private void validateId(StudentGroupMatch match) throws ValidationException {
        validateId(match.getGroupID(), StudentGroupMatch.COL_GROUP_ID);
        validateId(match.getStudentID(), StudentGroupMatch.COL_STUDENT_ID);

    }
    
     private void validateId(int id, String fieldName) throws ValidationException {
        if (id <= 0) {
            throw new ValidationException(String.format("%s cannot be less than or equal to 0", fieldName));
        } 
    }
}
