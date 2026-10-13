package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

public class FlywheelReal implements FlywheelIO{
    public final TalonFX flywheelLeader = new TalonFX(FlywheelConstants.flywheelL_ID, "mech");
    public final TalonFX flywheelFollower = new TalonFX(FlywheelConstants.flywheelF_ID, "mech");    

    public final TalonFXConfiguration config = FlywheelConstants.flywheelConfig.clone();

    public final VelocityVoltage flywheelSpeedControl = new VelocityVoltage(0);

    FlywheelReal(){
        config.Slot0.kP = FlywheelConstants.flywheel_kP;
        config.Slot0.kI = FlywheelConstants.flywheel_kI;
        config.Slot0.kD = FlywheelConstants.flywheel_kD;

        config.Slot0.kV = FlywheelConstants.flywheel_kV;


        flywheelLeader.getConfigurator().apply(config);
        flywheelFollower.getConfigurator().apply(config);

    flywheelFollower.setControl(new Follower(FlywheelConstants.flywheelL_ID, MotorAlignmentValue.Opposed));
    } 
    //Enter RPM you want flywheel to do
    @Override
    public void setFlywheelRPM(double rpm){
        setFlywheelRPS(rpm / 60.0);
    }

    @Override
    public void setFlywheelRPS(double rps){
        flywheelSpeedControl.withSlot(0).withAcceleration(0).withVelocity(rps);
        flywheelLeader.setControl(flywheelSpeedControl);
    }

    @Override
    public double getError() {
        return flywheelSpeedControl.Velocity - getFlywheelSpeed();
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
