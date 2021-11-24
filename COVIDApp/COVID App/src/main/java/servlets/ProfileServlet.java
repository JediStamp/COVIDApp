package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.User;
import app.UserBuilder;
import dao.ApplicationDao;
import dao.ApplicationDaoProxy;
import login.LoginController;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;

	private User user;
	private LoginController lc;

	//Proxy Pattern
	ApplicationDaoProxy appDaoProxy = new ApplicationDaoProxy();

	
    public ProfileServlet() {
        super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		
		// Get current user
		user = (User) request.getSession().getAttribute("thisUser");
		
		// Update user parameters
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		appDaoProxy.updateUser(user.getUserID(), firstName, lastName);				

		// Set error message & URL
		String url = "profile.jsp";
		
		// Set session info
		request.getSession().setAttribute("thisUser", user);
		
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}
	
	public void display(String url) {
		// Print out user's info
		System.out.printf("ProfileServlet:\n");
		System.out.printf("\tUpdate User: \n\t%s\n\t%s\n\t%s\n", 
				user.getFirstName(), user.getLastName(), user.getEmail());
		
		// Print outputs to screen
		System.out.printf("\tpath is: %s\n\n", url);
	}
}
