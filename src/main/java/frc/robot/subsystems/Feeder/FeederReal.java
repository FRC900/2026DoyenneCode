package frc.robot.subsystems.Feeder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class FeederReal implements FeederIO{

    public static final TalonFX feedMotor = new TalonFX(FeederConstants.feedMotorID);
    public static final TalonFXConfiguration feedMotorConfig = FeederConstants.feedMotorConfig.clone();

    public FeederReal(){
    feedMotor.getConfigurator().apply(feedMotorConfig);
    }
    
    @Override
    public void feedFeeder(double speed){
        //clamps speed between -1 & 1
        feedMotor.set(Math.max(Math.min(1, speed), -1));
    }
    
    @Override
    public void stopFeeder(){
        feedMotor.stopMotor();
    }

    @Override
    public void getFeederSpeed(){
        feedMotor.getVelocity().getValueAsDouble();
    }

}
