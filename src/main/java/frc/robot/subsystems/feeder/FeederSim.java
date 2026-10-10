package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.MathUtil;

public class FeederSim implements FeederIO{
    
    TalonFX feedMotor;
    TalonFXSimState feedMotorSim;

    public FeederSim(){
        feedMotor = new TalonFX(FeederConstants.feedMotorID);
        feedMotorSim = feedMotor.getSimState();
    }

    @Override
    public void feedFeeder(double speed){
        speed = MathUtil.clamp(speed, -1.0, 1.0);
        feedMotorSim.setSupplyVoltage(speed * 12);
    }

    @Override
    public void stopFeeder(){
        feedMotorSim.setSupplyVoltage(0);
    }

    @Override
    public double getFeederSpeed(){
        return feedMotorSim.getMotorVoltage();
    }

}
