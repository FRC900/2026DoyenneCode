package frc.robot.subsystems.Hood;

public class HoodSim implements HoodIO{
    
    public HoodSim(){

    }    
    
    @Override
    public void setHoodAngle(double angle){
        //set a calculation to convert angle into motor rotation
        System.out.println("");
    }

    @Override
    public void stopHood(){
        System.out.println("");
    }

    @Override
    public double getHoodAngle(){
        return 2.0;
    }

}
