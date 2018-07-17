package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwerveAngle extends Command {
	
	private double _angle;

    public SwerveAngle(double angle) {
    	requires(Robot.m_chassis);
    	_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_chassis.stopALL();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_chassis.setAngle(_angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
        //needs to add PID on target methods
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_chassis.stopALL();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
