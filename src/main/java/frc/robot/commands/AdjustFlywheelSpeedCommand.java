package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.flywheel.Flywheel;

public class AdjustFlywheelSpeedCommand extends Command{

    private final Flywheel flywheelSubsystem;

    public AdjustFlywheelSpeedCommand(Flywheel fly_subsystem){
        flywheelSubsystem = fly_subsystem;

        addRequirements(flywheelSubsystem);
    }

    @Override
    public void initialize(){
        flywheelSubsystem.setFlywheelSpeed(500);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       flywheelSubsystem.stopFlywheel();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}