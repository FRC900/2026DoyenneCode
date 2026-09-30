package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeReal implements IntakeIO{

  TalonFX extendMotor = new TalonFX(IntakeConstants.extendID);
  TalonFXConfiguration extendConfig = IntakeConstants.extendConfig.clone();

  PositionVoltage intakePos = new PositionVoltage(IntakeConstants.intakePos).withSlot(0);

  public IntakeReal() {

    extendConfig.Slot0.kP = IntakeConstants.extend_kP;
    extendConfig.Slot0.kI = IntakeConstants.extend_kI;
    extendConfig.Slot0.kD = IntakeConstants.extend_kD;

    extendMotor.getConfigurator().apply(extendConfig);

  }


  //Extends the intake to the setpoint within the constants
  @Override
  public void extendIntake(){
    extendMotor.setControl(intakePos.withSlot(0));
  }

  @Override
  public void stopIntake(){
    extendMotor.stopMotor();
  }
}
