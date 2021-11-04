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


@WebServlet("/resultsServlet")
public class resultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public resultsServlet() {
        super();
        try {
			doGet(null, null);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lineBreak = "<br />";
		try {
			List<QuestionAnswer> results = ApplicationDao.readQuestions();
			for(int i = 0; i < results.size(); i++) {
				response.getWriter().write(results.get(i).getUserID());
				response.getWriter().write(lineBreak);
				response.getWriter().write("qID " + results.get(i).getQuestionID());
				response.getWriter().write(lineBreak);
				response.getWriter().write("a ID " + results.get(i).getAnswerID());
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
