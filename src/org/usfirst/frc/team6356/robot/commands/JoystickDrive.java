package org.usfirst.frc.team6356.robot.commands;

import org.usfirst.frc.team6356.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDrive extends Command {

    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_chassis.resetAngleEncoder();
    	Robot.m_chassis.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.m_chassis.setRawAngle(4396);
    	Robot.m_chassis.joystickDrive();
//    	Robot.m_chassis.setAngle(180);
    	Robot.m_chassis.log();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
