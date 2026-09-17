package frc.robot.subsystems.Spindexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class SpindexerReal implements SpindexerIO{
    public static final TalonFX spinMotor = new TalonFX(SpindexerConstants.spinMotorID);

    public static final TalonFXConfiguration spinMotorConfig = SpindexerConstants.spinMotorConfig.clone();

    public SpindexerReal(){
        spinMotor.getConfigurator().apply(spinMotorConfig);
    }
    
    //Spins the spindexer at a given duty cycle
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    @Override
    public void spinSpindexer(double speed){
        //clamps speed between -1 & 1
        spinMotor.set(Math.max(Math.min(1, speed), -1));
    }
    
    @Override
    public void stopSpindexer(){
        spinMotor.stopMotor();
    }
}
