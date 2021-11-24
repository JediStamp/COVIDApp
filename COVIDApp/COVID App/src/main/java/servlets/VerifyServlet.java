package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.User;
import app.UserBuilder;
import login.LoginController;

@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;
	private User user;
	private LoginController lc;
       
    public VerifyServlet() {
        super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String v1 = request.getParameter("v1");
		String v2 = request.getParameter("v2");
		String v3 = request.getParameter("v3");
		String v4 = request.getParameter("v4");
		String v5 = request.getParameter("v5");
		String v6 = request.getParameter("v6");
		
		String verCode = v1 + v2 + v3 + v4 + v5 + v6;
		
		// Get user
		user = (User) request.getSession().getAttribute("thisUser");
		
		// Verify User
		String[] output = lc.verify(user.getUserID(), verCode);
		
		// Set error message & URL
		request.setAttribute("errorMsg", output[0] );
		String url = output[1];
		
		// Set session info
		request.getSession().setAttribute("thisUser", user);
		
		// Display Verify Servlet info
		display(verCode, output);
		
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}

	private void display(String verCode, String[] output) {
		System.out.printf("VerifyServlet:\n");
		System.out.printf("\tVerify User: \n\t%s\n\t%s\n\n\t%s\n\t%s\n",
					user.getFirstName(), user.getLastName(), user.getEmail(), verCode);
		
		// Print outputs to screen
		System.out.printf("\terror message is: %s\n", output[0]);
		System.out.printf("\tpath is: %s\n\n", output[1]);
	}
}
