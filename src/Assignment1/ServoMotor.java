package Assignment1;

import com.phidget22.*;

// this class will be called from MoveServoMotorClass
public class ServoMotor {
    // creating instance of Servo Motor
	static RCServo ch;

	public void changePosition(double position) throws PhidgetException, InterruptedException {
		// initializing the variable
		ch = new RCServo();
		
		// giving 5 seconds to attach the device
		 ch.open(5000);
		 // changing position of the servo Motor with given parameter
         ch.setTargetPosition(position);
         // Engage the motor
         ch.setEngaged(true);
         // wait 1 second for the motor to go to its angle then close it 
         Thread.sleep(1000);
         // Console message
         System.out.println("Motor moved to " + position);
         // close the motor
		 ch.close();
	}
	
	
}
