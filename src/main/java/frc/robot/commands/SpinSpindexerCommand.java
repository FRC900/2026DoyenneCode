package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.spindexer.Spindexer;

public class SpinSpindexerCommand extends Command{
    private final Spindexer spindexerSubsystem;

    public SpinSpindexerCommand(Spindexer spin_subsystem){
        spindexerSubsystem = spin_subsystem;

        addRequirements(spindexerSubsystem);
    }

    @Override
    public void initialize(){
        spindexerSubsystem.spinSpindexer(0.6);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       spindexerSubsystem.stopSpindexer();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
