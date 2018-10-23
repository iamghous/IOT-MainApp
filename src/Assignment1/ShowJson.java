package Assignment1;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/ShowJson")
public class ShowJson extends HttpServlet {
	private static final long serialVersionUID = 1L;		
 // method of HttpServlet which will be called when somebody access the web using HTTP connection
    public void doGet(HttpServletRequest request,
     		  HttpServletResponse response)	throws ServletException, IOException {
    		// this will set the response to OK 200
    			response.setStatus(HttpServletResponse.SC_OK);
    			// this is the printerWriter which will be used to write back the response 
    			PrintWriter out = response.getWriter();	
    			// create a variable of OutputGenerator class 
    			 OutputGenerator output = new OutputGenerator();
    		        String jsonData = "";
    				try {
    					// calls the method of OutputGenerator class and assign the value to string
    					jsonData = output.generateJson();
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
              out.println(jsonData);
    		// prints the Nice message to the console
    		System.out.println("Json Uploaded");
    		// closes the Printer
    		out.close();
    	}  }



    


	
	
