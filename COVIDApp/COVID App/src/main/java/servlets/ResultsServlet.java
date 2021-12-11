package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.User;
import app.UserBuilder;
import dao.ApplicationDaoProxy;
import dao.DBUtilities;
import login.LoginController;
import questionnaires.QuestionAnswer;
import questionnaires.Result;


@WebServlet("/ResultsServlet")
public class ResultsServlet extends HttpServlet implements Observer{
	private static final long serialVersionUID = 1L;
	private User user;
	private LoginController lc;

	//Proxy Pattern
	ApplicationDaoProxy appDaoProxy = ApplicationDaoProxy.getAppDAOProxy();
	
	public ResultsServlet() {
		super();
		user = new UserBuilder().createUser();
		lc = new LoginController();
		lc.registerObserver(this);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get individual lines of results and create a summarized result from it
		
		// Create ArrayList
		ArrayList<Result> resultsOut = new ArrayList<Result>();
		
		// Create output table
		try {
			List<QuestionAnswer> results = appDaoProxy.readFullSurveyResults();
			
			// Display the results 1 question per line
			String x = null;
			Timestamp ts = null;
			int cnt = 0;
			Result result;
			
			for(int i = 0; i < results.size(); i++) {
				// Set the values to compare against for first loop
				if(i == 0) {
					x = results.get(i).getUserID();
					ts = results.get(i).getTimestamp();	
				}
				
				// Create new Result
				result = new Result();
				result.setFirstName(results.get(i).getfName());
				result.setLastName(results.get(i).getlName());
				result.setTimeAnswered(results.get(i).getTimestamp());
				
				// Get user details to display
				String uID = results.get(i).getUserID();
				Timestamp time_stamp = results.get(i).getTimestamp();
				
				// Get cumulative results for user at given time
				if (x.equals(uID) && (ts.equals(time_stamp))) {
					// if answer is wrong
					if (results.get(i).getAnswerID() != results.get(i).getRightAns()) {
						cnt++;
					}
					// Set results (so far)
					if (cnt == 0) {
						result.setGoodToGo("No");
					}
					else {
						result.setGoodToGo("Yes");
					}
					
					// Check if is end of current set of results 
					Boolean isLastRecord = (i == results.size() - 1);
					if (isLastRecord) {
						resultsOut.add(result);
						break;
					}
					
					// Check if is last answer in a set of results 
					Boolean isNewRecord = !(x.equals(results.get(i+1).getUserID()) && (ts.equals(results.get(i+1).getTimestamp())));
					if(isNewRecord) {
						resultsOut.add(result);
					}
					
				// reset for next set of results
				}else {
					x = results.get(i).getUserID();
					ts = results.get(i).getTimestamp();	
					cnt = 0;
				}
			}
			
			request.setAttribute("resultsOut", resultsOut);
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		System.out.println("Successfully read readQuestions()");

		// Display new page
		request.getRequestDispatcher("results.jsp").forward(request,response);
	}

	@Override
	public void update(User user) {
		this.user = user;
	}

}
