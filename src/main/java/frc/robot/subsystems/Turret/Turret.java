package frc.robot.subsystems.Turret;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase{

    public static final TalonFX turretMotor = new TalonFX(TurretConstants.turretMotorID);
    public static final TalonFXConfiguration turretMotorConfig = TurretConstants.turretMotorConfig.clone();

    public static final PositionVoltage turretPos = new PositionVoltage(0).withSlot(0);    

    public Turret(){
        
        turretMotorConfig.Slot0.kP = TurretConstants.turret_kP;
        turretMotorConfig.Slot0.kI = TurretConstants.turret_kI;
        turretMotorConfig.Slot0.kD = TurretConstants.turret_kD;

        turretMotor.getConfigurator().apply(turretMotorConfig);

    }

    //set the setpoint in terms of radians to the position you want with your turret
    public static void setTurretPos(double radians){
        radians /= (2*Math.PI) * TurretConstants.turretGearRatio;
        radians %= (2*Math.PI);
        turretPos.withSlot(0).withPosition(radians);
        turretMotor.setControl(turretPos);
    }

    @Override
    public void periodic(){

    }

    @Override
    public void simulationPeriodic(){
        
    }    
}
