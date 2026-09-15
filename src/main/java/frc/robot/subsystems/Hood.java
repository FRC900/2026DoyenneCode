package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodedShooterConst;

public class Hood extends SubsystemBase{

    public final TalonFX hoodMotor = new TalonFX(HoodedShooterConst.hoodMotorID);
    public final TalonFXConfiguration hoodConfig = HoodedShooterConst.hoodMotorConfig.clone();

    public final PositionVoltage hoodAngle = new PositionVoltage(0);

    public Hood(){
        hoodConfig.Slot0.kP = HoodedShooterConst.hood_kP;
        hoodConfig.Slot0.kI = HoodedShooterConst.hood_kI;
        hoodConfig.Slot0.kD = HoodedShooterConst.hood_kD;


        hoodMotor.getConfigurator().apply(hoodConfig);
    }

    //Set the angle of the hood
    public void setHoodAngle(double angle){
        //set a calculation to convert angle into motor rotation
        hoodAngle.withSlot(0).withPosition(angle);
        hoodMotor.setControl(hoodAngle);
    }

    public void stopHood(){
        hoodMotor.stopMotor();
    }

    public double getHoodAngle(){
        return hoodMotor.getPosition().getValueAsDouble();
    }



  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
