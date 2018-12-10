 package business;

import dataaccess.StudentDAOImpl;
import java.util.List;
import dto.Student;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import java.util.Map;
import dataaccess.DAOInterface;

/**
 *
 * @author Claire
 */
public class StudentLogic {

    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int STUD_NAME_MAX_LENGTH = 45;

    private DAOInterface<Student> dao = null;
    private Factory<Student> factory = null;

    public StudentLogic() {
        dao = new StudentDAOImpl();
        factory = DTOFactoryCreator.createFactory(Student.class);
    }

    public List<Student> getAllStudents() {
        return dao.getAll();
    }

    public Student getById(String id){
        return dao.getById(id);
        
    }
    public void addStudent(Map<String, String[]> course) throws ValidationException {
        //addStudent( factory.createFromMap(course));
        Student c = new Student((course.get(Student.FIRST_NAME)[0]), course.get(Student.LAST_NAME)[0], Integer.valueOf(course.get(Student.ID)[0]));
        addStudent(c);
    }

    public void addStudent(Student s) throws ValidationException {
        validateStudent(s);
        cleanStudent(s);
        dao.add(s);
    }

    public void deleteStudents(String[] codes) throws ValidationException {
        for (String code : codes) {
            deleteStudent(code);
        }
    }

    public void deleteStudent(String code) throws ValidationException {
        dao.delete(code);
    }

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

    private void validateStudent(Student s) throws ValidationException {
        validateString(s.getfName(), "First Name", STUD_NAME_MAX_LENGTH, false);
        validateString(s.getlName(), "Last Name", STUD_NAME_MAX_LENGTH, false);
    }

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
