package dto;

import java.sql.Date;
import java.util.Objects;

/**
 * StudentGroupMatch DTO class
 * 
 * @author Harry Hum
 */
public class StudentGroupMatch {
    
    /**
     * Database table column names
     */
    public static final String COL_GROUP_ID = "group_id";
    public static final String COL_STUDENT_ID = "student_id";
    public static final String COL_DATE = "date";
    
    /**
     * Group id of match
     */
    private int groupID;
    
    /**
     * Student id of match
     */
    private int studentID;
    
    /**
     * Date of match
     */
    private Date date;

    /**
     * Default constructor
     */
    public StudentGroupMatch(){}
    
    /**
     * Constructor which initializes all attributes
     * @param group_id
     * @param student_id 
     */
    public StudentGroupMatch(int group_id, int student_id) {
        this.groupID = group_id;
        this.studentID = student_id;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Get match group id
     * @return group id
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * Set match group id
     * @param groupID 
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
    /**
     * Get match student id
     * @return student id
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Set match student id
     * @param studentID 
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Get match date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set match date
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Generate hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.groupID;
        hash = 29 * hash + this.studentID;
        hash = 29 * hash + Objects.hashCode(this.date);
        return hash;
    }

    /**
     * Compares object to match object
     * @param obj
     * @return true if equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentGroupMatch other = (StudentGroupMatch) obj;
        return true;
    }
       
}
