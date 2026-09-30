package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class ExtendIntakeCommand extends Command{
    private final Intake intakeSubsystem;

    public ExtendIntakeCommand(Intake in_subsystem){
        intakeSubsystem = in_subsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        intakeSubsystem.extendIntake();
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       intakeSubsystem.stopIntake();
    }

    @Override
    public boolean isFinished(){
        return false;
    }    
}
