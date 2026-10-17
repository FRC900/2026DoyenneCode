// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommands;
import frc.robot.commands.CommandBuilders;
import frc.robot.commands.ExtendIntakeCommand;
import frc.robot.commands.FeedFeederCommand;
import frc.robot.commands.GenericFlywheelCommand;
import frc.robot.commands.RollIntakeRollersCommand;
import frc.robot.commands.ShootHubFromCamera;
import frc.robot.commands.SpinSpindexerCommand;
import frc.robot.subsystems.feeder.Feeder;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.rollers.Rollers;
import frc.robot.subsystems.spindexer.Spindexer;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;
import frc.robot.subsystems.swerve.TunerConstants;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.vision.Vision;


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
    public final Spindexer spindexer = new Spindexer();
    public final Feeder feeder = new Feeder();
    public final Intake intake = new Intake();
    public final Flywheel flywheel = new Flywheel();
    public final Hood hood = new Hood();
    public final Rollers rollers = new Rollers();
    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
    public final Vision vision = new Vision();
    public final Turret turret = new Turret();

    private final SendableChooser<Command> autoChooser; 


    // Replace with CommandPS4Controller or CommandJoystick if needed
    @SuppressWarnings("unused")
    private final CommandXboxController m_driverController = new CommandXboxController(
            OperatorConstants.kDriverControllerPort);

    private final CommandXboxController m_operatorController = new CommandXboxController(
            OperatorConstants.kOperatorControllerPort);

    /**
     * The container for the robot. Contains subsystems, IO devices, and commands.
     */
    public RobotContainer() {
        CommandScheduler.getInstance().registerSubsystem(drivetrain);
        CommandScheduler.getInstance().registerSubsystem(spindexer);
        CommandScheduler.getInstance().registerSubsystem(feeder);
        CommandScheduler.getInstance().registerSubsystem(intake);
        CommandScheduler.getInstance().registerSubsystem(vision);
        CommandScheduler.getInstance().registerSubsystem(drivetrain);
        CommandScheduler.getInstance().registerSubsystem(turret);
        CommandScheduler.getInstance().registerSubsystem(flywheel);
        CommandScheduler.getInstance().registerSubsystem(intake);

        NamedCommands.registerCommand("shootClose", AutoCommands.shootAuto(
                CommandBuilders.createShootCommand(
                new GenericFlywheelCommand(flywheel, 2750), 
                new FeedFeederCommand(feeder), 
                new SpinSpindexerCommand(spindexer), 
                new Trigger(() -> flywheel.atSpeed(3))
            ).withTimeout(5), 5.0));

        NamedCommands.registerCommand("shootMid", AutoCommands.shootAuto(
                CommandBuilders.createShootCommand(
                new GenericFlywheelCommand(flywheel, 4000), 
                new FeedFeederCommand(feeder), 
                new SpinSpindexerCommand(spindexer), 
                new Trigger(() -> flywheel.atSpeed(3))
            ).withTimeout(5), 5.0));

        NamedCommands.registerCommand("intakeOut", AutoCommands.extendIntakeAuto(intake));

        autoChooser = AutoBuilder.buildAutoChooser("mid - shoot");  

        SmartDashboard.putData("Auto Mode", autoChooser);

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

        m_operatorController.leftBumper().onTrue(new ExtendIntakeCommand(intake));
        
        m_operatorController.leftTrigger(0.05).whileTrue(new RollIntakeRollersCommand(rollers));

        m_operatorController.rightTrigger(0.05).whileTrue(new SpinSpindexerCommand(spindexer));

        m_driverController.y().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        m_operatorController.a().whileTrue(CommandBuilders.createShootCommand(
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
        return autoChooser.getSelected();
    }
}
