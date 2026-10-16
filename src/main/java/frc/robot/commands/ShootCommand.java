package frc.robot.commands;
import frc.robot.subsystems.spindexer.Spindexer;
import frc.robot.subsystems.feeder.Feeder;
import frc.robot.subsystems.flywheel.Flywheel;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootCommand extends Command{
    private final Spindexer spindexerSubsystem;
    private final Feeder feederSubsystem;
    private final Flywheel flywheelSubsystem;

    public ShootCommand(Spindexer spindexer, Feeder feeder, Flywheel flywheel){
        spindexerSubsystem = spindexer;
        feederSubsystem = feeder;
        flywheelSubsystem = flywheel;

        addRequirements(spindexerSubsystem, feederSubsystem, flywheelSubsystem);
    }

    @Override
    public void initialize(){
        spindexerSubsystem.spinSpindexer(-0.5);
        feederSubsystem.feedFeeder(-0.5);
    }

    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
       spindexerSubsystem.stopSpindexer();
       feederSubsystem.stopFeeder();
       flywheelSubsystem.stopFlywheel();

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
