package business;

import dataaccess.StudentGroupMatchDAO;
import dataaccess.StudentGroupMatchDAOImpl;
import java.util.List;
import dto.StudentGroupMatch;
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

    public void addMatch(Map<String, String[]> match) {
        StudentGroupMatch m = new StudentGroupMatch(Integer.parseInt(match.get(StudentGroupMatch.COL_GROUP_ID)[0]), Integer.parseInt(match.get(StudentGroupMatch.COL_STUDENT_ID)[0]));
        studentGroupMatchDAO.addMatch(m);
    }
    
    public StudentGroupMatch getMatchByStudentID(int id) {
        return studentGroupMatchDAO.getMatchByStudentID(id);
    }

}
