package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodedShooterConst;

public class Flywheel extends SubsystemBase{
    public final TalonFX flywheelL = new TalonFX(HoodedShooterConst.flywheelL_ID);
    public final TalonFX flywheelF = new TalonFX(HoodedShooterConst.flywheelF_ID);    

    public final TalonFXConfiguration flywheelConfig = HoodedShooterConst.flywheelConfig.clone();

    public final VelocityVoltage flywheelSpeed = new VelocityVoltage(0);


    public Flywheel(){

    flywheelConfig.Slot0.kP = HoodedShooterConst.flywheel_kP;
    flywheelConfig.Slot0.kI = HoodedShooterConst.flywheel_kI;
    flywheelConfig.Slot0.kD = HoodedShooterConst.flywheel_kD;

    flywheelL.getConfigurator().apply(flywheelConfig);
    flywheelF.getConfigurator().apply(flywheelConfig);

    flywheelF.setControl(new Follower(5, MotorAlignmentValue.Opposed));

    }

    //Enter RPM you want flywheel to do
    public void setFlywheelSpeed(double speed){
        speed /= 60;
        flywheelSpeed.withSlot(0).withAcceleration(speed);
        flywheelL.setControl(flywheelSpeed);
    }

    //Stop! STOP!
    public void stopFlywheel(){
        flywheelL.stopMotor();
    }

    //Returns the velocity of the flywheel
    public double getFlywheelSpeed(){
        return flywheelL.getVelocity().getValueAsDouble();
    }


@Override
  public void periodic() {
    
  }

@Override
  public void simulationPeriodic() {
    
  }
}
