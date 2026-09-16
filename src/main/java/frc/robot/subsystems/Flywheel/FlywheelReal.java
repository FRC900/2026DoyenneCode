package frc.robot.subsystems.Flywheel;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;




public class FlywheelReal implements FlywheelIO{
    public final TalonFX flywheelLeader = new TalonFX(FlywheelConstants.flywheelL_ID);
    public final TalonFX flywheelFollower = new TalonFX(FlywheelConstants.flywheelF_ID);    

    public final TalonFXConfiguration config = FlywheelConstants.flywheelConfig.clone();

    public final VelocityVoltage flywheelSpeed = new VelocityVoltage(0);

    FlywheelReal(){
        config.Slot0.kP = FlywheelConstants.flywheel_kP;
        config.Slot0.kI = FlywheelConstants.flywheel_kI;
        config.Slot0.kD = FlywheelConstants.flywheel_kD;

        flywheelLeader.getConfigurator().apply(config);
        flywheelFollower.getConfigurator().apply(config);

    flywheelFollower.setControl(new Follower(5, MotorAlignmentValue.Opposed));
    } 
    //Enter RPM you want flywheel to do
    @Override
    public void setFlywheelSpeed(double speed){
        speed /= 60;
        flywheelSpeed.withSlot(0).withAcceleration(speed);
        flywheelLeader.setControl(flywheelSpeed);
    }

    //Stop! STOP!
    @Override
    public void stopFlywheel(){
        flywheelLeader.stopMotor();
    }

    //Returns the velocity of the flywheel
    @Override
    public double getFlywheelSpeed(){
        return flywheelLeader.getVelocity().getValueAsDouble();
    }
}
