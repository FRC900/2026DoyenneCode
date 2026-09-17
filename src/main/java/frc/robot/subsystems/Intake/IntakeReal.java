package frc.robot.subsystems.Intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeReal implements IntakeIO{

  TalonFX rollerMotor = new TalonFX(IntakeConstants.rollerID);
  TalonFX extendMotor = new TalonFX(IntakeConstants.extendID);

  TalonFXConfiguration rollerConfig = IntakeConstants.rollerConfig.clone();  
  TalonFXConfiguration extendConfig = IntakeConstants.extendConfig.clone();

  PositionVoltage intakePos = new PositionVoltage(IntakeConstants.intakePos).withSlot(0);

  public IntakeReal() {

    extendConfig.Slot0.kP = IntakeConstants.extend_kP;
    extendConfig.Slot0.kI = IntakeConstants.extend_kI;
    extendConfig.Slot0.kD = IntakeConstants.extend_kD;

    rollerMotor.getConfigurator().apply(rollerConfig);
    extendMotor.getConfigurator().apply(extendConfig);

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

  //Extends the intake to the setpoint within the constants
  @Override
  public void extendIntake(){
    extendMotor.setControl(intakePos.withSlot(0));
  }
}
