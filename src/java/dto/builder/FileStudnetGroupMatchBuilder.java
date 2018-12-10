
package dto.builder;

import dto.FileStudnetGroupMatch;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatchBuilder {
    
    // FSGM object being built
    private final FileStudnetGroupMatch fsgm = new FileStudnetGroupMatch();

    /**
     * <p>Set FSGM GroupID</p>
     * @param id 
     * @return - builder with modification;
     */
    public FileStudnetGroupMatchBuilder setGroupId( int id) {
        fsgm.setGroupId(id);
        return this;
    }

    /**
     * <p>Set FSGM StudentID</p>
     * @param id
     * @return - builder with modifications
     */
    public FileStudnetGroupMatchBuilder setStudentId( int id) {
        fsgm.setStudentId(id);
        return this;
    }
    
    /**
     * <p>Set FSGM FileID</p>
     * @param id
     * @return - builder with modifications 
     */
    public FileStudnetGroupMatchBuilder setFileId(int id){
        fsgm.setFileId(id);
        return this;
    }

    /**
     * <p>Get the complete FSGM</p>
     * @return - THIS FSGM
     */
    public FileStudnetGroupMatch get() {
        return fsgm;
    }
    
}
