package dto.factory;

import dto.StudentGroupMatch;
import dto.builder.StudentGroupMatchBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 *
 * @author Shariar
 */
public class StudentGroupMatchFactory extends AbstractFactory<StudentGroupMatch> {

    @Override
    public StudentGroupMatch createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()) {
            return null;
        }
        StudentGroupMatchBuilder builder = new StudentGroupMatchBuilder();
        builder.setGroupID(rs.getInt(StudentGroupMatch.COL_GROUP_ID))
                .setStudentID(rs.getInt(StudentGroupMatch.COL_STUDENT_ID));
        return builder.get();
    }

    @Override
    public StudentGroupMatch createFromMap(Map<String, int[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StudentGroupMatchBuilder builder = new StudentGroupMatchBuilder();
        builder.setGroupID(map.get(StudentGroupMatch.COL_GROUP_ID)[0])
                .setStudentID(map.get(StudentGroupMatch.COL_STUDENT_ID)[0]);
        return builder.get();
    }
}
