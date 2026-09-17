package frc.robot.subsystems.Hood;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.util.LoggedTunableNumber;

public class HoodReal implements HoodIO{

    public final TalonFX hoodMotor = new TalonFX(HoodConstants.hoodMotorID);
    public final TalonFXConfiguration hoodConfig = HoodConstants.hoodMotorConfig.clone();

    private double goal;
    
    LoggedTunableNumber KP = new LoggedTunableNumber("Hood/KP", HoodConstants.hood_kP);
    LoggedTunableNumber KI = new LoggedTunableNumber("Hood/KI", HoodConstants.hood_kI);
    LoggedTunableNumber KD = new LoggedTunableNumber("Hood/KD", HoodConstants.hood_kD);

    public final PositionVoltage hoodAngle = new PositionVoltage(0);

    public HoodReal(){
        hoodConfig.Slot0.kP = HoodConstants.hood_kP;
        hoodConfig.Slot0.kI = HoodConstants.hood_kI;
        hoodConfig.Slot0.kD = HoodConstants.hood_kD;

        hoodMotor.getConfigurator().apply(hoodConfig);
    }

    private void reconfigure() { //shows how to use logged tunable numbers
        hoodConfig.Slot0.kP = KP.get();
        hoodConfig.Slot0.kI = KI.get();
        hoodConfig.Slot0.kD = KD.get();
    }

    @Override
    public void setHoodAngle(double angle){
        //set a calculation to convert angle into motor rotation
        hoodAngle.withSlot(0).withPosition(angle);
        hoodMotor.setControl(hoodAngle);
    }

    @Override
    public void stopHood(){
        hoodMotor.stopMotor();
    }

    @Override
    public double getHoodAngle(){
        return hoodMotor.getPosition().getValueAsDouble();
    }

    @Override
    public void updateInputs(HoodIOInputs inputs) {
        inputs.goalRotations = this.goal;
        inputs.position = hoodMotor.getPosition().getValueAsDouble();
        inputs.voltage = hoodMotor.getMotorVoltage().getValueAsDouble();
    }
}
