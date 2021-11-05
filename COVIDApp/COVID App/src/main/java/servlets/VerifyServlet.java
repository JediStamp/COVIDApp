package servlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import app.User;

import app.User;
import login.LoginController;

/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String v1 = request.getParameter("v1");
		String v2 = request.getParameter("v2");
		String v3 = request.getParameter("v3");
		String v4 = request.getParameter("v4");
		String v5 = request.getParameter("v5");
		String v6 = request.getParameter("v6");
		
		String verCode = v1 + v2 + v3 + v4 + v5 + v6;
//		String info = "Verification Code: " + v1 + v2 + v3 + v4 + v5 + v6;
//		String htmlOut = "<html>" + info + "</html>";
//		PrintWriter writer = response.getWriter();
//		writer.write(htmlOut);
//
//		System.out.printf("New User: \n\t%s%s%s%s%s%s\n",v1,v2,v3,v4,v5,v6);
		
		// Check verification code against value returned by user
//		String userID1 = request.getAttribute("userID").toString();
//		String userID = (String) request.getSession().getAttribute("userID");
		User user = (User) request.getSession().getAttribute("thisUser");
		String userID = user.getUserID();
		System.out.println("Verify page: userID is: " + userID);
		
		String[] output = LoginController.verify(userID, verCode);
		// Set error message & URL
		request.setAttribute("errorMsg", output[0] );
		
		String url = output[1];
		user.setUserID(output[2]);
		user.setFirstName(output[3]);
		user.setLastName(output[4]);
		user.setEmail(output[5]);
		
		request.getSession().setAttribute("thisUser", user);
		
//		request.getSession().setAttribute("userID", output[2]);
		request.getSession().setAttribute("firstName", output[3]);
		request.getSession().setAttribute("lastName", output[4]);
		request.getSession().setAttribute("email", output[5]);
		
		// Print them to the screen
		System.out.println("VerifyServlet: Printing output parameters...");
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
