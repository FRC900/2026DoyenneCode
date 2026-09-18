package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShootCommandConstants;
import frc.robot.subsystems.Feeder.Feeder;
import frc.robot.subsystems.Flywheel.Flywheel;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.Spindexer.Spindexer;

public class ShootCommand extends Command{
    private final Hood hoodSubsystem;
    private final Spindexer spindexerSubsystem;
    private final Flywheel flywheelSubsystem;
    private final Feeder feederSubsystem;


    public ShootCommand(Hood h_subsystem, Spindexer s_subsystem, Flywheel fw_subsystem, Feeder f_subsystem){
        hoodSubsystem = h_subsystem;
        spindexerSubsystem = s_subsystem;
        flywheelSubsystem = fw_subsystem;
        feederSubsystem = f_subsystem;

        addRequirements(hoodSubsystem, spindexerSubsystem, feederSubsystem, flywheelSubsystem);
    }

    @Override
    public void initialize(){
        hoodSubsystem.setHoodAngle(ShootCommandConstants.hoodAngle);
        spindexerSubsystem.spinSpindexer(ShootCommandConstants.spindexerSpeed);
        feederSubsystem.feedFeeder(ShootCommandConstants.feederSpeed);
        flywheelSubsystem.setFlywheelSpeed(ShootCommandConstants.flywheelSpeed);
    }

    @Override
    public void execute(){
        /*
         * TODO:
         * Something something, vision to set hood angle, Something something
         */

        hoodSubsystem.setHoodAngle(ShootCommandConstants.hoodAngle);
        spindexerSubsystem.spinSpindexer(ShootCommandConstants.spindexerSpeed);
        feederSubsystem.feedFeeder(ShootCommandConstants.feederSpeed);
        flywheelSubsystem.setFlywheelSpeed(ShootCommandConstants.flywheelSpeed);

        /*
         * TODO:
         * Something something, shoot on the move, Something something
         */

    }

    @Override
    public void end(boolean interrupted){
        spindexerSubsystem.stopSpindexer();
        feederSubsystem.stopFeeder();
        flywheelSubsystem.stopFlywheel();

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
