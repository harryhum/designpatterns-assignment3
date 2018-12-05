package business;

import dataaccess.StudentGroupMatchDAO;
import dataaccess.StudentGroupMatchDAOImpl;
import java.util.List;
import dto.StudentGroupMatch;
import dto.factory.StudentGroupMatchFactory;
import java.util.Map;

/**
 *
 * @course Shahriar Emami
 * @author Stanley Pieda
 */
public class StudentGroupMatchLogic {

    private StudentGroupMatchDAO studentGroupMatchDAO = null;

    public StudentGroupMatchLogic() {
        studentGroupMatchDAO = new StudentGroupMatchDAOImpl();
    }

    public List<StudentGroupMatch> getAllMatches() {
        return studentGroupMatchDAO.getAllMatches();
    }

    //use builder
    public void addMatch(Map<String, String[]> match) {
        StudentGroupMatchFactory factory = new StudentGroupMatchFactory();
        studentGroupMatchDAO.addMatch(factory.createFromMap(match));
    }
    
    public StudentGroupMatch getMatchByStudentID(int id) {
        return studentGroupMatchDAO.getMatchByStudentID(id);
    }

}
