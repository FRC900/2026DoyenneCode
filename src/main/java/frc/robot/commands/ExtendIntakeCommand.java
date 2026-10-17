package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.intake.Intake;

public class ExtendIntakeCommand extends Command{
    private final Intake intakeSubsystem;
    private final Timer timer;

    public ExtendIntakeCommand(Intake in_subsystem){
        intakeSubsystem = in_subsystem;
        timer = new Timer();

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        timer.start();
        intakeSubsystem.extendIntake();
    }

    @Override
    public void execute(){
    }

    @Override
    public void end(boolean interrupted){
       intakeSubsystem.stopIntake();
       timer.stop();
    }

    @Override
    public boolean isFinished(){
        return timer.hasElapsed(0.2);
    }    
}
