package frc.robot.subsystems.rollers;

public interface RollersIO {

  //Runs rollers with a given duty cycle,
  /*
  1 meaning 100% in positive direction,
  -1 meaning 100% in the opposite direction, 
  and 0 being no movement at all
  */
  public void runRollers(double dutyCycle);

  public void stopRollers();
}
