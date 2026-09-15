// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  TalonFX rollerMotor = new TalonFX(IntakeConstants.rollerID);
  TalonFX extendMotor = new TalonFX(IntakeConstants.extendID);

  TalonFXConfiguration rollerConfig = IntakeConstants.rollerConfig.clone();  
  TalonFXConfiguration extendConfig = IntakeConstants.extendConfig.clone();

  PositionVoltage intakePos = new PositionVoltage(IntakeConstants.intakePos).withSlot(0);

  public Intake() {

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
  public void runRollers(double dutyCycle){
    rollerMotor.set(dutyCycle);
  }

  //Extends the intake to the setpoint within the constants
  public void extendIntake(){
    extendMotor.setControl(intakePos.withSlot(0));
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
