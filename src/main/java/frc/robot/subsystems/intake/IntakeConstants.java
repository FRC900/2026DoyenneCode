package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IntakeConstants {
    public static final int rollerID = 60;
    public static final int extendID = 61;

    public static final double extend_kP = 0;
    public static final double extend_kI = 0;
    public static final double extend_kD = 0;

    //TODO: needs a correct gear ratio
    public static final double intakeGearing = 4;

    //This position is the amount of rotations it takes the motor to reach the point you want the intake to be at
    public static final double intakePos = 0;

    public static final TalonFXConfiguration extendConfig =  new TalonFXConfiguration()
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
