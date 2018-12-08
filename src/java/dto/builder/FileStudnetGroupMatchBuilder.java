/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.builder;

import dto.FileStudnetGroupMatch;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatchBuilder {
    
    private final FileStudnetGroupMatch fsgm = new FileStudnetGroupMatch();

    public FileStudnetGroupMatchBuilder setGroupId( int id) {
        fsgm.setGroupId(id);
        return this;
    }

    public FileStudnetGroupMatchBuilder setStudentId( int id) {
        fsgm.setStudentId(id);
        return this;
    }
    
    public FileStudnetGroupMatchBuilder setFileId(int id){
        fsgm.setFileId(id);
        return this;
    }

    public FileStudnetGroupMatch get() {
        return fsgm;
    }
    
}
