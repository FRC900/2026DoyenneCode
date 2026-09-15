package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SpindexerConstants;

public class Spindexer extends SubsystemBase{

    public static final TalonFX spinMotor = new TalonFX(SpindexerConstants.spinMotorID);
    public static final TalonFX feedMotor = new TalonFX(SpindexerConstants.feedMotorID);

    public static final TalonFXConfiguration spinMotorConfig = SpindexerConstants.spinMotorConfig.clone();
    public static final TalonFXConfiguration feedMotorConfig = SpindexerConstants.feedMotorConfig.clone();


    public Spindexer(){
        spinMotor.getConfigurator().apply(spinMotorConfig);
        feedMotor.getConfigurator().apply(feedMotorConfig);
    }
    
    //Spins the spindexer at a given duty cycle
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    public void spinSpindexer(double speed){
        //clamps speed between -1 & 1
        spinMotor.set(Math.max(Math.min(1, speed), -1));
    }

    //Runs the wheels that feed the turrent using the same means as the spindexer
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    public void feedTurrent(double speed){
        //clamps speed between -1 & 1
        feedMotor.set(Math.max(Math.min(1, speed), -1));
    }

    @Override
    public void periodic(){

    }

    @Override
    public void simulationPeriodic(){

    }
    
}
