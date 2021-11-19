package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.User;
import app.UserBuilder;
import app.UserPwd;
import login.LoginController;

@WebServlet( "/RegisterServlet")
public class RegisterServlet extends HttpServlet implements Observer {
	private static final long serialVersionUID = 1L;
	private User user;
	private LoginController lc;
       
    public RegisterServlet() {
        super();
        user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Go to home page to register...");
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		// Create new user
		user = new UserBuilder()
					.setFirstName(firstName)
					.setLastName(lastName)
					.setEmail(email)
					.setPassword(UserPwd.hashPwd(pwd))
					.createUser();
		
		// Register User		
		String[] output = lc.register(user);

		// Set error message & URL
		request.setAttribute("errorMsg", output[0]);
		String url = output[1];
		
		// Set session info
		request.getSession().setAttribute("thisUser", user);

		// Display Register Servlet info
		display(pwd, output);

		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}
	
	public void display(String pwd, String[] output) {
		// Print out user's info
		System.out.printf("RegisterServlet:\n");
		System.out.printf("\tRegister User: \n\t%s\n\t%s\n\t%s\n\t%s\n",
					user.getFirstName(), user.getLastName(), user.getEmail(), pwd);
		
		// Print outputs to screen
		System.out.printf("\terror message is: %s\n", output[0]);
		System.out.printf("\tpath is: %s\n\n", output[1]);
	}
}
