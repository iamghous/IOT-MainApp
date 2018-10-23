package Assignment1;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class ConnectDB {
	private Connection connection = null;
	
	public Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	    /*
		String user = "test1";
		String password = "test1";
		String url = "jdbc:mysql://localhost/nomanFilms";
		**/
		String user = "ghousn";
		String password = "rerRykil2";
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/"+user;
		
		try {  Class.forName("com.mysql.jdbc.Driver").newInstance();
		// this will connect to the database and pass the connection to Connection conn
		connection = DriverManager.getConnection(url, user, password);
	     System.out.println("Connection Opened ");
	}
		catch(SQLException se) {
			// this will return message if there is any error
		    se.getMessage();
		}
	// this will return the connection
	return connection;
	
}
	
	// this method will return ResultSet
	public ResultSet getDataFromDatabase(String query) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Make a call to the connect method
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		// executes the query
		ResultSet rs = stmt.executeQuery(query);
		// writes message in console that query has executed
		System.out.println("DEBUG: Select: " + query);
		// returns ResultSet
		return rs;
		
	}
	// this is the insert method which gets 2 parameters
public void insertDataToDatabase(String sensorName , String sensorValue) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
	   // Insert query that will use to insert data in database, we will use id 10001 default so,
	// that the data we get is specific for the users
	    String query = 
	     	"insert into sensorData(userId,sensorName, sensorValue, time) "+
	      	"values('10001','"+sensorName+"','"+sensorValue+"', now());";
		// gets connection using connect method
	    Connection conn = connect();
		
		Statement stmt = conn.createStatement();
		// executes the query 
		int check = stmt.executeUpdate(query);	
		// checks if the data has been inserted or not 
		if (check > 0) {
			System.out.println("DEBUG: Inserted: " + query);
		}
		else {
			System.out.println("Sorry, could not insert the data into database, please make sure the Film Does not exist already");
		}
		// closes the connection using close connection method
		closeConnection();
		
	}
public void closeConnection() throws SQLException {
	//closes the connection
	connection.close();
	System.out.println("Connection closed ");
	
}
	
}
