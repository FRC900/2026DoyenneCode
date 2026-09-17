package frc.robot.subsystems.Hood;

import org.littletonrobotics.junction.AutoLog;

public interface HoodIO {

    @AutoLog
    public static class HoodIOInputs {
        public double goalRotations = 0.0;
        public double position = 0.0;
        public double voltage = 0.0;
    }

    public void setHoodAngle(double angle);

    public void stopHood();

    public double getHoodAngle();
    
    public default void updateInputs(HoodIOInputs inputs) {}

}
