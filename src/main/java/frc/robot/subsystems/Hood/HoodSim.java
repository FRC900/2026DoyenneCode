package frc.robot.subsystems.Hood;

public class HoodSim implements HoodIO{

    private double goal;

    public HoodSim(){

    }    
    
    @Override
    public void setHoodAngle(double angle){
        //set a calculation to convert angle into motor rotation
        goal = angle;
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

    @Override
    public void updateInputs(HoodIOInputs inputs) {
        inputs.goalRotations = this.goal;
        inputs.position = 2.0;
        inputs.voltage = 12;
    }
}
