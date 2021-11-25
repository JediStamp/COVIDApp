package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		  )
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get file and file name
	    Part filePart = request.getPart("file");
	    String fileName = filePart.getSubmittedFileName();
	    
	    // Should save file and display it on the profile.jsp
	    System.out.println(filePart + " : " + fileName);
	    
	    // Then would update the user to have vax status == yes
	    
		// Display new page
		request.getRequestDispatcher("profile.jsp").forward(request,response);
	}

}
