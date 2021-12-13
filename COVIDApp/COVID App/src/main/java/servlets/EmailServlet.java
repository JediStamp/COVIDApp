package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.app.User;
import beans.app.UserBuilder;
import beans.login.LoginController;


@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;
	private User user;
	private LoginController lc;
       
    public EmailServlet() {
        super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String email = request.getParameter("email");
		
		System.out.println("At email servlet. user: " + email);
		
		// Create user for checks
		user = new UserBuilder().setEmail(email).createUser();
		
		// Logic for changing a password when forgotten
		String[] output = lc.changePwd(user);
		
		// Set error message & URL
		request.setAttribute("errorMsg", output[0] );
		String url = output[1];

		// Set session info
		request.getSession().setAttribute("thisUser", user);
		
		// Display Email Servlet info
		display(output);

		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}

	private void display(String[] output) {
		System.out.printf("EmailServlet:\n");
		System.out.printf("\tEmail User: \n\t%s\n\t%s\n\n\t%s\n",
					user.getFirstName(), user.getLastName(), user.getEmail());
		
		// Print outputs to screen
		System.out.printf("\terror message is: %s\n", output[0]);
		System.out.printf("\tpath is: %s\n\n", output[1]);
	}
}
