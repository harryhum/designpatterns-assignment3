package dataaccess;

import java.util.List;
import dto.StudentGroupMatch;

/**
 * StudentGroupMatch DAO interface
 * 
 * @author Harry Hum
 */
public interface StudentGroupMatchDAO {
    	void addMatch(StudentGroupMatch match);
	List<StudentGroupMatch> getAllMatches();
        StudentGroupMatch getMatchByStudentID(int studentID);
        void updateMatch(StudentGroupMatch match);
        void deleteMatch(StudentGroupMatch match);
}
