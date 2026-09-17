package frc.robot.subsystems.Spindexer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Robot;

public class Spindexer extends SubsystemBase{

    SpindexerIO io;

    public Spindexer(){
        io = Robot.isReal() ? new SpindexerReal() : new SpindexerSim();
    }
    
    //Spins the spindexer at a given duty cycle
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    public void spinSpindexer(double speed){
        //clamps speed between -1 & 1
        io.spinSpindexer(speed);
    }

    @Override
    public void periodic(){

    }

    @Override
    public void simulationPeriodic(){

    }
    
}
