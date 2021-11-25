package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import dao.ApplicationDao;
import dao.ApplicationDaoProxy;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionFactory;
import questionnaires.QuestionSet;

/**
 * Servlet implementation class QuestionnaireServlet
 */
@WebServlet("/QuestionnaireServlet")
public class QuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Proxy Pattern
	ApplicationDaoProxy appDaoProxy = ApplicationDaoProxy.getAppDAOProxy();
       
    public QuestionnaireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//create question set to store answers to survey
		QuestionSet questionSet = new QuestionSet(1,1);
		
		// create question answer to use to store each question and answer combo
		QuestionAnswer qa;
		
		// Get time stamp of entry 
		Date date = new Date();
		Timestamp time_stamp = new Timestamp(date.getTime());
		
		//check if answer is yes or no
		for (int i = 1; i<=10; i++) {
			int j;
			if(request.getParameter("select" + i).equalsIgnoreCase("yes")){
				j=1;
			}else {
				j=2;
			}
			qa = new QuestionAnswer(i, j);
			qa.setTimestamp(time_stamp);
			questionSet.addQuestionAnswer(qa);
			System.out.println(i + " " + j);
		}
		
		User user = (User) request.getSession().getAttribute("thisUser");
		
		System.out.println(user.getUserID());
		System.out.println(questionSet.getQuestions().size());
				
		appDaoProxy.saveQuestionnaireResponse(user.getUserID(), questionSet);
		System.out.println("finished storeQuestions()");		

		// Display new page
		request.getRequestDispatcher("results.jsp").forward(request,response);
	}

}
