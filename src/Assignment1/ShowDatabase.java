
package Assignment1;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet("/ShowDatabase")
public class ShowDatabase extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
			
// method of HttpServlet which will be called when somebody access the web using HTTP connection
    public void doGet(HttpServletRequest request,
     		  HttpServletResponse response)	throws ServletException, IOException {

    	String data = "";
    	// this will set the response to OK 200s
		response.setStatus(HttpServletResponse.SC_OK);
		// this is the printerWriter which will be used to write back the response 
		PrintWriter out = response.getWriter();	
		try {
		String query = "select userId,sensorName, sensorValue, time from sensorData";
		// creates variable of OutputGenerator class
	    OutputGenerator outputGenerator  = new OutputGenerator();
	    // calls the method of outputGenerator class and assign the value to string
	    data = outputGenerator.generateStructured(query);
	    
		} catch (Exception e) {
			e.printStackTrace();
		}
       // gives a message to console
	    out.print(data);
      System.out.println("Database Uploaded");
     // closes the printWriter
       out.close();
}  
    }
	
	
