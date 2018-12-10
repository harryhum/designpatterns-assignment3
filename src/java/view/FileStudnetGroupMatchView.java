/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.FileStudnetGroupMatchLogic;
import business.ValidationException;
import dto.FileStudnetGroupMatch;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tdesj
 */
public class FileStudnetGroupMatchView extends HttpServlet{
    
    private String errorMessage = null;
    
    private boolean search = false;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FileStudnetGroupMatch Table</title>");            
            out.println("</head>");
            out.println("<body>");
            
            FileStudnetGroupMatchLogic logic = new FileStudnetGroupMatchLogic();
            List<FileStudnetGroupMatch> matches = new ArrayList<>();
            int searchId = 0;
            try  {
                      
                if (search){
                    Logger.getLogger(FileStudnetGroupMatchView.class.getName()).log(Level.INFO, request.getParameter(FileStudnetGroupMatch.STUDENT));
                    searchId = Integer.parseInt(request.getParameter(FileStudnetGroupMatch.STUDENT));
                    
                    if (logic.getById(searchId) == null){
                        throw new ValidationException();
                    }
                    
                    matches.add(logic.getById(searchId));
                } else {
                    matches = logic.getAll();
                }
            } catch (ValidationException | NumberFormatException e){
                errorMessage = String.valueOf(searchId) + " is not a valid search entry";
           }
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>FileStudnetGroupMatch</caption>");
            out.println("<tr>");
            out.println("<th>Student ID</th>");
            out.println("<th>Group ID</th>");
            out.println("<th>File ID</th>");
            out.println("</tr>");
            
            if (matches == null || matches.isEmpty()){
                errorMessage = "No matches for Student ID: " + searchId;
            } else {
                for (FileStudnetGroupMatch fsgm : matches){
                    out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", fsgm.getStudentId(), fsgm.getGroupId(), fsgm.getFileId());
                }
            }
            out.println("</table>");
            
            out.println("<br><div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: center;\">");
            
            if(errorMessage!=null&&!errorMessage.isEmpty()){
                out.println("<p color=red>");
                out.println("<font color=red size=4px>");
                out.println(errorMessage);
                out.println("</font>");
                out.println("</p>");
            }
            
            out.println("<form action=\"FileSudnetGroupMatchView\" method=\"post\">");
            out.println("Search FileStudnetGroupMatch by Student ID:<br>");
            
            out.println("<div style=\"position: relative; left: 50%; transform: translateX(-50%);\">");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\">", FileStudnetGroupMatch.STUDENT);
            out.println("<input type=\"submit\" name=\"search\" value=\"Go\">");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            
            // Reset search to false and error message to null
            search = false;
            errorMessage = null;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if( request.getParameter("search")!=null){
            search = true;
            processRequest(request, response);
        }
    }
}
