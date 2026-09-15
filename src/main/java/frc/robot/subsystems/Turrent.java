package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurrentConstants;

public class Turrent extends SubsystemBase{

    public static final TalonFX turrentMotor = new TalonFX(TurrentConstants.turrentMotorID);
    public static final TalonFXConfiguration turrentMotorConfig = TurrentConstants.turrentMotorConfig.clone();


    public static final PositionVoltage turrentPos = new PositionVoltage(0).withSlot(0);    

    public Turrent(){
        
        turrentMotorConfig.Slot0.kP = TurrentConstants.turrent_kP;
        turrentMotorConfig.Slot0.kI = TurrentConstants.turrent_kI;
        turrentMotorConfig.Slot0.kD = TurrentConstants.turrent_kD;

        turrentMotor.getConfigurator().apply(turrentMotorConfig);

    }

    //set the setpoint in terms of motor rotations to the position you want with your turrent
    public static void setTurrentPos(double turrentPosition){
        turrentPos.withSlot(0).withPosition(turrentPosition);
        turrentMotor.setControl(turrentPos);
    }

    @Override
    public void periodic(){

    }

    @Override
    public void simulationPeriodic(){
        
    }    
}
