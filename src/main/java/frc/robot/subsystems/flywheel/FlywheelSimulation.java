package frc.robot.subsystems.flywheel;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class FlywheelSimulation implements FlywheelIO{
    
    private double targetRPM = 0.0;

    private final FlywheelSim flySim = new FlywheelSim(
        LinearSystemId.createFlywheelSystem(
            DCMotor.getKrakenX60(2), 
            1.0, 
            0.0
        ), 
        DCMotor.getKrakenX60(2));

    private final SimpleMotorFeedforward feedForward = new SimpleMotorFeedforward(
        //Accurate SVA values needed
        FlywheelConstants.flywheel_kS, 
        FlywheelConstants.flywheel_kV, 
        FlywheelConstants.flywheel_kA
        );

    public FlywheelSimulation(){

    }


    @Override
    public void stopFlywheel(){
        targetRPM = 0.0;
        flySim.setInputVoltage(0.0);
    }

    //Returns the velocity of the flywheel
    @Override
    public double getFlywheelSpeed(){
        return flySim.getAngularVelocityRPM();
    }

    @Override
    public void setFlywheelSpeed(double speed) {
        targetRPM = speed;
    }

    public void periodic(){

        if (this.targetRPM <= 0.0) {
            flySim.setInputVoltage(0.0);
        } else {
            double targetRadPerSec =
                Units.rotationsPerMinuteToRadiansPerSecond(this.targetRPM);

            double ffVolts = feedForward.calculate(targetRadPerSec);

            flySim.setInputVoltage(
                Math.max(-12.0, Math.min(12.0, ffVolts))
            );
        }

    }

}
