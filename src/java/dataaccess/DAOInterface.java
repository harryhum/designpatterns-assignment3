package dataaccess;

import java.util.List;

/**
 * StudentGroupMatch DAO interface
 * 
 * @author Harry Hum
 */
public interface DAOInterface<T> {
    	void addMatch(T match);
	List<T> getAllMatches();
        T getMatchByStudentID(int studentID);
        void updateMatch(T match);
        void deleteMatch(T match);
}
