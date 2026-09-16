package frc.robot.subsystems.Flywheel;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.Constants;

public class FlywheelConstants {
    public static final int flywheelL_ID = 5;
    public static final int flywheelF_ID = 6;

    public static final double flywheel_kP = 0;
    public static final double flywheel_kI = 0;
    public static final double flywheel_kD = 0;
    
    public static final TalonFXConfiguration flywheelConfig = Constants.commonConfig.clone();

}
