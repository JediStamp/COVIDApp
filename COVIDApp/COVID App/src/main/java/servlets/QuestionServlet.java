package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Request Parameters
		String question = request.getParameter("question_content");
		String answer = request.getParameter("correct_ans");
		
		System.out.println("Adding question " + question + " Correct answer is: " + answer);
		
	
		// Display new page
		request.getRequestDispatcher("question.jsp").forward(request,response);
	}

}
