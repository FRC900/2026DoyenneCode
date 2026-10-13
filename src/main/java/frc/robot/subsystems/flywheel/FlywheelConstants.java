package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FlywheelConstants {
    public static final int flywheelL_ID = 55;
    public static final int flywheelF_ID = 56;

    public static final double flywheel_kP = 0.5;
    public static final double flywheel_kI = 0;
    public static final double flywheel_kD = 0;
    
    public static final double flywheel_kS = 0;
    public static final double flywheel_kV = 0.15;
    public static final double flywheel_kA = 0;

    public static final TalonFXConfiguration flywheelConfig = new TalonFXConfiguration()
                                                                  .withCurrentLimits(
                                                                    new CurrentLimitsConfigs()
                                                                      .withStatorCurrentLimit(120)
                                                                      .withSupplyCurrentLimit(60)
                                                                      .withStatorCurrentLimitEnable(true)
                                                                      .withSupplyCurrentLimitEnable(true)
                                                                  )
                                                                  .withMotorOutput(
                                                                    new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Coast)
                                                                  );

}
