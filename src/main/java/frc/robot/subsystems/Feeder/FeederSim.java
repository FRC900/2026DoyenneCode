package frc.robot.subsystems.Feeder;

public class FeederSim implements FeederIO{
    
    public FeederSim(){

    }

    @Override
    public void feedFeeder(double speed){
        System.out.println("Feeding feeder");
    }

    @Override
    public void stopFeeder(){
        System.out.println("Stopping feeder");
    }

    @Override
    public void getFeederSpeed(){
        System.out.println("Getting feeder speed");
    }

}
