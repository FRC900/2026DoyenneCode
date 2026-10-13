package frc.robot.subsystems.flywheel;

public interface FlywheelIO {
    public void stopFlywheel();
    public void setFlywheelRPM(double rpm);
    public void setFlywheelRPS(double rps);
    public double getFlywheelSpeed();
    public double getError();
}
