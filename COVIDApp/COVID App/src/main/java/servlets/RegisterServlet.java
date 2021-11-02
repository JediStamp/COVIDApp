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
 * Servlet implementation class RegisterServlet
 */
@WebServlet( "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");

		
		String info = "New user: " + fName + " " + lName + " " + email + " " + pwd;
		String htmlOut = "<html>" + info + "</html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlOut);

		System.out.printf("New User: \n\t%s\n\t%s\n\t%s\n\t%s\n",
				fName,lName,email,pwd);
		
		//TODO: check that pwd == pwd2 in html before sending (Javascript maybe)
		//TODO: encrypt password
		
		//Create new user
		String pwdHashed = UserPwd.hashPwd(pwd);
		User user = new User(fName, lName, email, pwdHashed);
		
//		response.sendRedirect(LoginController.register(user));
		String[] output = LoginController.register(user);
		request.setAttribute("errorMsg", output[0] );		
		String url = output[1];

		System.out.println("error message is: " + output[0]);
		System.out.println("path is: " + output[1]);
		
		request.getRequestDispatcher(url).forward(request,response);
	}

}
