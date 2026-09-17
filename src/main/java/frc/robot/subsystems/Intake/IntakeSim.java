package frc.robot.subsystems.Intake;

public class IntakeSim implements IntakeIO{

  public IntakeSim() {

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

  //Extends the intake to the setpoint within the constants
  @Override
  public void extendIntake(){
    System.out.println("");
  }    
}
