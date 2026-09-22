package frc.robot.subsystems.Turret;

public class TurretSim implements TurretIO{ 

    public TurretSim(){
        

    }

    //set the setpoint in terms of radians to the position you want with your turret
    @Override
    public void setTurretPos(double radians){
        radians /= (2*Math.PI) * TurretConstants.turretGearRatio;
        radians %= (2*Math.PI);
        System.out.println(radians);
    }
}
