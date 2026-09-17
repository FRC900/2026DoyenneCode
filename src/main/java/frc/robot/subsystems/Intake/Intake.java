// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;


public class Intake extends SubsystemBase {
 
  IntakeIO io;

  public Intake() {

    io = Robot.isReal() ? new IntakeReal() : new IntakeSim();

  }

  //Runs rollers with a given duty cycle,
  /*
  1 meaning 100% in positive direction,
  -1 meaning 100% in the opposite direction, 
  and 0 being no movement at all
  */
  public void runRollers(double dutyCycle){
    io.runRollers(dutyCycle);
  }

  //Extends the intake to the setpoint within the constants
  public void extendIntake(){
    io.extendIntake();
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
