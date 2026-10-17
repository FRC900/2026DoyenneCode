package frc.robot.subsystems.flywheel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.util.LoggedTunableNumber;

import frc.robot.Robot;

public class Flywheel extends SubsystemBase {

    LoggedTunableNumber loggedSpeed = new LoggedTunableNumber("testing/speed", 0);

    FlywheelIO io;

    public Flywheel() {

        io = Robot.isReal() ? new FlywheelReal() : new FlywheelSimulation();

    }

    public void setFlywheelRPM(double rpm) {
        io.setFlywheelRPM(rpm);
    }

    public void setFlywheelRPS(double rps) {
        io.setFlywheelRPM(rps);
    }

    public void stopFlywheel() {
        io.stopFlywheel();
    }

    public double getFlywheelSpeed() {
        return io.getFlywheelSpeed();
    }

    public boolean atSpeed(double tolearance) {
        return Math.abs(io.getError()) < tolearance;
    }

    @Override
    public void periodic() {
        // if (loggedSpeed.hasChanged(loggedSpeed.hashCode())) {
        //     io.setFlywheelRPM(loggedSpeed.getAsDouble());
        // }
    }

    @Override
    public void simulationPeriodic() {

    }
}
