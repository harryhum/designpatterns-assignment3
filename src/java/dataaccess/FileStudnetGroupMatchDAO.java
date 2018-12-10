/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import dto.FileStudnetGroupMatch;
import dto.factory.Factory;
import dto.factory.DTOFactoryCreator;
import dto.factory.FileStudnetGroupMatchFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatchDAO implements FSGMDAOInterface<FileStudnetGroupMatch>{
    
    
            // Various SQL statements for given expected results
    private static final String GET_ALL_GROUPS = "SELECT * FROM filestudnetgroupmatch ORDER BY group_id";
    private static final String INSERT_GROUPS = "INSERT INTO filestudnetgroupmatch (group_id, student_id, file_id) VALUES(?, ?, ?)";
    private static final String DELETE_GROUP = "DELETE FROM filestudnetgroupmatch WHERE group_id = ?";
    private static final String DELETE_STUDENT = "DELETE FROM filestudnetgroupmatch WHERE student_id = ?";
    private static final String DELETE_FILE = "DELETE FROM filestudnetgroupmatch WHERE file_id = ?";
    private static final String DELETE_FILES = "DELETE FROM filestudnetgroupmatch WHERE (file_id) IN ";
    private static final String DELETE_GROUPS = "DELETE FROM filestudnetgroupmatch WHERE (group_id) IN ";
    private static final String DELETE_STUDENTS = "DELETE FROM filestudnetgroupmatch WHERE (student_id) IN ";
    private static final String GET_BY_GROUP = "SELECT * FROM filestudnetgroupmatch WHERE group_id = ?";
    private static final String GET_BY_STUDENT = "SELECT * FROM filestudnetgroupmatch WHERE student_id = ?";
    private static final String GET_BY_FILE = "SELECT * FROM filestudnetgroupmatch WHERE file_id = ?";

    private final Factory<FileStudnetGroupMatch> factory = DTOFactoryCreator.createFactory(FileStudnetGroupMatch.class);
    
    /**
     * <p>Get all entries on the FSGM table</p>
     * @return results from given query
     */
    @Override
    public List<FileStudnetGroupMatch> getAll() {
        List<FileStudnetGroupMatch> list = Collections.emptyList();
        FileStudnetGroupMatch fsg;
        try (Connection conn = DataSource.createConnection();
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_GROUPS);
            ResultSet rs = pstmt.executeQuery();) {
            //find a way to implement factory
            //list = factory.createListFromResultSet(rs);
            list = new ArrayList<>(100);
            while(rs.next()){
                fsg = new FileStudnetGroupMatch();
                fsg.setGroupId(rs.getInt(FileStudnetGroupMatch.GROUP));
                fsg.setStudentId(rs.getInt(FileStudnetGroupMatch.STUDENT));
                fsg.setFileId(rs.getInt(FileStudnetGroupMatch.FILE));
                list.add(fsg);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**
     * <p>Delete entry at this location by student ID</p>
     * @param id 
     */
    @Override
    public void delete(String id) {
    try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_STUDENT);) {
            pstmt.setInt(1, Integer.valueOf(id));
            pstmt.executeUpdate();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(GroupDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    

    /**
     * <p>delete all entries for given array by student id</p>
     * @param str 
     */
    @Override
    public void deleteAll(String[] str) {
    StringBuilder query = new StringBuilder(DELETE_STUDENTS);
        query.append("(");
        String delimiter = "";
        for (int i = 0; i < str.length; i++) {
            query.append(delimiter).append("(?)");
            delimiter = ",";
        }
        query.append(")");
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(query.toString());) {
            for (int i = 0; i < str.length; i++) {
                pstmt.setString(i + 1, str[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    /**
     * <p>Add FSGM to database</p>
     * @param fsgm 
     */
    @Override
    public void add(FileStudnetGroupMatch fsgm) {
        try (Connection con = DataSource.createConnection();
                PreparedStatement pstmt = con.prepareStatement(INSERT_GROUPS);) {
            pstmt.setInt(1, fsgm.getGroupId());
            pstmt.setInt(2, fsgm.getStudentId());
            pstmt.setInt(3, fsgm.getFileId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * <p>Retrieve FSGM with given Student ID </p>
     * @param id
     * @return 
     */
    @Override
    public FileStudnetGroupMatch getById(int id) {
        FileStudnetGroupMatch fsgm = null;
        try (Connection conn = DataSource.createConnection();
                PreparedStatement pstmnt = conn.prepareStatement(GET_BY_STUDENT);){
            pstmnt.setInt(1, id);
            ResultSet rs = pstmnt.executeQuery();
            if (rs.next()){
                FileStudnetGroupMatchFactory factory = new FileStudnetGroupMatchFactory();
                fsgm = factory.createFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(FileStudnetGroupMatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fsgm;
    }
    
    
}
