package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shootCommand extends Command {
	
	boolean m_check;

    public shootCommand(boolean set) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_check = set;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_intake.setWheelSpeed(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (m_check) {
    	Robot.m_intake.setSolenoid(false);
    	Robot.m_intake.setWheelSpeed(-0.8, -0.8);
    	}
    	else if (!m_check){
    		Robot.m_intake.setSolenoid(true);
    		Robot.m_intake.setWheelSpeed(0.8, 0.8);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intake.setWheelSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
