/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import dto.StudentGroupMatch;

/**
 *
 * @author Shawn
 */
public interface StudentGroupMatchDAO {
    	void addMatch(StudentGroupMatch match);
	List<StudentGroupMatch> getAllMatches();
        List<StudentGroupMatch> getMatchesByGroupID(int groupID);
        StudentGroupMatch getMatchByStudentID(int studentID);
        void updateMatch(StudentGroupMatch match);
        void deleteMatch(StudentGroupMatch match);
}
