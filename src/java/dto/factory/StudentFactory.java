/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.factory;

import dto.Student;
import dto.builder.StudentBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Claire
 * hides creation of student objects, via the Student Builder
 */
public class StudentFactory extends  AbstractFactory<Student>{

    /**
     * 
     * @param rs from which values are extracted
     * @return created student
     * @throws SQLException 
     */
    @Override
    public Student createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null) {
            return null;
        }
        StudentBuilder builder = new StudentBuilder();
        builder.setId(rs.getInt(Student.ID))
                .setfName(rs.getString(Student.FIRST_NAME))
                .setlName(rs.getString(Student.LAST_NAME))
                ;
        return builder.get();
    }

  
    
    /**
     * 
     * @param map from which values are extracted
     * @return created student
     */
    
    @Override
    public Student createFromMap(Map< String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StudentBuilder builder = new StudentBuilder();
        builder.setId(Integer.valueOf(map.get(Student.ID)[0]))
                .setfName(map.get(Student.FIRST_NAME)[0])
                    .setlName(map.get(Student.LAST_NAME)[0]);
        return builder.get();
    }

    
    
    
    
}
