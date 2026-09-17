package frc.robot.subsystems.Intake;

public interface IntakeIO {

  //Runs rollers with a given duty cycle,
  /*
  1 meaning 100% in positive direction,
  -1 meaning 100% in the opposite direction, 
  and 0 being no movement at all
  */
  public void runRollers(double dutyCycle);

  //Extends the intake to the setpoint within the constants
  public void extendIntake();
}
