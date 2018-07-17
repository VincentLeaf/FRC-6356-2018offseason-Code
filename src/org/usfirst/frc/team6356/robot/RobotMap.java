/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6356.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static WPI_TalonSRX angle1;
	public static WPI_TalonSRX angle2;
	public static WPI_TalonSRX angle3;
	public static WPI_TalonSRX angle4;
	public static VictorSP speed1;
	public static VictorSP speed2;
	public static VictorSP speed3;
	public static VictorSP speed4;
	
	public static class ChassisPort {
		public static int angle1 = 10;
		public static int angle2 = 11;
		public static int angle3 = 12;
		public static int angle4 = 13;
		public static int speed1 = 0;
		public static int speed2 = 1;
		public static int speed3 = 2;
		public static int speed4 = 3;
	}
	
	public static class PIDConstants {
		public static double angleP = 0.42;
		public static double angleI = 0;
		public static double angleD = 0.000;
	}
	
	public static void init() {
		angle1 = new WPI_TalonSRX(ChassisPort.angle1);
		angle2 = new WPI_TalonSRX(ChassisPort.angle2);
		angle3 = new WPI_TalonSRX(ChassisPort.angle3);
		angle4 = new WPI_TalonSRX(ChassisPort.angle4);
		speed1 = new VictorSP(ChassisPort.speed1);
		speed2 = new VictorSP(ChassisPort.speed2);
		speed3 = new VictorSP(ChassisPort.speed3);
		speed4 = new VictorSP(ChassisPort.speed4);
		System.out.println("Chassis MC init succesfully!");
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
