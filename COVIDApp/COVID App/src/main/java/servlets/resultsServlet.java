package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import dao.DBUtilities;
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
		String lineOut = "<table class=\"resultsTable\"><tr><th>First Name</th><th>Last Name</th><th>Time Answered</th><th>Clear</th></tr>";
		try {
			List<QuestionAnswer> results = ApplicationDao.readFullSurveyResults();
			
			// Display the results 1 question per line
			String x = null;
			Timestamp ts = null;
			int cnt = 0;
			for(int i = 0; i < results.size(); i++) {
				// Set the values to compare against for first loop
				if(i == 0) {
					x = results.get(i).getUserID();
					ts = results.get(i).getTimestamp();				
				}
				
				// Get user details to display
				String uID = results.get(i).getUserID();
				String fName = results.get(i).getfName();
				String lName = results.get(i).getlName();
				Timestamp time_stamp = results.get(i).getTimestamp();
				
				// Get cumulative results for user at given time
				if (x.equals(uID) && (ts.equals(time_stamp))) {
					if (results.get(i).getAnswerID() != results.get(i).getRightAns()) {
						cnt++;
					}

					// End of current set of results or last set of results display a line
					Boolean isLastRecord = (i == results.size() - 1);
					if (isLastRecord) {
						lineOut += "<tr><td>" + fName + "</td><td> " + lName + "</td><td>" + time_stamp + "</td>";
						if (cnt == 0) {
							lineOut += "<td style=\"background-color:#6AF190;\">" + "Yes" + "</td><tr>";
						}
						else {
							lineOut += "<td style=\"background-color:#BA3B54;\">" + "No" + "</td><tr>";
						}
						break;
					}
					Boolean isNewRecord = !(x.equals(results.get(i+1).getUserID()) && (ts.equals(results.get(i+1).getTimestamp())));
					if(isNewRecord) {
						lineOut += "<tr><td>" + fName + "</td><td> " + lName + "</td><td>" + time_stamp + "</td>";
						if (cnt == 0) {
							lineOut += "<td style=\"background-color:#6AF190;\">" + "Yes" + "</td><tr>";
						}
						else {
							lineOut += "<td style=\"background-color:#BA3B54;\">" + "No" + "</td><tr>";
						}
					}
				// reset for next set of results
				}else {
					x = results.get(i).getUserID();
					ts = results.get(i).getTimestamp();	
					cnt = 0;
				}
			}

			lineOut += "</table>";
			request.setAttribute("lineOut", lineOut);
		} catch (SQLException e) {
			DBUtilities.processException(e);
		}
		System.out.println("Successfully read readQuestions()");

		// Display new page
		request.getRequestDispatcher("results.jsp").forward(request,response);
	}

}
