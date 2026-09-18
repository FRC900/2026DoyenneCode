package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.Spindexer.Spindexer;
import frc.robot.subsystems.Turret.Turret;

public class ShootCommand extends Command{
    @SuppressWarnings("unused")
    private final Hood hoodSubsystem;
    @SuppressWarnings("unused")
    private final Spindexer spindexerSubsystem;
    @SuppressWarnings("unused")
    private final Turret turretSubsystem;

    public ShootCommand(Hood h_subsystem, Spindexer s_subsystem, Turret t_subsystem){
        hoodSubsystem = h_subsystem;
        spindexerSubsystem = s_subsystem;
        turretSubsystem = t_subsystem;

        addRequirements(h_subsystem, s_subsystem, t_subsystem);
    }

    @Override
    public void initialize(){
<<<<<<< Updated upstream

=======
        hoodSubsystem.setHoodAngle(ShootCommandConstants.hoodAngle);
        spindexerSubsystem.spinSpindexer(ShootCommandConstants.spindexerSpeed);
        feederSubsystem.feedFeeder(ShootCommandConstants.feederSpeed);
        flywheelSubsystem.setFlywheelSpeed(ShootCommandConstants.flywheelSpeed);
>>>>>>> Stashed changes
    }

    @Override
    public void execute(){
<<<<<<< Updated upstream
        
=======

        /*
         * TODO:
         * Something something, shoot on the move, Something something
         */

>>>>>>> Stashed changes
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
