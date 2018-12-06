package dto.builder;

import dto.StudentGroupMatch;
import java.sql.Date;

/**
 * Builder for StudentGroupMatch DTO
 * 
 * @author Harry Hum
 */
public class StudentGroupMatchBuilder {

    /**
     * StudentGroupMatch object
     */
    private final StudentGroupMatch studentGroupMatch = new StudentGroupMatch();

    /**
     * Set group id of StudentGroupMatch
     * @param groupID
     * @return StudentGroupMatch object
     */
    public StudentGroupMatchBuilder setGroupID(int groupID) {
        studentGroupMatch.setGroupID(groupID);
        return this;
    }

    /**
     * Set student id of StudentGroupMatch
     * @param studentID
     * @return StudentGroupMatch object
     */
    public StudentGroupMatchBuilder setStudentID(int studentID) {
        studentGroupMatch.setStudentID(studentID);
        return this;
    }

    /**
     * Set date of StudentGroupMatch
     * @param date
     * @return StudentGroupMatch object
     */
    public StudentGroupMatchBuilder setDate(Date date) {
        studentGroupMatch.setDate(date);
        return this;
    }
            
    /**
     * Gets the StudentGroupMatch object
     * @return StudentGroupMatch object
     */
    public StudentGroupMatch get() {
        return studentGroupMatch;
    }
    
}
