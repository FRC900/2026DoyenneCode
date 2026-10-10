package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class IntakeSim implements IntakeIO{

  private SingleJointedArmSim intakeSim;

  private double intakeInputVoltage = 0;

  public IntakeSim() {

    intakeSim = new SingleJointedArmSim(
      DCMotor.getKrakenX60(1), 
      IntakeConstants.intakeGearing,
      0.00022, 
      19, 
      0, 
      Units.degreesToRadians(90), 
      false, 
      0
      );

  }

  @Override
  public void extendIntake(){
    // convert linear height to angle assuming arc of arm length
    double targetAngleRad =
            MathUtil.clamp(
            90 / intakeSim.getAngleRads(),
            0.0,
            1.0
        ) * (Units.degreesToRadians(90) - 0);

    double error = targetAngleRad - intakeSim.getAngleRads();
    double kP = 2.0; // simple sim-only proportional gain
    intakeInputVoltage = MathUtil.clamp(kP * error, -12.0, 12.0);
  }    

  @Override
   public void stopIntake(){
      intakeSim.setInputVoltage(0);
  }

  public void periodic(){
    intakeSim.setInputVoltage(intakeInputVoltage);
    intakeSim.update(0.01);
  }
}
