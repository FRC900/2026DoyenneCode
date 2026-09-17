package frc.robot.subsystems.Flywheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Robot;

public class Flywheel extends SubsystemBase{

  FlywheelIO io;

    public Flywheel(){

      io = Robot.isReal() ? new FlywheelReal() : new FlywheelSim();

    }


  public void setFlywheelSpeed(double speed){
    io.setFlywheelSpeed(speed);
  }


  public void stopFlywheel(){
    io.stopFlywheel();
  }

  public double getFlywheelSpeed(){
    return io.getFlywheelSpeed();
  }

@Override
  public void periodic() {
    
  }

@Override
  public void simulationPeriodic() {
    
  }
}
