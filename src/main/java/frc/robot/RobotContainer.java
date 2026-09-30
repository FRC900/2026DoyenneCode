// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.RollIntakeRollersCommand;
import frc.robot.commands.AdjustHoodCommand;
import frc.robot.commands.AdjustFlywheelSpeedCommand;
import frc.robot.commands.ExtendIntakeCommand;
import frc.robot.commands.SpinSpindexerCommand;
import frc.robot.commands.FeedFeederCommand;
import frc.robot.subsystems.swerve.*;
import frc.robot.subsystems.hood.*;
import frc.robot.subsystems.rollers.*;
import frc.robot.subsystems.flywheel.*;
import frc.robot.subsystems.intake.*;
import frc.robot.subsystems.feeder.*;
import frc.robot.subsystems.spindexer.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public CommandSwerveDrivetrain drivetrain;
  public Rollers rollers = new Rollers();
  public Hood hood = new Hood();
  public Flywheel flywheel = new Flywheel();
  public Intake intake = new Intake();
  public Feeder feeder = new Feeder();
  public Spindexer spindexer = new Spindexer();
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  @SuppressWarnings("unused")
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

private final CommandXboxController m_operatorController =
        new CommandXboxController(OperatorConstants.kOperatorControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    drivetrain = TunerConstants.createDrivetrain();
    configureBindings();
  }

   private final SwerveRequest.FieldCentric drive =
            new SwerveRequest.FieldCentric()
                    .withDeadband(TunerConstants.kSpeedAt12Volts.magnitude() * 0.1)
                    .withRotationalDeadband(TunerConstants.kMaxAngularRate * 0.1)
                    .withDriveRequestType(SwerveModule.DriveRequestType.OpenLoopVoltage);
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    drivetrain.setDefaultCommand(
                drivetrain.applyRequest(
                        () ->
                                drive.withVelocityX(
                                                -m_driverController.getLeftY()
                                                        * TunerConstants.kSpeedAt12Volts
                                                              .magnitude() * 0.2)
                                        .withVelocityY(
                                                -m_driverController.getLeftX()
                                                        * TunerConstants.kSpeedAt12Volts
                                                                .magnitude() * 0.2)
                                        .withRotationalRate(
                                               -m_driverController.getRightX()
                                                        *TunerConstants.kMaxAngularRate)));

   m_operatorController.leftBumper().onTrue(new ExtendIntakeCommand(intake));
   m_operatorController.leftTrigger().whileTrue(new RollIntakeRollersCommand(rollers));
   
   m_operatorController.rightBumper().whileTrue(new SpinSpindexerCommand(spindexer));
   m_operatorController.rightTrigger().whileTrue(new ParallelCommandGroup(new AdjustFlywheelSpeedCommand(flywheel), new FeedFeederCommand(feeder)));

   m_operatorController.b().onTrue(new AdjustHoodCommand(hood));
   m_operatorController.a().onTrue(new AdjustFlywheelSpeedCommand(flywheel));
   
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
      return null;

  }
}
