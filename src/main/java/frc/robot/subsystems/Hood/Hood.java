package frc.robot.subsystems.Hood;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Hood extends SubsystemBase{

    HoodIO io;
    private HoodIOInputsAutoLogged inputs = new HoodIOInputsAutoLogged();

    public Hood(){

        io = Robot.isReal() ? new HoodReal() : new HoodSim();

    }

    //Set the angle of the hood
    public void setHoodAngle(double angle){
        //set a calculation to convert angle into motor rotation
        io.setHoodAngle(angle);
    }

    public void stopHood(){
        io.stopHood();
    }

    public double getHoodAngle(){
        return io.getHoodAngle();
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Hood", inputs);
    }

    @Override
    public void simulationPeriodic() {
        
    }
}
