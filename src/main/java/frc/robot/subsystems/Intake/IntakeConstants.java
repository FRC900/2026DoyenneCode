package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.Constants;

public class IntakeConstants {
    public static final int rollerID = 0;
    public static final int extendID = 1;

    public static final double extend_kP = 0;
    public static final double extend_kI = 0;
    public static final double extend_kD = 0;

    //This position is the amount of rotations it takes the motor to reach the point you want the intake to be at
    public static final double intakePos = 0;

    public static final TalonFXConfiguration rollerConfig = Constants.commonConfig.clone();
    public static final TalonFXConfiguration extendConfig = Constants.commonConfig.clone();
}
