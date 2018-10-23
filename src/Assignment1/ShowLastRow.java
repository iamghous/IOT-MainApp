package Assignment1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/ShowLastRow")
public class ShowLastRow extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
			
	// method of HttpServlet which will be called when somebody access the web using HTTP connection
    public void doGet(HttpServletRequest request,
     		  HttpServletResponse response)	throws ServletException, IOException {

    		String data = "";
        	// this will set the response to OK 200s
    		response.setStatus(HttpServletResponse.SC_OK);
    		// this is the printerWriter which will be used to write back the response 
    		PrintWriter out = response.getWriter();	
    		String query = "SELECT * FROM sensorData WHERE entryId = (SELECT max(entryId) from sensorData)";
    	    OutputGenerator outputGenerator  = new OutputGenerator();
    		try {
    	 // calls the method of OutputGenerator class and assign the value to string
    	    data = outputGenerator.generateStructured(query);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
           // gives a message to console
    	    out.print(data);
          System.out.println("Last Row Uploaded");
         // closes the printWriter
           out.close();
   		
    }
    

}
	
	
