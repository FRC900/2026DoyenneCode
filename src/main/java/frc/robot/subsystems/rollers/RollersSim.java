package frc.robot.subsystems.rollers;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.MathUtil;

public class RollersSim implements RollersIO{

  private TalonFX rollerMotor;
  private TalonFXSimState rollerSim;
  

  public RollersSim() {

    rollerMotor = new TalonFX(RollersConstants.rollerID);
    rollerSim = rollerMotor.getSimState();

  }

  @Override
  public void runRollers(double dutyCycle){
    dutyCycle = MathUtil.clamp(dutyCycle, -1, 1);
    rollerSim.setSupplyVoltage(dutyCycle * 12);
  }

  @Override
  public void stopRollers(){
    rollerSim.setSupplyVoltage(0);
  }

}
