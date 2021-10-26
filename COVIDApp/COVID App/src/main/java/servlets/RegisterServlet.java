package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.sendRedirect("http://localhost:8080/COVID_App/html/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pwd1 = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		
		String info = "New user: " + fName + " " + lName + " " + email + " " + pwd1 + " " + pwd2;
		String htmlOut = "<html>" + info + "</html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlOut);

		System.out.printf("New User: \n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
				fName,lName,email,pwd1,pwd2);
		
		//TODO: check that pwd == pwd2 in html before sending
		
		//TODO: check if user exists in db (email)
//		if user exists && is verified, && pwd matches LOGIN
//		if user exists && is verified && pwd ! matches LOGIN page with pwd doesn't match error
//		if user exists && ! verified verification page and send email with not verified error
//		if user doesn't exist, register, verification page and send email 

	}

}
