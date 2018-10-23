

package Assignment1;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet("/ShowXml")
public class ShowXml extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
			
  // method of HttpServlet which will be called when somebody access the web using HTTP connection
    public void doGet(HttpServletRequest request,
     		  HttpServletResponse response)	throws ServletException, IOException {
           
    	// this will set the response to OK 200
		response.setStatus(HttpServletResponse.SC_OK);
		// this is the printerWriter which will be used to write back the response 
		PrintWriter out = response.getWriter();	
       OutputGenerator output = new OutputGenerator();
        String xmlData = "";
		try {
			// calls the method of OutputGenerator class and assign the value to string
			xmlData = output.generateXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
       //  send the response back to the client
		out.println(xmlData);
		// Nice output message for the client
		System.out.println("Xml Uploaded");
	   out.close();
	}  }

	
	
