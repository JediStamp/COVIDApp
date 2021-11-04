package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionnaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Questionnaire Process Started...");
		response.sendRedirect("questionnaire.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		/* 
		 * These strings below will obtain the Yes or No value from radio buttons in fill in questionnaire page
//		 */
//		String select1 = request.getParameter("select1"); // represents question 1 answer
//		String select2 = request.getParameter("select2");// represents question 2 answer
//		String select3 = request.getParameter("select3"); // represents question 3 answer
//		String select4 = request.getParameter("select4"); // represents question 4 answer
//		String select5 = request.getParameter("select5"); // represents question 5 answer
//		String select6 = request.getParameter("select6"); // represents question 6 answer
//		String select7 = request.getParameter("select7"); // represents question 7 answer
//		String select8 = request.getParameter("select8"); // represents question 8 answer
//		String select9 = request.getParameter("select9"); // represents question 9 answer
//		String select10 = request.getParameter("select10"); // represents question 10 answer
		
		
//		/* Simple outputting the answer for each questions */
//		System.out.printf("Q1: \t%s\n",select1);
//		System.out.printf("Q2: \t%s\n",select2);
//		System.out.printf("Q3: \t%s\n",select3);
//		System.out.printf("Q4: \t%s\n",select4);
//		System.out.printf("Q5: \t%s\n",select5);
//		System.out.printf("Q6: \t%s\n",select6);
//		System.out.printf("Q7: \t%s\n",select7);
//		System.out.printf("Q8: \t%s\n",select8);
//		System.out.printf("Q9: \t%s\n",select9);
//		System.out.printf("Q10: \t%s\n",select10);
		
		//create question set
		
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
		
		String userID = (String) request.getSession().getAttribute("userID");
		
		System.out.println(userID);
		System.out.println(questionSet.getQuestions().size());
				
		ApplicationDao.storeQuestions(userID, questionSet);
		
		
		
		
		//call to get questions 
		System.out.println("finished storeQuestions()");
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

		
		
		
		//redirect to results page
		// Set error message & URL
				request.setAttribute("errorMsg", "");	
				//request.getSession().setAttribute("userID", output[2]);
				String url = "results.jsp";
				
				// Print them to the screen
//				System.out.println("RegisterServlet: Printing output parameters...");
//				System.out.println("error message is: " + output[0]);
//				System.out.println("path is: " + output[1]);
//				System.out.println("userID is: " + output[2]);
				
				// Display new page
				
//				request.getRequestDispatcher(url).forward(request,response);
		
			
	}

}
