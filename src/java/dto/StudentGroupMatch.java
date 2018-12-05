package dto;

import java.sql.Date;

/**
 *
 * @author Harry Hum
 */
public class StudentGroupMatch {
    
    public static final String COL_GROUP_ID = "group_id";
    public static final String COL_STUDENT_ID = "student_id";
    public static final String COL_DATE = "date";
    
    private int groupID;
    private int studentID;
    private Date date;
    
    public StudentGroupMatch(){}
    
    public StudentGroupMatch(int group_id, int student_id) {
        this.groupID = group_id;
        this.studentID = student_id;
        this.date = new Date(System.currentTimeMillis());
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
    
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
