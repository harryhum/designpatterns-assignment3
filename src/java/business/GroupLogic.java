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
    

    /**
     * for validating input
     */
    private static final int COURSE_CODE_MAX_LENGTH = 45;
    private static final int GROUP_NAME_MAX_LENGTH = 45;

    /**
     * for adding groups and accessing db
     */
    private DAOInterface<Group> dao = null;
    private Factory<Group> factory = null;

    /**
     * constructor, sets up to make groups and access db
     */
    public GroupLogic() {
        dao = new GroupDAOImpl();
        factory = DTOFactoryCreator.createFactory(Group.class);
    }

    /**
     * 
     * @return list of all the groups
     */
    public List<Group> getAllGroups() {
        return dao.getAll();
    }

    /**
     * 
     * @param id id to search for
     * @return group with given id
     */
    public Group getById(String id){
        return dao.getById(id);
        
    }
    
    /**
     * adds a group to the db
     * @param match map to pull group's details from
     * @throws ValidationException 
     */
    public void addGroup(Map<String, String[]> match) throws ValidationException {
        //addGroup( factory.createFromMap(course));
        //Group c = new Group((course.get(Group.NAME)[0]), Integer.valueOf(course.get(Group.ID)[0]));
        
        GroupFactory factory = new GroupFactory();
        dao.add(factory.createFromMap(match));
        
        
        //addGroup(c);
    }
    /**
     * adds a group to the DB after validation
     * @param s group to add
     * @throws ValidationException 
     */

    public void addGroup(Group s) throws ValidationException {
        validateGroup(s);
        cleanGroup(s);
        dao.add(s);
    }
    
    /**
     * deletes groups with given ids
     * @param ids
     * @throws ValidationException 
     */

    public void deleteGroups(String[] ids) throws ValidationException {
        for (String id : ids) {
            deleteGroup(id);
        }
    }

    /**
     * deletes a group with given id
     * @param id
     * @throws ValidationException 
     */
    public void deleteGroup(String id) throws ValidationException {
        dao.delete(id);
    }

    /**
     * 
     * @param s group to clean
     */
    private void cleanGroup(Group s) {

        if (s.getName() != null) {
            s.setName(s.getName().trim());
        }
        
        if (s.getId() != 0) {
            s.setId(s.getId());
        }
    }

    /**
     * 
     * @param s string group name to validate
     * @throws ValidationException 
     */
    private void validateGroup(Group s) throws ValidationException {
        validateString(s.getName(), "Name", GROUP_NAME_MAX_LENGTH, false);
        
    }

    /**
     * validates the name string of a group
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
