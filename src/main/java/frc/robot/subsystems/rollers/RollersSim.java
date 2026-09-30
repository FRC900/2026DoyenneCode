package frc.robot.subsystems.rollers;

public class RollersSim implements RollersIO{

  public RollersSim() {

  }

  //Runs rollers with a given duty cycle,
  /*
  1 meaning 100% in positive direction,
  -1 meaning 100% in the opposite direction, 
  and 0 being no movement at all
  */
  @Override
  public void runRollers(double dutyCycle){
    System.out.println("");
  }

  @Override
  public void stopRollers(){
    System.out.println("stop");
  }

}
