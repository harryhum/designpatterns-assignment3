package view;

import business.GroupLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Group;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claire
 */
public class GroupView extends HttpServlet {
    
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
            out.println("<title>Group Table</title>");            
            out.println("</head>");
            out.println("<body>");
            
            GroupLogic logic = new GroupLogic();
            List<Group> results = new ArrayList<Group>();
            if(!(search)){
                results = logic.getAllGroups();
            }
            else {
                Logger.getLogger(GroupView.class.getName()).log(Level.INFO, request.getParameter(Group.ID));
                String searchID = (request.getParameter(Group.ID));
                
                // Add search result to results if results match search params, or configure error message and add all results if not
                if (logic.getById(searchID) != null) {
                    results.add(logic.getById(searchID));
                }
                else {
                    errorMessage = "No results with ID = " + searchID + " found.";
                    results = logic.getAllGroups();
                }
            }
            
            
            out.println("<table align=\"center\" border=\"1\">");
            out.println("<caption>Groups</caption>");
            out.println("<tr>");
            out.println("<th>Group ID</th>");
            out.println("<th>Group Name</th>");
            out.println("</tr>");
            
            if (results == null || results.isEmpty()) {
                errorMessage = "No matches found.";
            } else {
                for (Group g : results) {
                    out.printf("<tr><td>%s</td><td>%s</td></tr>", g.getId(), g.getName());
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
            out.println("<form action=\"GroupView\" method=\"post\">");
            out.println("Search Group by Group ID:<br>");
            
            out.println("<div style=\"position: relative; left: 50%; transform: translateX(-50%);\">");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\">", Group.ID);
            out.println("<input type=\"submit\" name=\"search\" value=\"Go\">");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            
            // Reset search to false and error message to null
            search = false;
            errorMessage = null;
            
            
            
            
            
            out.println("</body>");
            out.println("</html>");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
