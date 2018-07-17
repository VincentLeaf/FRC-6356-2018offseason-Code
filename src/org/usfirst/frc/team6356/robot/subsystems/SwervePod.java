package org.usfirst.frc.team6356.robot.subsystems;

import org.usfirst.frc.team6356.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class SwervePod extends Subsystem {
	
	private WPI_TalonSRX _angle;
	private VictorSP _speed;
	private boolean isFailed;
	private PIDController anglePID;
	
	public SwervePod(WPI_TalonSRX angle, VictorSP speed, boolean inverted, double kP, double kI, double kD) {
		_angle = angle;
		_speed = speed;
		
		
		
		_angle.setSensorPhase(true);
		_angle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		_angle.selectProfileSlot(0, 0);
		_angle.configAllowableClosedloopError(0, 0, 10);
		_angle.config_kP(0, kP, 10);
		_angle.config_kI(0, kI, 10);
		_angle.config_kD(0, kD, 10);
		
		_speed.setInverted(inverted);
		
		
		
		
	}
	
	public SwervePod(WPI_TalonSRX angle, VictorSP speed, boolean failed, boolean inverted) {
		_angle = angle;
		_speed = speed;
		
		anglePID = new PIDController(0.0265,0.00,0.0155,new PIDSource() {
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}
			@Override
			public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
			@Override
			public double pidGet() {return getAngle();}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double output) {_angle.set(output);}});
		
		anglePID.setInputRange(-180, 180);
		anglePID.setOutputRange(-1, 1);
		anglePID.setAbsoluteTolerance(1);
		
		
		_speed.setInverted(inverted);
	}
	

	
	public void setAngle(double angle) {
		if(isFailed) {
			anglePID.setSetpoint(angle);
		}
		else {
		double rawAngle = angle*4096/108;
		_angle.set(ControlMode.Position, rawAngle);
		}
	}
	
	public void setRawAngle(double angle) {
		_angle.set(ControlMode.Position, angle);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void resetAngle() {
		_angle.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	public double getAngle() {
		double angle = _angle.getSelectedSensorPosition(0)*107.5/4096;
		angle %= 360;
		return angle;
	}
	
	public double getRawAngle() {
		return _angle.getSelectedSensorPosition(0);
	}
	
	public void setAngleSpeed(double speed) {
		_angle.set(speed);
	}
	
	public void setSpeed(double speed) {
		_speed.set(speed);
	}

	public PIDController getPIDController() {
		return anglePID;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new JoystickDrive());
    }
}

