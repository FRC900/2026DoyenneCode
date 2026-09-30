package frc.robot.subsystems.spindexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.Constants;

public class SpindexerConstants {
    public static final int spinMotorID = 50;

    public static final TalonFXConfiguration spinMotorConfig = Constants.commonConfig.clone();
}
