// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;


public class Intake extends SubsystemBase {
 
  IntakeIO io;

  public Intake() {

    io = Robot.isReal() ? new IntakeReal() : new IntakeSim();

  }

  //Extends the intake to the setpoint within the constants
  public void extendIntake(){
    io.extendIntake();
  }

  public void stopIntake(){
    io.stopIntake();
  }

  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
