/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.factory;

import dto.builder.FileStudnetGroupMatchBuilder;
import dto.FileStudnetGroupMatch;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatchFactory extends AbstractFactory<FileStudnetGroupMatch>{

    
    /**
     * <p>Create instance of FSGM from Result Set using builder</p>
     * @param rs - Result Set (query)
     * @return - FSGM built by builder class
     * @throws SQLException 
     */
    @Override
    public FileStudnetGroupMatch createFromResultSet(ResultSet rs) throws SQLException {
        if (rs == null || !rs.next()){
            return null;
        }
        FileStudnetGroupMatchBuilder builder = new FileStudnetGroupMatchBuilder();
        builder.setGroupId(rs.getInt(FileStudnetGroupMatch.GROUP))
                .setStudentId(rs.getInt(FileStudnetGroupMatch.STUDENT))
                .setFileId(rs.getInt(FileStudnetGroupMatch.FILE));
        return builder.get();
    }

/**
* <p>Create instance of FSGM from Map using builder</p>
     * @param map - map used to create FSGM
     * @return - FSGM built by builder class
     
 */
    @Override
    public FileStudnetGroupMatch createFromMap(Map<String, String[]> map) {
        if ( map == null || map.isEmpty()){
            return null;
        }
        FileStudnetGroupMatchBuilder builder = new FileStudnetGroupMatchBuilder();
        builder.setFileId(Integer.valueOf(map.get(FileStudnetGroupMatch.FILE)[0]))
                .setGroupId(Integer.valueOf(map.get(FileStudnetGroupMatch.GROUP)[0]))
                .setStudentId(Integer.valueOf(map.get(FileStudnetGroupMatch.STUDENT)[0]));
        return builder.get();
        
        
    }
    
}
