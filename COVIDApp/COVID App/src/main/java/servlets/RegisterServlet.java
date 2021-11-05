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

@WebServlet( "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Go to home page to register...");
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * DEBUGGING GUIDE
	 * 
	 * fname = "ver" gets you verified
	 * pwd = "pwd" gets correct password
	 * registered works through dB
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		System.out.println(request.getParameterMap());
		System.out.println("select"+ 1);
		
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		String info = "New user: " + fName + " " + lName + " " + email + " " + pwd;
		String htmlOut = "<html>" + info + "</html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlOut);

		System.out.printf("User: \n\t%s\n\t%s\n\t%s\n\t%s\n",
				fName,lName,email,pwd);
		
		//Create new user
		String pwdHashed = UserPwd.hashPwd(pwd);
		User user = new User(fName, lName, email, pwdHashed);
		
		// Register User
		String[] output = LoginController.register(user);
		
		
		// Set error message, user details & URL
		request.setAttribute("errorMsg", output[0]);
		request.getSession().setAttribute("thisUser", user);
//		request.getSession().setAttribute("userID", output[2]);
		request.getSession().setAttribute("firstName", output[3]);
		request.getSession().setAttribute("lastName", output[4]);
		request.getSession().setAttribute("email", output[5]);
		
		String url = output[1];
		
		// Print them to the screen
		System.out.println("RegisterServlet: Printing output parameters...");
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
