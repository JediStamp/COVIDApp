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
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lineOut = "<table class=\"resultsTable\"><tr><th>User ID</th><th>Question ID</th><th>Answer ID</th><th>Correct Answer</th></tr>";
		try {
			List<QuestionAnswer> results = ApplicationDao.readSurveyResults();
			for(int i = 0; i < results.size(); i++) {
				String uID = results.get(i).getUserID();
				lineOut += "<tr><td>" + uID + "</td>";

//				if(results.get(i).getUserID().equals(uID)) {
					lineOut += "<td>" + results.get(i).getQuestionID() + "</td>";
					lineOut += "<td>" + results.get(i).getAnswerID() + "</td>";
					if (results.get(i).getAnswerID() == results.get(i).getRightAns()) {
						lineOut += "<td style=\"background-color:green;\">" + "Yes" + "</td>";
					}
					else {
						lineOut += "<td style=\"background-color:red;\">" + "No" + "</td>";
					}

					
//				}
				lineOut += "</tr>";
				}
//				response.getWriter().write(results.get(i).getUserID() + "qID " + results.get(i).getQuestionID()
//						+ "a ID " + results.get(i).getAnswerID() + " RightAns: " + results.get(i).getRightAns());
//				response.getWriter().write(lineBreak);
//				response.getWriter().write(lineBreak);
				
//				response.getWriter().write(lineBreak);
				
			lineOut += "</table>";
			request.setAttribute("lineOut", lineOut);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully read readQuestions()");
		// Display new page
		
		request.getRequestDispatcher("results.jsp").forward(request,response);
	}

}
