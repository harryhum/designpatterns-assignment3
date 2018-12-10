/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.FileStudnetGroupMatchLogic;
import dto.FileStudnetGroupMatch;
import dto.FileStudnetGroupMatch;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
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
public class FileStudnetGroupMatchForm extends HttpServlet {
    
    private String errorMessage = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FileStudnetGroupMatch Form</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\">");
            out.println("<form action=\"FileStudnetGroupMatchForm\" method=\"post\">");
            out.println("Group ID:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>",FileStudnetGroupMatch.GROUP);
            out.println("Student ID:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br><br>",FileStudnetGroupMatch.STUDENT);
            out.println("File ID:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br><br>",FileStudnetGroupMatch.FILE);
            out.println("<input type=\"submit\" name=\"view1\" value=\"Add and View\">");
            out.println("<input type=\"submit\" name=\"add1\" value=\"Add\">");
            out.println("</form>");
            if(errorMessage!=null&&!errorMessage.isEmpty()){
                out.println("<p color=red>");
                out.println("<font color=red size=4px>");
                out.println(errorMessage);
                out.println("</font>");
                out.println("</p>");
            }
            out.println("<pre>");
            out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</pre>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Creates a string version of a map of strings
     * @param values
     * @return string
     */
    private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Runnable add = ()->{
            FileStudnetGroupMatchLogic logic = new FileStudnetGroupMatchLogic();
            try{
                logic.addFSGM(request.getParameterMap());
            }catch(Exception e){
                Logger.getLogger(FileStudnetGroupMatchForm.class.getName()).log(Level.SEVERE, null, e);
                errorMessage = "Unable to add FileStudnetGroupMatch";
            }
        };
        if( request.getParameter("add1")!=null){
            add.run();
            processRequest(request, response);
        } else if (request.getParameter("view1")!=null) {
            add.run();
            response.sendRedirect("FileStudnetGroupMatchView");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
    
}
