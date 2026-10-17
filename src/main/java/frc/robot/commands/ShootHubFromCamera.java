package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.vision.Vision;
import frc.robot.subsystems.turret.Turret;
import frc.robot.util.DistanceMap;


public class ShootHubFromCamera extends Command {

    private final Flywheel flywheel;

    private final Vision vision;

    private final Turret turret;

    public ShootHubFromCamera(Flywheel fw, Vision vis, Turret tur){
        this.vision = vis;
        this.flywheel = fw;
        this.turret = tur;

        addRequirements(vision, flywheel, turret);
    }

    @Override
    public void initialize(){
        flywheel.setFlywheelRPS(DistanceMap.getRps(vision.getDistance())); 
        turret.changeTurretPos(vision.getDesiredHeading());
    }

    @Override
    public void execute(){
        flywheel.setFlywheelRPS(DistanceMap.getRps(vision.getDistance())); 
        // turret.changeTurretPos(vision.getDesiredHeading());
    }

    @Override
    public void end(boolean interrupted){
        flywheel.stopFlywheel();
        turret.setTurretPos(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}