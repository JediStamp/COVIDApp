package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import app.UserPwd;
import login.LoginController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Start Login...");
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		String info = "New user: " + email + " " + pwd;
		String htmlOut = "<html>" + info + "</html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlOut);

		System.out.printf("New User: \n\t%s\n\t%s\n",email,pwd);
		
		// Create user for checks
		String pwdHashed = UserPwd.hashPwd(pwd);
		User user = new User(email, pwdHashed);
		
		String[] output = LoginController.login(user);
		
		// Set error message & URL
		request.setAttribute("errorMsg", output[0]);	
		request.getSession().setAttribute("userID", output[2]);
//		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("firstName", output[3]);
		request.getSession().setAttribute("lastName", output[4]);
		request.getSession().setAttribute("email", output[5]);
		// set email, first name, last name for user
		String url = output[1];
		
		// Print them to the screen
		System.out.println("LoginServlet: Printing output parameters...");
		System.out.println("error message is: " + output[0]);
		System.out.println("path is: " + output[1]);
		System.out.println("userID is: " + output[2]);
		System.out.println("firstName is: " + output[3]);
		System.out.println("lastName is: " + output[4]);
		System.out.println("email is: " + output[5]);
		
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

}
