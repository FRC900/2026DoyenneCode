// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.rollers;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;


public class Rollers extends SubsystemBase {
 
  RollersIO io;

  public Rollers() {

    io = Robot.isReal() ? new RollersReal() : new RollersSim();

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

  public void stopRollers(){
    io.stopRollers();
  }
  



  @Override
  public void periodic() {
    
  }

  @Override
  public void simulationPeriodic() {
    
  }
}
