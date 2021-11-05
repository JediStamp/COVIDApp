package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import dao.ApplicationDao;
import login.LoginController;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public ProfileServlet() {
        super();
//        try {
//			doGet(null, null);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		User user = (User) request.getSession().getAttribute("thisUser");
//		
//		request.getSession().setAttribute("firstName", user.getFirstName());
//		request.getSession().setAttribute("lastName", user.getLastName());
//		request.getSession().setAttribute("email", user.getEmail());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (request.getParameter(getServletName()))
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		
		System.out.println("firstName is: " + firstName);
		System.out.println("lastName is: " + lastName);
		
		User user = (User) request.getSession().getAttribute("thisUser");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		ApplicationDao.updateUser(user.getUserID(), firstName, lastName);				

		request.getSession().setAttribute("thisUser", user);		
		request.getSession().setAttribute("firstName", firstName);
		request.getSession().setAttribute("lastName", lastName);
		
		String url = "profile.jsp";
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}
}
