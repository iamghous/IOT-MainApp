package Assignment1;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OutputGenerator {

////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// XML GENERATOR METHOD ////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String generateXml() throws Exception{
		String xmlData = "";
		ConnectDB cDB = new ConnectDB();
		try{
			    // query
				String query = "select userId,sensorName, sensorValue, time from sensorData";
			     // executes the query using call to method from ConnectDB class
				ResultSet rs = cDB.getDataFromDatabase(query);	
				
			        // creates new object of DocumentBuilderFactory
					DocumentBuilderFactory dbFactory =
							DocumentBuilderFactory.newInstance();
					// intialize new DocumentBuilder object and assign it DocumentBuilderFactory new Document
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					// creates new Document object
					Document doc = dBuilder.newDocument();

					// create new root element and name it data
					Element rootElement = doc.createElement("data");
					// add the element in the document
					doc.appendChild(rootElement);
					// loops through the database data
					while (rs.next()) {
				 // get data from database and assign it to the variables
				int id = rs.getInt("userId");
				// convert integer to String
				String id2 = Integer.toString(id);
				String name = rs.getString("sensorName");
				String value = rs.getString("sensorValue");
				Timestamp time = rs.getTimestamp("time");
				// convert time stamp to String
				String time2 = time.toString();
				  
				
				 // create sub element and call it values
				 Element values = doc.createElement("values");
				 // and append it in the root Element which is "data"
			     rootElement.appendChild(values);

			     // create new element and call it userId
			     Element userId = doc.createElement("userId");
			     // add String "id2" to the value of the element 
			     userId.appendChild(doc.createTextNode(id2));
			     // append the element entryID to its parent element values
			     values.appendChild(userId);
			     
			     
			  // create new element and call it sensorName
			     Element sensorName = doc.createElement("sensorName");
			     // add String "name" to the value of the element 
			     sensorName.appendChild(doc.createTextNode(name));
			  // append the element sensorName to its parent element values
			     values.appendChild(sensorName);
			     
			     
			  // create new element and call it sensorValue
			     Element sensorValue = doc.createElement("sensorValue");
			     // add String "value" to the value of the element 
			     sensorValue.appendChild(doc.createTextNode(value));
			  // append the element sensorvalue to its parent element values
			     values.appendChild(sensorValue);
			     
			     
			  // create new element and call it timeInserted
			     Element timeInserted = doc.createElement("timeInserted");
			     // add String "time2" to the value of the element 
			     timeInserted.appendChild(doc.createTextNode(time2));
			  // append the element timeInserted to its parent element values
			     values.appendChild(timeInserted);
			    

					}
					// create new object of Transformer class
					Transformer transformer = TransformerFactory.newInstance().newTransformer();
					// create new object of StreamResult
					StreamResult result = new StreamResult(new StringWriter());
					// create new Object of DOMSource and assign it the Document  
					DOMSource source = new DOMSource(doc);
					// transform the whole document using this method transform()
					transformer.transform(source, result);

					// assign the whole document to the string
					xmlData = result.getWriter().toString();
					
		}

		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
		// closes the opened connection
		cDB.closeConnection();
		// return xml Data
		  return xmlData;
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// JSON GENERATOR METHOD///////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String generateJson() throws Exception {
		
		// query
		String query = "select userId,sensorName, sensorValue, time from sensorData";
	     // executes the query using method from ConnectDB class
		ConnectDB cDB = new ConnectDB();
		ResultSet rs = cDB.getDataFromDatabase(query);	
		
		JSONArray jsonArray = new JSONArray();

       // loops through the data got from database 
		while (rs.next()) {

			try {
                // assign the data to variables
				int id = rs.getInt("userId");
				String name = rs.getString("sensorName");
				String value = rs.getString("sensorValue");
				Timestamp time = rs.getTimestamp("time");
				
				JSONObject jsonObject = new JSONObject();
				// insert into JSON object the current values 
				jsonObject.put("userId", id);
				jsonObject.put("sensorName", name);
				jsonObject.put("sensorValue", value);
				jsonObject.put("timeInserted", time);
				// insert this JSON object into JSONarray
				jsonArray.put(jsonObject);

			}
			catch(Exception e) {
				e.getMessage();

			}
		}
		// outputs the whole JOSN data in the response
	   //closes the opened connection
		cDB.closeConnection();
		// returns the String
		return jsonArray.toString();
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// TEXT GENERATOR METHOD///////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public String generateStructured(String query) throws Exception {
	
	String data = "";
		
	// gets the query using parameter
	// call the method of ConnectDB class and get ResultSet in return
    ConnectDB cDB = new ConnectDB();
    ResultSet rs = cDB.getDataFromDatabase(query);
	
	
	// generates an output for the response
	data += "<table> <thead>" + 
    "<tr>"+
      "<th>User ID</th>" + 
      "<th>Sensor Name</th>" + 
      "<th>Sensor Value</th>" +
      "<th>Time Inserted</th>" + 
    "</tr>" + 
  "</thead> <tbody>";
	// loops through all the data got from the database
	while (rs.next()) {
	
	// assign values retrieved from the database on to the variables
	int id = rs.getInt("userId");
	String name = rs.getString("sensorName");
	String value = rs.getString("sensorValue");
	Timestamp time = rs.getTimestamp("time");
	
	// outputs them nicely in the response using html tags
	data += "<tr>";
	data += "<td>" + id + "</td>";
	data += "<td>" + name + "</td>";
	data += "<td>" + value + "</td>";
	data += "<td>" + time + "</td>";
	data += "</tr>";
   
}
	data += "</tbody></table>";
		
	cDB.closeConnection();
		return data;
	}
	
	
}
