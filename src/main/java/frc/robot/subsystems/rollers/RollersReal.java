package frc.robot.subsystems.rollers;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;


public class RollersReal implements RollersIO{

  TalonFX rollerMotor = new TalonFX(RollersConstants.rollerID);

  TalonFXConfiguration rollerConfig = RollersConstants.rollerConfig.clone();  

  public RollersReal() {

    rollerMotor.getConfigurator().apply(rollerConfig);


  }

  //Runs rollers with a given duty cycle,
  /*
  1 meaning 100% in positive direction,
  -1 meaning 100% in the opposite direction, 
  and 0 being no movement at all
  */
  @Override
  public void runRollers(double dutyCycle){
    rollerMotor.set(dutyCycle);
  }

  @Override
  public void stopRollers(){
    rollerMotor.stopMotor();
  }

}
