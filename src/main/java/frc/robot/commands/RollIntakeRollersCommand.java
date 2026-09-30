package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.rollers.Rollers;

public class RollIntakeRollersCommand extends Command {

    private final Rollers rollersSubsystem;

    public RollIntakeRollersCommand(Rollers roll_subsystem){
        rollersSubsystem = roll_subsystem;

        addRequirements(rollersSubsystem);
    }

    @Override
    public void initialize(){
        rollersSubsystem.runRollers(0.5);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       rollersSubsystem.stopRollers();
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}