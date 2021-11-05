package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.User;
import dao.ApplicationDao;
import questionnaires.QuestionAnswer;
import questionnaires.QuestionSet;

/**
 * Servlet implementation class QuestionnaireServlet
 */
@WebServlet("/QuestionnaireServlet")
public class QuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionnaireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//create question set to store answers to survey
		QuestionSet questionSet = new QuestionSet(1,1);
		
		//check if answer is yes or no
		for (int i = 1; i<=10; i++) {
			int j;
			if(request.getParameter("select" + i).equalsIgnoreCase("yes")){
				j=1;
			}else {
				j=2;
			}
			questionSet.addQuestionAnswer(new QuestionAnswer(i, j));
			System.out.println(i + " " + j);
		}
		
		User user = (User) request.getSession().getAttribute("thisUser");
		
		System.out.println(user.getUserID());
		System.out.println(questionSet.getQuestions().size());
				
		ApplicationDao.storeQuestions(user.getUserID(), questionSet);
		System.out.println("finished storeQuestions()");		

		String url = "/ResultsServlet";
				
		// Display new page
				
		request.getRequestDispatcher("results.jsp").forward(request,response);
	}

}
