package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import questionnaires.QuestionAnswer;


@WebServlet("/ResultsServlet")
public class resultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public resultsServlet() {
        super();
//		try {
//			List<QuestionAnswer> results = ApplicationDao.readSurveyResults();
//			for(int i = 0; i < results.size(); i++) {
//				System.out.println(results.get(i).getUserID() + "qID " + results.get(i).getQuestionID()
//						+ "a ID " + results.get(i).getAnswerID() + " RightAns: " + results.get(i).getRightAns());
//			
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("Successfully read readQuestions()");
        
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lineBreak = "<br />";
		try {
			List<QuestionAnswer> results = ApplicationDao.readSurveyResults();
			for(int i = 0; i < results.size(); i++) {
				String uID = results.get(i).getUserID();
				String lineOut ="<tr><td>" + uID + "</td><td>";
				while(results.get(i).getUserID().equals(uID)) {
					lineOut += "";
				}
				response.getWriter().write(results.get(i).getUserID() + "qID " + results.get(i).getQuestionID()
						+ "a ID " + results.get(i).getAnswerID() + " RightAns: " + results.get(i).getRightAns());
				response.getWriter().write(lineBreak);
				response.getWriter().write(lineBreak);
				
//				response.getWriter().write(lineBreak);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully read readQuestions()");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		doGet(request, response);
	}

}
