package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MainAuto extends Command {
	
	Timer m_timer;
	double m_time;

    public MainAuto(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_chassis);
    	m_timer = new Timer();
    	m_time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_chassis.resetAngleEncoder();
    	Robot.m_chassis.stopALL();
    	m_timer.reset();
    	m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_chassis.joystickDrive(0.5, 0, 0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return m_timer.get()>m_time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_chassis.stopALL();
    	m_timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
