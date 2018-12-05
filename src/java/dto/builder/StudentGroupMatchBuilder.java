package dto.builder;

import dto.StudentGroupMatch;

/**
 *
 * @author Harry Hum
 */
public class StudentGroupMatchBuilder {

    private final StudentGroupMatch studentGroupMatch = new StudentGroupMatch();

    public StudentGroupMatchBuilder setGroupID(int groupID) {
        studentGroupMatch.setGroupID(groupID);
        return this;
    }

    public StudentGroupMatchBuilder setStudentID(int studentID) {
        studentGroupMatch.setStudentID(studentID);
        return this;
    }

    public StudentGroupMatch get() {
        return studentGroupMatch;
    }
}
