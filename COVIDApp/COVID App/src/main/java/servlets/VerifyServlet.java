package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.User;

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
		
		String info = "Verification Code: " + v1 + v2 + v3 + v4 + v5 + v6;
		String htmlOut = "<html>" + info + "</html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlOut);

		System.out.printf("New User: \n\t%s%s%s%s%s%s\n",v1,v2,v3,v4,v5,v6);
		
		// Cam's
//    	response.setContentType("text/html;charset=UTF-8");
//    	try (PrintWriter out = response.getWriter()) {
//            
//            HttpSession session = request.getSession();
//            User user= (User) session.getAttribute("authcode");
//            
//            String code = request.getParameter("authcode");
//            
//            if(code.equals(user.getVerCode())){
//                out.println("Verification Done");
//            }else{
//                out.println("Incorrect verification code");
//            }
//    	}
//		response.sendRedirect("http://localhost:8080/COVID_App/html/profile.html");
		
		//TODO: Check against verification code expected
	}

}
