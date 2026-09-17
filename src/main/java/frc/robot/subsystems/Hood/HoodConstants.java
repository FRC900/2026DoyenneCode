package frc.robot.subsystems.Hood;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.Constants;

public class HoodConstants {
    public static final int hoodMotorID = 7;

    public static final TalonFXConfiguration hoodMotorConfig = Constants.commonConfig.clone();

    public static final double hood_kP = 0;
    public static final double hood_kI = 0;
    public static final double hood_kD = 0;
}
