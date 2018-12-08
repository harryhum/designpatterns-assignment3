/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatch {
    
    public static final String GROUP = "group_id";
    public static final String STUDENT = "student_id";
    public static final String FILE = "file_id";
    
    
    private int groupId;
    private int studentId;
    private int fileId;
    
    
    public FileStudnetGroupMatch(){}
    public FileStudnetGroupMatch(int gid, int sid, int fid){
        
        setGroupId(gid);
        setStudentId(sid);
        setFileId(fid);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    
    
}
