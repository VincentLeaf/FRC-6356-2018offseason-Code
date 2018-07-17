package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.Robot;
import org.usfirst.frc.team6356.robot.RobotMap;
import org.usfirst.frc.team6356.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {
	
	private SwervePod pod1;
	private SwervePod pod2;
	private SwervePod pod3;
	private SwervePod pod4;
	
	private ADXRS450_Gyro gyro;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ChassisSubsystem() {
		pod1 = new SwervePod(RobotMap.angle1, RobotMap.speed1, false, 0.192, 0.00001, 0.0);
		pod2 = new SwervePod(RobotMap.angle2, RobotMap.speed2, true, false);
		pod3 = new SwervePod(RobotMap.angle3, RobotMap.speed3, true, false);
		pod4 = new SwervePod(RobotMap.angle4, RobotMap.speed4, false, 0.42, 0.0, 0.0);
		gyro = new ADXRS450_Gyro();
		resetAngleEncoder();
	}
	
	public void resetAngleEncoder() {
		pod1.resetAngle();
		pod2.resetAngle();
		pod3.resetAngle();
		pod4.resetAngle();
	}
	
	public double getGyroRadian() {
		double angle = gyro.getAngle()%360;
		angle = Math.toRadians(angle);
		return angle;
		
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	
	public void joystickDrive() {
		double fwd;
		double str;
		double rcw;
		double temp;
		double fwd2;
		double str2;
		double wheelbase;
		double trackwidth;
		double r;
		double a;
		double b;
		double c;
		double d;
		
		double frs, fls, rls, rrs;
		double fra, fla, rla, rra;
		double max;
		
		fwd=-Robot.m_oi.driver.getRawAxis(1);
		str=Robot.m_oi.driver.getRawAxis(0);
		rcw=Robot.m_oi.driver.getRawAxis(4);
		
//		temp=(fwd*Math.cos(getGyroRadian()))+str*Math.sin(getGyroRadian());
//		str2 = (-fwd*Math.sin(getGyroRadian())) + str*Math.cos(getGyroRadian());
//    	fwd2 = temp;
//		field-centric
		
		str2 = str;
		fwd2 = fwd;
    	
    	wheelbase = 60;
    	trackwidth = 55;
    	r = Math.sqrt((wheelbase*wheelbase) + (trackwidth*trackwidth));
    	
    	a = str2 - rcw * (wheelbase/r);
    	b = str2 + rcw * (wheelbase/r);
    	c = fwd2 - rcw * (trackwidth/r);
    	d = fwd2 + rcw * (trackwidth/r);
    	
    	frs = Math.sqrt(b*b + c*c);
    	fls = Math.sqrt(b*b + d*d);
    	rls = Math.sqrt(a*a + d*d);
    	rrs = Math.sqrt(a*a + c*c);
    	
    	fra = Math.atan2(b,c) * 180/Math.PI;
    	fla = Math.atan2(b,d) * 180/Math.PI;
    	rla = Math.atan2(a,d) * 180/Math.PI;
    	rra = Math.atan2(a,c) * 180/Math.PI;
    	
    	pod1.setAngle(fla);
    	pod2.setAngle(-rla);
    	pod3.setAngle(-fra);
    	pod4.setAngle(rra);
    	
    	SmartDashboard.putNumber("expFrontLeft", fla);
    	SmartDashboard.putNumber("expRearLeft", rla);
    	SmartDashboard.putNumber("expFrontRight", fra);
    	SmartDashboard.putNumber("expRearRight", rra);
    	
    	max = frs;
    	if(fls>max){
    		max = fls;
    	}
    	if(rls>max){
    		max = rls;
    	}
    	if(rrs>max){
    		max = rrs;
    	}
    	if(max>1){
    		frs/=max;
    		fls/=max;
    		rrs/=max;
    		rls/=max;
    	}
    	
    	SmartDashboard.putBoolean("onTarget", fla-4<pod1.getAngle()&&pod1.getAngle()<fla+4);
    	
    	if ((fla-35<pod1.getAngle()&&pod1.getAngle()<fla+35) ||
    		(fra-35<pod2.getAngle()&&pod2.getAngle()<fra+35) ||
    		(rla-35<pod3.getAngle()&&pod3.getAngle()<rla+35) ||
    		(rra-35<pod4.getAngle()&&pod4.getAngle()<rra+35)) {
    		pod1.setSpeed(fls);
    		pod2.setSpeed(rls);
    		pod3.setSpeed(frs);
    		pod4.setSpeed(rrs);
    	}
    	
    	
    	else {
    		pod1.setSpeed(0);
    		pod2.setSpeed(0);
    		pod3.setSpeed(0);
    		pod4.setSpeed(0);
    	}
	}
	
	public void setAngle(double angle) {
		pod1.setAngle(angle);
//		pod2.setAngle(angle);
//		pod3.setAngle(angle);
//		pod4.setAngle(angle);
	}
	
	public void setRawAngle(double angle) {
		pod2.setRawAngle(angle);
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public PIDController getpod3PIDController() {
		return pod3.getPIDController();
	}
	
	public PIDController getpod2PIDController() {
		return pod2.getPIDController();
	}

	public double getPod2Angle() {
		return pod2.getAngle();
	}
	
	public void stopALL() {
		pod1.setAngleSpeed(0);
		pod2.setAngleSpeed(0);
		pod3.setAngleSpeed(0);
		pod4.setAngleSpeed(0);
		
		pod1.setSpeed(0);
		pod2.setSpeed(0);
		pod3.setSpeed(0);
		pod4.setSpeed(0);
	}

	public void log() {
		SmartDashboard.putNumber("FrontLeftAngle", pod1.getAngle());
		SmartDashboard.putNumber("RearLeftAngle", pod2.getAngle());
		SmartDashboard.putNumber("FrontRightANgle", pod3.getAngle());
		SmartDashboard.putNumber("RearRightAngle", pod4.getAngle());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		SmartDashboard.putNumber("RawAngle", pod2.getRawAngle());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }

}

