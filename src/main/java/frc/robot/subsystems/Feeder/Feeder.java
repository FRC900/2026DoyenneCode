package frc.robot.subsystems.Feeder;

import frc.robot.Robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase{
    FeederIO io;

    public Feeder(){
        io = Robot.isReal() ? new FeederReal() : new FeederSim();
    }

    public void feedFeeder(double speed){
        //clamps speed between -1 & 1
        io.feedFeeder(speed);
    }
    
    public void stopFeeder(){
        io.stopFeeder();
    }

    public void getFeederSpeed(){
        io.getFeederSpeed();
    }

}
