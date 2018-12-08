 package business;

import dataaccess.StudentDAOImpl;
import java.util.List;
import dto.Student;
import dataaccess.DAOInterface;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import dto.factory.StudentFactory;
import java.util.Map;

/**
 *
 * @author Claire
 */
public class StudentLogic {
    
    /**
     * convenience for validating inout
     */

    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int STUD_NAME_MAX_LENGTH = 45;
    
    /**
     * for creating students/accessing DB
     */

    private DAOInterface<Student> dao = null;
    private Factory<Student> factory = null;
    
    /**
     * constructor, sets up to create students and access DB
     */

    public StudentLogic() {
        dao = new StudentDAOImpl();
        factory = DTOFactoryCreator.createFactory(Student.class);
    }
    /**
     * 
     * @return all students, as selected from the DB
     */

    public List<Student> getAllStudents() {
        //System.out.println("sup");
        return dao.getAll();
    }

    /**
     * 
     * @param id of the student to select
     * @return student, as seelcted from the DB
     */
    public Student getById(String id){
        return dao.getById(id);
        
    }
    /**
     * creates a student and adds it to the DB
     * @param match map to create with
     * @throws ValidationException 
     */
    public void addStudent(Map<String, String[]> match) throws ValidationException {
       
        StudentFactory factory = new StudentFactory();
        dao.add(factory.createFromMap(match));
        

    }

    /**
     * 
     * @param s student to add, after validation and cleaning
     * @throws ValidationException 
     */
    public void addStudent(Student s) throws ValidationException {
        validateStudent(s);
        cleanStudent(s);
        dao.add(s);
    }

    /**
     * 
     * @param codes array of IDs to delete
     * @throws ValidationException 
     */
    public void deleteStudents(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteStudent(code);
        }
    }

    /**
     * 
     * @param code the ID to delete
     * @throws ValidationException 
     */
    public void deleteStudent(String code) throws ValidationException {
        dao.delete(code);
    }
    
    /**
     * 
     * @param s  student whose variables we are cleaning
     */

    private void cleanStudent(Student s) {
        if (s.getfName()!= null) {
            s.setfName(s.getfName().trim());
        }
        if (s.getlName() != null) {
            s.setlName(s.getlName().trim());
        }
        
        if (s.getId() != 0) {
            s.setId(s.getId());
        }
    }

    /**
     * makes sure student name is of a valid length
     * @param s student to check
     * @throws ValidationException 
     */
    private void validateStudent(Student s) throws ValidationException {
        validateString(s.getfName(), "First Name", STUD_NAME_MAX_LENGTH, false);
        validateString(s.getlName(), "Last Name", STUD_NAME_MAX_LENGTH, false);
    }

    /**
     * makes sure the string of student name is of an acceptable format
     * @param value
     * @param fieldName
     * @param maxLength
     * @param isNullAllowed
     * @throws ValidationException 
     */
    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed) throws ValidationException {
        if (value == null && isNullAllowed) {
            // null permitted, nothing to validate
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null", fieldName));
        } else if (value.isEmpty()) {
            throw new ValidationException(String.format("%s cannot be empty or only whitespace", fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters", fieldName, maxLength));
        }
    }
}
