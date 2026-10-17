package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.rollers.Rollers;

public class WiggleIntakeCommand extends Command{

    private final Intake intakeSubsystem;

    public WiggleIntakeCommand(Intake i_subsystem){
        intakeSubsystem = i_subsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        intakeSubsystem.wiggle();
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
