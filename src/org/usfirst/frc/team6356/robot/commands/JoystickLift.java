package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickLift extends Command {

    public JoystickLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_lift.setLiftSpeed(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_lift.setLiftSpeed(Robot.m_oi.operator.getRawAxis(1));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_lift.setLiftSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
