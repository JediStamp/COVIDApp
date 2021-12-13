package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.app.User;
import beans.app.UserBuilder;
import beans.app.UserPwd;
import beans.login.LoginController;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;
	private User user;
	private LoginController lc;
	
    public LoginServlet() {
        super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Start Login...");
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		// Create user for checks
		user = new UserBuilder().setEmail(email).setPassword(UserPwd.hashPwd(pwd)).createUser();
		
		// Login user using loginController
		String[] output = lc.login(user);
		
		// Set error message & URL
		request.setAttribute("errorMsg", output[0]);	
		String url = output[1];
		
		// Set session info
		request.getSession().setAttribute("thisUser", user);
		
		// Display Login servlet info
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
		System.out.printf("LoginServlet:\n");
		System.out.printf("\tLogin User: \n\t%s\n\t%s\n", user.getEmail(), pwd);
		
		// Print outputs to screen
		System.out.printf("\terror message is: %s\n", output[0]);
		System.out.printf("\tpath is: %s\n\n", output[1]);
	}
}
