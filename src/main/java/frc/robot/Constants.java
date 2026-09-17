// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
      public static final TalonFXConfiguration commonConfig = new TalonFXConfiguration()
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


  public static class OperatorConstants {

    public static final int kDriverControllerPort = 0;
    public static final int kOpperaatorControllerPort = 1;
    
  }

  public static class TurretConstants {

    public static final int turretMotorID = 4;
    public static final double turret_kP = 0;
    public static final double turret_kI = 0;
    public static final double turret_kD = 0;
    //22:1
    public static final double turretGearRatio = 22;

    public static final TalonFXConfiguration turretMotorConfig = commonConfig.clone();

  }

  public static class ShootCommandConstants {
    public static final double flywheelSpeed = 0;
    public static final double feederSpeed = 0;
    public static final double spindexerSpeed = 0;
    public static final double hoodAngle = 0;
  }
}
