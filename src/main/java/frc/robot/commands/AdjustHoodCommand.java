package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.hood.Hood;

public class AdjustHoodCommand extends Command{
    private final Hood hoodSubsystem;

    public AdjustHoodCommand(Hood h_subsystem){
        hoodSubsystem = h_subsystem;

        addRequirements(h_subsystem);
    }

    @Override
    public void initialize(){
        hoodSubsystem.setHoodAngle(10);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       hoodSubsystem.stopHood();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
