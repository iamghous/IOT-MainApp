package Assignment1;

import java.sql.SQLException;

import com.phidget22.PhidgetException;
import com.phidget22.RFID;
import com.phidget22.RFIDTagEvent;
import com.phidget22.RFIDTagListener;
import com.phidget22.VoltageRatioInput;
import com.phidget22.VoltageRatioInputVoltageRatioChangeEvent;
import com.phidget22.VoltageRatioInputVoltageRatioChangeListener;


public class ScanData {
	
	private String sensorName ="";
	private String sensorValue="";
	private String data ="";
	private double check = 0;
	int count = 0;
	// this method will scan the both Rfid and Slider
	public String scanDataMethod() throws PhidgetException, InterruptedException {
	
		
		// Creates variable of RFID class
		RFID rfid = new RFID();
		// Creates variable of Slider class
	    VoltageRatioInput slider = new VoltageRatioInput();

     // This is the id of your PhidgetInterfaceKit (on back of device)
	    slider.setDeviceSerialNumber(314524);
     // This is the channel your slider is connected to on the interface kit
	    slider.setChannel(1); 
	    
	    // this method will run when the slider has changed its slide
	    slider.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
	    	public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
				//sensorName = slider.getDeviceName();
					
				// gets the voltage value and puts it in the double variable
	    		Double sliderData = e.getVoltageRatio();
	    		// when you run the slider most times you get the value instantly and
	    		// you keep getting the values which you dont even want 
	    		// and because of that problem i have used this if statement 
	    		// it basically checks if the value of the voltage has a change of at least 0.0010
	    		// and the first time method runs it does not save the value
	    		// 
	    		double a = check + 0.0010;
	    		double b = check - 0.0010; 
	    	    // count is 0 at the start so first time method will not run but the second time it will 
	    		if( count != 0 && (sliderData > a || sliderData < b))  {
	    			sensorName = "Slider";
	    			sensorValue = Double.toString(sliderData);  // converts the double into string 
	    		}
	    		// change the count value  to 1 
	    		count = 1;
	    		// assign the data to check so next time method runsm it can compare between new value and the old one
	    		check = sliderData;				
				
				
				}
			});
    
    
	// Make the RFID Phidget able to detect loss or gain of an rfid card
    rfid.addTagListener(new RFIDTagListener() {
		public void onTag(RFIDTagEvent e) {
			//sensorName = rfid.getDeviceName();
			sensorName = "RFID-TAG";
			// will assign the Tag value to the string
			sensorValue = e.getTag();
			
		}
    });
   
 // gives 5 seconds to present the device
   slider.open(5000);
 // will give 5 seconds to present the device 
    rfid.open(5000);
   // will Enable the antenna of rfid reader 
    rfid.setAntennaEnabled(true);
  
  // for loop that waits for the tag for 10 seconds if not scanned, as soon as it gets scanned loop finishes 
    for(int i = 0; i < 10; i++) {
    	if(sensorName.equals("") && sensorValue.equals("")) {
    		// thread sleeps for 1 second
    		Thread.sleep(1000);
    	}else {
    		
    	}
		
    }
	// once the code overcomes the for loop it gives half a second 
    Thread.sleep(500);
		
	// checks if there is any scanned tag or not, if not it will return the message otherwise it will upload the values to the database	
    if(sensorName.equals("") && sensorValue.equals("")) {
    	System.out.println("Sorry, No Data is scanned, Please make sure you have "
    			+ "connected the RFID reader correctly");
    	
    	
    }else {
    	// assign the sensorName and sensorValue to string and separete them by line
    	data += sensorName+"\n";
    	data += sensorValue;
    	  // calls the method with two parameters
    	  ConnectDB cDB = new ConnectDB();
    	  try {
			cDB.insertDataToDatabase(sensorName, sensorValue);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
  //closes rfid
    rfid.close();
    // closes slider
    slider.close();
    
    return data;
	
	}
	
	
}
