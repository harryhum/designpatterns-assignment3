package dto.factory;

import dto.StudentGroupMatch;
import dto.builder.StudentGroupMatchBuilder;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Concrete extension of AbstractFactory class
 *
 * @author Harry Hum
 */
public class StudentGroupMatchFactory extends AbstractFactory<StudentGroupMatch> {

    /**
     * Creates a StudentGroupMatch from a result set
     * @param rs
     * @return StudentGroupMatch object
     * @throws SQLException 
     */
    @Override
    public StudentGroupMatch createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }
        StudentGroupMatchBuilder builder = new StudentGroupMatchBuilder();
        builder.setGroupID(rs.getInt(StudentGroupMatch.COL_GROUP_ID))
                .setStudentID(rs.getInt(StudentGroupMatch.COL_STUDENT_ID))
                .setDate(rs.getDate(StudentGroupMatch.COL_DATE));
        return builder.get();
    }

    /**
     * Creates a StudentGroupMatch from a request parameter map
     * @param map - servlet request parameter map
     * @return StudentGroupMatch object
     */
    @Override
    public StudentGroupMatch createFromMap(Map<String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StudentGroupMatchBuilder builder = new StudentGroupMatchBuilder();
        builder.setGroupID(Integer.parseInt(map.get(StudentGroupMatch.COL_GROUP_ID)[0]))
                .setStudentID(Integer.parseInt(map.get(StudentGroupMatch.COL_STUDENT_ID)[0]))
                .setDate(new Date(System.currentTimeMillis()));
        return builder.get();
    }
}
