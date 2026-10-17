package frc.robot.subsystems.spindexer;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;

import edu.wpi.first.math.MathUtil;

public class SpindexerSim implements SpindexerIO{


    private TalonFX spindexerMotor;
    private TalonFXSimState spinSim;

    public SpindexerSim(){
        
        spindexerMotor = new TalonFX(SpindexerConstants.spinMotorID);
        spinSim = spindexerMotor.getSimState();

    }
    
    //Spins the spindexer at a given duty cycle
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    @Override
    public void spinSpindexer(double speed){
        speed = MathUtil.clamp(speed, -1, 1);
        spinSim.setSupplyVoltage(speed * 12);
    }

    @Override
    public void stopSpindexer(){
        spinSim.setSupplyVoltage(0);
    }
}
