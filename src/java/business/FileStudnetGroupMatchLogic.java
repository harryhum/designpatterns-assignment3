/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dataaccess.FileStudnetGroupMatchDAO;
import dto.FileStudnetGroupMatch;
import dto.factory.Factory;
import dto.factory.DTOFactoryCreator;
import java.util.List;
import java.util.Map;
import dataaccess.FSGMDAOInterface;

/**
 * <p> Used as intermediary to access the DTO object</p>
 * @author tdesj
 */
public class FileStudnetGroupMatchLogic {
    

    private FSGMDAOInterface<FileStudnetGroupMatch> dao = null;
    private Factory<FileStudnetGroupMatch> factory = null;

    public FileStudnetGroupMatchLogic() {
        dao = new FileStudnetGroupMatchDAO();
        factory = DTOFactoryCreator.createFactory( FileStudnetGroupMatch.class);
    }

    public List<FileStudnetGroupMatch> getAll() {
        return dao.getAll();
    }   
    
    public FileStudnetGroupMatch getById(int id){
        return dao.getById(id);
    }

    public void addFSGM(Map<String, String[]> course) throws ValidationException {
        dao.add(factory.createFromMap(course));
       
    }

    public void addFSGM(FileStudnetGroupMatch fsgm) throws ValidationException {
        validateId(fsgm);
        dao.add(fsgm);
    }

    public void deleteFSGM(int[] ids) throws ValidationException {
        for (int id : ids) {
            deleteFSGM(id);
        }
    }

    public void deleteFSGM(int id) throws ValidationException {
        dao.delete(String.valueOf(id));
    }

    /**
     * <p>Validate each property</p>
     * @param fsgm
     * @throws ValidationException 
     */
    private void validateId(FileStudnetGroupMatch fsgm) throws ValidationException {
        validateId(fsgm.getGroupId(), FileStudnetGroupMatch.GROUP);
        validateId(fsgm.getStudentId(), FileStudnetGroupMatch.STUDENT);
        validateId(fsgm.getFileId(), FileStudnetGroupMatch.FILE);
        
    }

    private void validateId(int id, String fieldName) throws ValidationException {
        if (id <= 0) {
            throw new ValidationException(String.format("%s cannot be less than or equal to 0", fieldName));
        } 
    }
    
}
