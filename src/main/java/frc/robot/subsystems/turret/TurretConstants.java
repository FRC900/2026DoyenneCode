package frc.robot.subsystems.turret;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class TurretConstants {
    
    public static final int turretMotorID = 4;
    public static final double turret_kP = 0;
    public static final double turret_kI = 0;
    public static final double turret_kD = 0;
    //22:1
    public static final double turretGearRatio = 22;

    public static final TalonFXConfiguration turretMotorConfig =  new TalonFXConfiguration()
                                                                  .withCurrentLimits(
                                                                    new CurrentLimitsConfigs()
                                                                      .withStatorCurrentLimit(120)
                                                                      .withSupplyCurrentLimit(60)
                                                                      .withStatorCurrentLimitEnable(true)
                                                                      .withSupplyCurrentLimitEnable(true)
                                                                  )
                                                                  .withMotorOutput(
                                                                    new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
                                                                  );
}
