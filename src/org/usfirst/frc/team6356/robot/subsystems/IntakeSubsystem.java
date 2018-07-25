package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.commands.JoystickRotate;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP intakeL;
	VictorSP intakeR;
	VictorSP rotate;
	
	public IntakeSubsystem() {
		intakeL = RobotMap.intakeLeft;
		intakeR = RobotMap.intakeRight;
		rotate = RobotMap.intakeRotate;
		
		intakeL.setInverted(true);
		RobotMap.m_compressor.start();
	}
	
	public void setWheelSpeed(double leftSpeed, double rightSpeed) {
		intakeL.set(leftSpeed);
		intakeR.set(rightSpeed);
	}
	
	public void setRotateSpeed(double speed) {
		rotate.set(speed);
	}

	public void setSolenoid(boolean set) {
		if(set) RobotMap.m_solenoid.set(Value.kForward);
		else if(!set) RobotMap.m_solenoid.set(Value.kReverse);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickRotate());
    }
}

