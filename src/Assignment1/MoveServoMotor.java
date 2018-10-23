
package Assignment1;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// this is a Web servlet which extends Http Servlet class 
@WebServlet("/MoveServoMotor")
public class MoveServoMotor extends HttpServlet {

	// The serialVersionUID is a universal version identifier for a Serializable class
	private static final long serialVersionUID = 1L;
	
	// this is built in method in Http servlet class, and it will be called when user call this webservlet
    public void doGet(HttpServletRequest request,
     		  HttpServletResponse response)	throws ServletException, IOException {
    	
           // This will set the response to OK 
           response.setStatus(HttpServletResponse.SC_OK);
           // this will create the Print Writer which will be used to send back the response
           PrintWriter out = response.getWriter();

           //  assign a parameter that is in the http request to the string
           String sPosition= request.getParameter("position");
           // convert the string to double
           Double position = Double.parseDouble(sPosition);

          // create an object of ServoMotor Class
           ServoMotor servoMotor = new ServoMotor();


           try {
        	   // call the method of Servo Motor to change the position of the Motor
        	   servoMotor.changePosition(position);
           } catch (Exception e) {
        	   // TODO Auto-generated catch block
        	   e.printStackTrace();
           }

     
         // close the PrintWriter
           out.close();
    }
	}





    


	
	
