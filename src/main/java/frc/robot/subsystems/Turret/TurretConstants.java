package frc.robot.subsystems.Turret;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import frc.robot.Constants;

public class TurretConstants {
    
    public static final int turretMotorID = 4;
    public static final double turret_kP = 0;
    public static final double turret_kI = 0;
    public static final double turret_kD = 0;
    //22:1
    public static final double turretGearRatio = 22;

    public static final TalonFXConfiguration turretMotorConfig = Constants.commonConfig.clone();
}
