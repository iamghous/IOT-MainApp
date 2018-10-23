package Assignment1;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.phidget22.PhidgetException;



@WebServlet("/Scan")
public class Scan extends HttpServlet { 
     
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		
    
    
     
		 	// method of HttpServlet which will be called when somebody access the web using HTTP connection
	public void doGet(HttpServletRequest request,
    		  HttpServletResponse response)	throws ServletException, IOException {
		
		// this will set the response to OK 200
		response.setStatus(HttpServletResponse.SC_OK);
		// this is the printerWriter which will be used to write back the response 
		PrintWriter out = response.getWriter();	
        String data = "";		
	// creates a variable of scanData class
     ScanData s = new ScanData();
      try {
    	  // calls the method and assign the value to string
	data =  s.scanDataMethod();
    } catch (PhidgetException | InterruptedException e) {
	e.printStackTrace();
    }
		// sends the response to the webservlet
		out.println(data);	
		// closes the output
		out.close();
		}	
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	

