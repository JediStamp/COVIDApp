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
import dao.ApplicationDao;
import dao.ApplicationDaoProxy;
import dao.ApplicationDaoProxy;


@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;

	private User user;
	private LoginController lc;
	


	//Proxy Pattern
	ApplicationDaoProxy appDaoProxy = ApplicationDaoProxy.getAppDAOProxy();
       

    public ChangePassServlet() {
        super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get request parameters
		String pwd = request.getParameter("pwd");
		String pwdHashed = UserPwd.hashPwd(pwd);
		
		// Update Password
		user = (User) request.getSession().getAttribute("thisUser");
		user.setPassword(pwdHashed);
		appDaoProxy.updateUserPwd(pwdHashed, user.getUserID());
		
		// Set URL
		String url = "profile.jsp";

		
		// Update Session Parameters
		request.getSession().setAttribute("thisUser",  user);
		
		// Display Change Pass Servlet info
		display(pwd, url);
		
		// Display new page
		request.getRequestDispatcher(url).forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}

	private void display(String pwd, String url) {
		System.out.printf("ChangePassServlet:\n");
		System.out.printf("\tChange User Password: \n\t%s\n\t%s\n\n\t%s\n\t%s\n",
					user.getFirstName(), user.getLastName(), user.getEmail(), pwd);
		
		// Print outputs to screen
		System.out.printf("\tpath is: %s\n\n", url);
		
	}
}
