package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import app.UserPwd;
import dao.ApplicationDao;

@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("pwd");
		String pwdHashed = UserPwd.hashPwd(pwd);
		
		// Update Password
		User user = (User) request.getSession().getAttribute("thisUser");
		user.setPassword(pwdHashed);
		ApplicationDao.updateUserPwd(pwdHashed, user.getUserID());
		
		// Update Session Parameters
		request.getSession().setAttribute("thisUser",  user);
		
		// Go back to profile page
		String url = "profile.jsp";
		request.getRequestDispatcher(url).forward(request,response);
	}

}
