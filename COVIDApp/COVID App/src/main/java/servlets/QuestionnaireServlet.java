package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		 */
		String select = request.getParameter("select"); // represents question 1 answer
		String select2 = request.getParameter("select2");// represents question 2 answer
		String select3 = request.getParameter("select3"); // represents question 3 answer
		String select4 = request.getParameter("select4"); // represents question 4 answer
		String select5 = request.getParameter("select5"); // represents question 5 answer
		String select6 = request.getParameter("select6"); // represents question 6 answer
		String select7 = request.getParameter("select7"); // represents question 7 answer
		String select8 = request.getParameter("select8"); // represents question 8 answer
		String select9 = request.getParameter("select9"); // represents question 9 answer
		String select0 = request.getParameter("select0"); // represents question 10 answer
		
		
		/* Simple outputting the answer for each questions */
		System.out.printf("Q1: \t%s\n",select);
		System.out.printf("Q2: \t%s\n",select2);
		System.out.printf("Q3: \t%s\n",select3);
		System.out.printf("Q4: \t%s\n",select4);
		System.out.printf("Q5: \t%s\n",select5);
		System.out.printf("Q6: \t%s\n",select6);
		System.out.printf("Q7: \t%s\n",select7);
		System.out.printf("Q8: \t%s\n",select8);
		System.out.printf("Q9: \t%s\n",select9);
		System.out.printf("Q10: \t%s\n",select0);
		
		
		
	}

}
