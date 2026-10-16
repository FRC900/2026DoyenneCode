// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.RollIntakeRollersCommand;
import frc.robot.commands.ShootHubFromCamera;
import frc.robot.commands.CommandBuilders;
import frc.robot.commands.GenericFlywheelCommand;
import frc.robot.commands.ExtendIntakeCommand;
import frc.robot.commands.SpinSpindexerCommand;
import frc.robot.commands.FeedFeederCommand;
import frc.robot.subsystems.swerve.*;
import frc.robot.subsystems.hood.*;
import frc.robot.subsystems.rollers.*;
import frc.robot.subsystems.flywheel.*;
import frc.robot.subsystems.intake.*;
import frc.robot.subsystems.feeder.*;
import frc.robot.subsystems.vision.*;
import frc.robot.subsystems.turret.*;
import frc.robot.subsystems.spindexer.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    public final CommandSwerveDrivetrain drivetrain;
    public final Rollers rollers;
    public final Hood hood;
    public final Flywheel flywheel;
    public final Intake intake;
    public final Feeder feeder;
    public final Spindexer spindexer;
    public final Vision vision;
    public final Turret turret;

    // Replace with CommandPS4Controller or CommandJoystick if needed
    @SuppressWarnings("unused")
    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);

    private final CommandXboxController m_operatorController = new CommandXboxController(
            OperatorConstants.kOperatorControllerPort);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the trigger bindings
        spindexer = new Spindexer();
        feeder = new Feeder();
        intake = new Intake();
        flywheel = new Flywheel();
        hood = new Hood();
        rollers = new Rollers();
        drivetrain = TunerConstants.createDrivetrain();
        vision = new Vision();
        turret = new Turret();

        configureBindings();
    }

    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(TunerConstants.kSpeedAt12Volts.magnitude() * 0.1)
            .withRotationalDeadband(TunerConstants.kMaxAngularRate * 0.1)
            .withDriveRequestType(SwerveModule.DriveRequestType.OpenLoopVoltage);

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
     * {@link
     * CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or
     * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        drivetrain.setDefaultCommand(
                drivetrain.applyRequest(() -> drive
                .withVelocityX(-m_driverController.getLeftY() * TunerConstants.kSpeedAt12Volts.magnitude() * 0.2)
                .withVelocityY(-m_driverController.getLeftX() * TunerConstants.kSpeedAt12Volts.magnitude() * 0.2)
                .withRotationalRate(-m_driverController.getRightX() * TunerConstants.kMaxAngularRate))
        );

        m_driverController.leftBumper().onTrue(new ExtendIntakeCommand(intake));
        
        m_driverController.leftTrigger().whileTrue(new RollIntakeRollersCommand(rollers));

        m_driverController.rightTrigger().whileTrue(new SpinSpindexerCommand(spindexer).alongWith(new FeedFeederCommand(feeder)));

        m_driverController.rightBumper().onTrue(new GenericFlywheelCommand(flywheel,2000));

        m_driverController.a().whileTrue(CommandBuilders.createShootCommand(
            new GenericFlywheelCommand(flywheel, 3000), 
            new FeedFeederCommand(feeder), 
            new SpinSpindexerCommand(spindexer), 
            new Trigger(() -> flywheel.atSpeed(3))
        ));
        
        m_operatorController.b().whileTrue(CommandBuilders.createShootCommand(
            new GenericFlywheelCommand(flywheel, 4500), 
            new FeedFeederCommand(feeder), 
            new SpinSpindexerCommand(spindexer), 
            new Trigger(() -> flywheel.atSpeed(3))
        ));

        m_operatorController.x().whileTrue(CommandBuilders.createShootCommand(
            new GenericFlywheelCommand(flywheel, 2750), 
            new FeedFeederCommand(feeder), 
            new SpinSpindexerCommand(spindexer), 
            new Trigger(() -> flywheel.atSpeed(3))
        ));

        m_operatorController.y().whileTrue(CommandBuilders.createShootCommand(
            new ShootHubFromCamera(flywheel, vision, turret), 
            new FeedFeederCommand(feeder), 
            new SpinSpindexerCommand(spindexer), 
            new Trigger(() -> flywheel.atSpeed(3))
        ));


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
