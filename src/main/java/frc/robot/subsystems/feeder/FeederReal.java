package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;

public class FeederReal implements FeederIO{

    public static final TalonFX feedMotor = new TalonFX(FeederConstants.feedMotorID, "mech");
    public static final TalonFXConfiguration feedMotorConfig = FeederConstants.feedMotorConfig.clone();

    public FeederReal(){
    feedMotor.getConfigurator().apply(feedMotorConfig);
    }
    
    @Override
    public void feedFeeder(double speed){
        //clamps speed between -1 & 1
        speed = MathUtil.clamp(speed, -1.0, 1.0);
        feedMotor.set(speed);
    }
    
    @Override
    public void stopFeeder(){
        feedMotor.stopMotor();
    }

    @Override
    public double getFeederSpeed(){
        return feedMotor.getVelocity().getValueAsDouble();
    }

}
