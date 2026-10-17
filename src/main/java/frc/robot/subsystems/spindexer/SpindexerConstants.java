package frc.robot.subsystems.spindexer;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class SpindexerConstants {
    public static final int spinMotorID = 50;

    public static final TalonFXConfiguration spinMotorConfig =  new TalonFXConfiguration()
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
