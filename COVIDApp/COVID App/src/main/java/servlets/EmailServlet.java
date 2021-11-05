package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import login.LoginController;


@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		System.out.println("At email servlet. user: " + email);
		
		// Create user for checks
		User user = new User(email);
		
		// Logic for changing a password when forgotten
		String[] output = LoginController.changePwd(user);
		
		user.setUserID(output[2]);
		user.setFirstName(output[3]);
		user.setLastName(output[4]);
		user.setEmail(output[5]);
		
		// Set error message & URL
		request.setAttribute("errorMsg", output[0]);	
		
		request.getSession().setAttribute("thisUser", user);

		request.getSession().setAttribute("firstName", output[3]);
		request.getSession().setAttribute("lastName", output[4]);
		request.getSession().setAttribute("email", output[5]);
		// set email, first name, last name for user
		String url = output[1];
//		
//		// Print them to the screen
		System.out.println("EmailServlet: Printing output parameters...");
//		System.out.println("error message is: " + output[0]);
		System.out.println("path is: " + output[1]);
//		System.out.println("userID is: " + output[2]);
//		System.out.println("firstName is: " + output[3]);
//		System.out.println("lastName is: " + output[4]);
//		System.out.println("email is: " + output[5]);
		
		// Display new page
		request.getRequestDispatcher("verify.jsp").forward(request,response);
	}

}
