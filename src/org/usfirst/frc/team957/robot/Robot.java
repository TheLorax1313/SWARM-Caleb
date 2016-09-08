

package org.usfirst.frc.team957.robot;


import edu.wpi.first.wpilibj.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import edu.wpi.first.wpilibj.CameraServer;

import edu.wpi.first.wpilibj.SampleRobot;

import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.Relay;

/**

 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;  

    CameraServer server;
    

 // Defines Joystick 1 and 2 and defines all 4 drive CAN Talons

    Joystick controller1 = new Joystick(0);

    CANTalon t0 = new CANTalon(0);
    CANTalon t1 = new CANTalon(1);
    CANTalon t2 = new CANTalon(2);
    CANTalon t3 = new CANTalon(3);   

    //Ball Grabber Talon
    CANTalon t5 = new CANTalon(5);

    //Shooter Talon
    Talon nT0 = new Talon(0);

    //Ball Feed Talon
    Talon nT1 = new Talon(1);

    //Ball Roller Spike
   
    Relay exampleRelay = new Relay(0, Relay.Direction.kForward);
    DigitalInput limitswitch0;
    DigitalInput limitswitch1;
    double shootertime;
    double feedtime;
    int shootTime2;
    int ShootStateHold;
    
    int warmTime;
    int shootTime;
    
    double state;
    double speed = 0.75;
    double armTime;
    double rollTime;
    /**

     * This function is run when the robot is first started up and should be

     * used for any initialization code.

     */

    public void robotInit() {
       
    	chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);

        
        server = CameraServer.getInstance();
        server.setQuality(5);
        //\the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");

    }

    

    /**

     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
     * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
     * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
     * below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
     * If using the SendableChooser make sure to add them to the chooser code above as well.
     */

    public void autonomousInit() {
        autoSelected = (String) chooser.getSelected();
//       autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
        System.out.println("Auto selected: " + autoSelected);

    }


    /**
     * This function is called periodically during autonomous
     */

   

    public void autonomousPeriodic() {

        switch(autoSelected) {
        case customAuto:
        //Put custom auto code here   
            break;
        
        case defaultAuto:
        default:
        
        	
        			
        	
        	
        	
        	
        	
        	
        	
            break;
        }
    }

    /**
     * This function is called periodically during operator control
     */

    public void teleopPeriodic() {

        int POV = controller1.getPOV();
        if(POV == 45 || POV == 225){
        	POV = POV + 45;
        
        }
        if(POV == 135 || POV == 315){
        	POV = POV - 45;
        }
        SmartDashboard.putNumber("povUp", POV);
    //forward
        if(POV == 0){
            t0.set(-1 * speed); //right talons
            t1.set(-1 * speed);
            t2.set(-1 * -speed); //left talons
            t3.set(-1 * -speed);
        }
   //backward
        if(POV == 180){
            t0.set(1 * speed); //right talons
            t1.set(1 * speed);
            t2.set(1 * -speed); //left talons
            t3.set(1 * -speed);
        }
   //turn left
        if(POV == 90){
            t0.set(1 * speed); //right talons
            t1.set(1 * speed);
            t2.set(-1 * -speed); //left talons
            t3.set(-1 * -speed);
        }	
   //turn right
        if(POV == 270){
            t0.set(-1 * speed); //right talons
            t1.set(-1 * speed);
            t2.set(1 * -speed); //left talons
            t3.set(1 * -speed);
        }    	
 
    	
          /** DRIVETRAIN **/  
        // Asks the controller (Xbox360 or Dualshock 4 defined as XB360 using DS4W software)
        // to give variables labeled axis1 and axis2 axis 1 and 5, and place them in variables,
        //respectively.

        double axis1 = controller1.getRawAxis(1);
        double axis2 = controller1.getRawAxis(5);

        
       //Takes axis1 or 2 and puts them in the SRX move command, thereby moving the motors

        if(POV == -1){
        	
        	t0.set(axis1 * speed); //right talons
            t1.set(axis1 * speed);
            t2.set(axis2 * -speed); //left talons
            t3.set(axis2 * -speed);
        	
        }
       
        boolean but11 = controller1.getRawButton(11);
        if(but11 == true){
            //RIGHT
        	t0.set(0.75); //right talons
            t1.set(0.75);
            t2.set(0.75); //left talons
            t3.set(0.75);
        } 
        boolean but12 = controller1.getRawButton(12);
        if(but12 == true){
            //LEFT
        	t0.set(-0.75); //right talons
            t1.set(-0.75);
            t2.set(-0.75); //left talons
            t3.set(-0.75);
        } 
        
        
            //Talons and variables for ball feed and shooter
        


        
        //this checks if Button 5 is being pressed, and then is reset to false if true later
        



        boolean but14 = controller1.getRawButton(14);
        
        if(true == but14){
            if(shootertime == 0){
                shootertime = 120;    
            }
        }
        if(shootertime > 0){
            nT0.set(-1);
            if(shootertime < 60){
                nT1.set(-1);
            }
            shootertime--;
        }else{
            nT1.set(0);
            nT0.set(0);
        }
        boolean but5 = controller1.getRawButton(5);
        if(true == but5){
    		
    		nT1.set(1);
        }
        //grabber

        

        
        //Ball Roller
        
        boolean but6 = controller1.getRawButton(6);
        if(true == but6){
        	exampleRelay.set(Relay.Value.kForward);

        }else{
        
        	
    			exampleRelay.set(Relay.Value.kOff);
        	}
      
    	    
        
        
        double axis5 = (controller1.getRawAxis(4) + 1)/4;
        axis5 = axis5 - (controller1.getRawAxis(3) + 1)/4;
        
        SmartDashboard.putNumber("Arm Speed",axis5);

        boolean but4 = controller1.getRawButton(4);
        if(but4 == true){
        	if(armTime < 1){
        		armTime = 60;
        	}
        }
        if (armTime > 0){
        	armTime--;
        	t5.set(-0.5);
        	
        }else{
        	t5.set(0+axis5);
        	
        }
        
        
        
     /* int shootStateF = shootState;
        int shootTime2F = shootTime2;
                      
        boolean but13 = controller1.getRawButton(14);
      

        if (true == but13 && shootStateF == 1){
            shootStateF = 2;
            shootTime2F = 100;
        }

        if (shootTime2F < 80 && shootStateF == 2){
            shootStateF = 3;    
        }

        if (shootTime2F < 0){
            shootStateF = 1;

        }

        switch(shootStateF)

        {

        case '2':

            nT0.set(-1);

            shootTime2F--;

            break;

            

        case '3':

            nT1.set(-1);

            shootTime2F--;

            break;

            

        case '1': //default case

            nT0.set(0);

            nT1.set(0);

            shootTime2F = 0;    

        }  */

        //t5.enableLimitSwitch(true, true);
        //t5.enableBrakeMode(true);
        state++;
        int ShootState = ShootStateHold;
        if(state < 2 ){
        	ShootState = 1;
        }
        
       /*
        
        boolean but4 = controller1.getRawButton(4); //start button
        boolean but3 = controller1.getRawButton(3); //shoot button
        
        
        if(ShootState == 1){
        	if(but4 == true){
        		ShootState = 2;
        		warmTime = 40;
        	}
        }
        		
        if(ShootState == 2){
        	if(but4 == true){
        		ShootState = 1;
        		warmTime = 40;
        	}
        	
        	if(but3 == true ){
        		ShootState = 3;
        		shootTime = 60;
        	}
        }
        
        if(ShootState == 3 ){
        	ShootState = 1;
        }
        
       
        
        switch(ShootState)
        {
        case '1': //default case
            nT0.set(0); //shooter
            nT1.set(0); //feed
           break;
        
        case '2': //Shooter warm up case
        	nT0.set(-1); //shooter
        	nT1.set(0);  //feed
        	if(warmTime > 0){
        	warmTime--;	
        	}
        	break;
        	
        case '3': //Shooting case
        	nT0.set(-1); //shooter
        	nT1.set(-1);  //feed
        	shootTime--;
        	break;
        	
        
        }
        
        SmartDashboard.putNumber("state",ShootStateHold);
        
        ShootStateHold = ShootState ;
        */
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}



