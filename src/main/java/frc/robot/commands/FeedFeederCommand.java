package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.feeder.Feeder;


public class FeedFeederCommand extends Command{
    private final Feeder feederSubsystem;

    public FeedFeederCommand(Feeder feed_subsystem){
        feederSubsystem = feed_subsystem;

        addRequirements(feederSubsystem);
    }

    @Override
    public void initialize(){
        feederSubsystem.feedFeeder(0.6);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       feederSubsystem.stopFeeder();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
