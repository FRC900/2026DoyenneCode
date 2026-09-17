package frc.robot.subsystems.Flywheel;

public class FlywheelSim implements FlywheelIO{
    
    @Override
    public void stopFlywheel(){
        System.out.println("");
    }

    //Returns the velocity of the flywheel
    @Override
    public double getFlywheelSpeed(){
        return 2.0;
    }

    @Override
    public void setFlywheelSpeed(double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFlywheelSpeed'");
    }

}
