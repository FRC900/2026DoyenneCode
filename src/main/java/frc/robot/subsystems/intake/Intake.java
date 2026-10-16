// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;


public class Intake extends SubsystemBase {
 
  IntakeIO io;
  public CANcoder intakeEncoder = new CANcoder(62, "mech");
  
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

  public void wiggle(){
    io.wiggle();
  }


  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
