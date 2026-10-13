package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.flywheel.Flywheel;

public class GenericFlywheelCommand extends Command{

    private final Flywheel flywheelSubsystem;

    private final double speed;

    public GenericFlywheelCommand(Flywheel fly_subsystem, double RPM){
        this.speed = RPM;
        this.flywheelSubsystem = fly_subsystem;

        addRequirements(flywheelSubsystem);
    }

    @Override
    public void initialize(){
        flywheelSubsystem.setFlywheelRPM(speed);
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