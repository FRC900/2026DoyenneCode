package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Spindexer;
import frc.robot.subsystems.Turrent;

public class ShootCommand extends Command{
    @SuppressWarnings("unused")
    private final Hood hoodSubsystem;
    @SuppressWarnings("unused")
    private final Spindexer spindexerSubsystem;
    @SuppressWarnings("unused")
    private final Turrent turrentSubsystem;

    public ShootCommand(Hood h_subsystem, Spindexer s_subsystem, Turrent t_subsystem){
        hoodSubsystem = h_subsystem;
        spindexerSubsystem = s_subsystem;
        turrentSubsystem = t_subsystem;

        addRequirements(h_subsystem, s_subsystem, t_subsystem);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
