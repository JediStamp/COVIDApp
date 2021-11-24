package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TeamServlet")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeamServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Request Parameters
		String teamName = request.getParameter("team_name");
		
		System.out.println("Adding team " + teamName);
		
		// Add team to dB
		// Make user team owner
		
		// Display new page
		request.getRequestDispatcher("team.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Request Parameters
		String email = request.getParameter("member_email");
		String role = request.getParameter("role");
		
		System.out.println("new member's email is: " + email + " and role is: " + role);
		
		// Add email to team list with role as assigned.
		// if email is already registered, just update the role if required
		// if email is not registered, send email to user inviting them to join
		// add email to list with team id and role id, and a NOT registered flag??
		// update table at bottom 
		
		
		// Display new page
		request.getRequestDispatcher("team.jsp").forward(request,response);
	}

}
