package view;

import business.StudentGroupMatchLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.StudentGroupMatch;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet which displays the matches table and provides a search by student id
 * 
 * @author Harry Hum
 */
public class StudentGroupMatchView extends HttpServlet {
    
    /**
     * Error message
     */
     private String errorMessage = null;
     
     /**
      * Determines if a search was conducted
      */
     private boolean search = false;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>StudentGroupMatch Table</title>");            
            out.println("</head>");
            out.println("<body>");
            
            StudentGroupMatchLogic logic = new StudentGroupMatchLogic();
            List<StudentGroupMatch> matches = new ArrayList<>();
            
            // Add search results to match list if a search was conducted, or add all matches if not
            if (search) {
                Logger.getLogger(StudentGroupMatchView.class.getName()).log(Level.INFO, request.getParameter(StudentGroupMatch.COL_STUDENT_ID));
                int searchID = Integer.parseInt(request.getParameter(StudentGroupMatch.COL_STUDENT_ID));
                
                // Add search result to matches if results match search params, or configure error message and add all matches if not
                if (logic.getMatchByStudentID(searchID) != null) {
                    matches.add(logic.getMatchByStudentID(searchID));
                }
                else {
                    errorMessage = "No matches with StudentID = " + searchID + " found.";
                    matches = logic.getAllMatches();
                }
            } else {
                matches = logic.getAllMatches();
            }
            
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>StudentGroupMatch</caption>");
            out.println("<tr>");
            out.println("<th>Student ID</th>");
            out.println("<th>Group ID</th>");
            out.println("<th>Date</th>");
            out.println("</tr>");
            
            // Display error message if not null, or display matches if no error
            if (matches == null || matches.isEmpty()) {
                errorMessage = "No matches found.";
            } else {
                for (StudentGroupMatch match : matches) {
                    out.printf("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", match.getStudentID(), match.getGroupID(), match.getDate());
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
            
            // StudentID search form
            out.println("<form action=\"StudentGroupMatchView\" method=\"post\">");
            out.println("Search StudentGroupMatch by Student ID:<br>");
            
            out.println("<div style=\"position: relative; left: 50%; transform: translateX(-50%);\">");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\">", StudentGroupMatch.COL_STUDENT_ID);
            out.println("<input type=\"submit\" name=\"search\" value=\"Go\">");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            
            // Reset search to false and error message to null
            search = false;
            errorMessage = null;
        }
    }
    
        /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if( request.getParameter("search")!=null){
            search = true;
            processRequest(request, response);
        }
    }
}
