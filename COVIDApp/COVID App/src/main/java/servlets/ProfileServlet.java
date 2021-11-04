package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		System.out.println("firstName is: " + firstName);
		System.out.println("lastName is: " + lastName);
		System.out.println("email is: " + email);
		
//		ApplicationDao.updateUser(firstName, lastName, email); - Future work
		
		String url = "profile.jsp";
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}
}
