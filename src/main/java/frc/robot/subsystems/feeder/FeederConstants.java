package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public final class FeederConstants {
    
    public static final int feedMotorID = 40;
    public static final TalonFXConfiguration feedMotorConfig = new TalonFXConfiguration()
                                                                  .withCurrentLimits(
                                                                    new CurrentLimitsConfigs()
                                                                      .withStatorCurrentLimit(80)
                                                                      .withSupplyCurrentLimit(40)
                                                                      .withStatorCurrentLimitEnable(true)
                                                                      .withSupplyCurrentLimitEnable(true)
                                                                  )
                                                                  .withMotorOutput(
                                                                    new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
                                                                  );


}
