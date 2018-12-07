 package business;

import dataaccess.GroupDAOImpl;
import java.util.List;
import dto.Group;
import dataaccess.DAOInterface;
import dto.factory.DTOFactoryCreator;
import dto.factory.Factory;
import dto.factory.GroupFactory;
import java.util.Map;

/**
 *
 * @author Claire
 */
public class GroupLogic {

    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int GROUP_NAME_MAX_LENGTH = 45;

    private DAOInterface<Group> dao = null;
    private Factory<Group> factory = null;

    public GroupLogic() {
        dao = new GroupDAOImpl();
        factory = DTOFactoryCreator.createFactory(Group.class);
    }

    public List<Group> getAllGroups() {
        return dao.getAll();
    }

    public Group getById(String id){
        return dao.getById(id);
        
    }
    public void addGroup(Map<String, String[]> match) throws ValidationException {
        //addGroup( factory.createFromMap(course));
        //Group c = new Group((course.get(Group.NAME)[0]), Integer.valueOf(course.get(Group.ID)[0]));
        
        GroupFactory factory = new GroupFactory();
        dao.add(factory.createFromMap(match));
        
        
        //addGroup(c);
    }

    public void addGroup(Group s) throws ValidationException {
        validateGroup(s);
        cleanGroup(s);
        dao.add(s);
    }

    public void deleteGroups(String[] ids) throws ValidationException {
        for (String id : ids) {
            deleteGroup(id);
        }
    }

    public void deleteGroup(String id) throws ValidationException {
        dao.delete(id);
    }

    private void cleanGroup(Group s) {

        if (s.getName() != null) {
            s.setName(s.getName().trim());
        }
        
        if (s.getId() != 0) {
            s.setId(s.getId());
        }
    }

    private void validateGroup(Group s) throws ValidationException {
        validateString(s.getName(), "Name", GROUP_NAME_MAX_LENGTH, false);
        
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
