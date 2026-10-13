package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Timer;

public class IntakeReal implements IntakeIO{

    TalonFX extendMotor = new TalonFX(IntakeConstants.extendID, "mech");
    TalonFXConfiguration extendConfig = IntakeConstants.extendConfig.clone();

    private boolean extended = false;

    Timer t = new Timer();

    PositionVoltage intakePosControl = new PositionVoltage(IntakeConstants.intakePos).withSlot(0);

    public IntakeReal() {
        t.reset();
        t.start();

        extendConfig.Slot0.kP = IntakeConstants.extend_kP;
        extendConfig.Slot0.kI = IntakeConstants.extend_kI;
        extendConfig.Slot0.kD = IntakeConstants.extend_kD;

        extendMotor.getConfigurator().apply(extendConfig);

    }

    //Extends the intake to the setpoint within the constants
    @Override
    public void extendIntake(){
        extended = true;
        extendMotor.setControl(intakePosControl.withSlot(0));
    }

    @Override
    public void stopIntake(){
        extendMotor.stopMotor();
    }

    @Override
    public void periodic() {
        if (extended) {
            extendMotor.setControl(intakePosControl.withSlot(0).withPosition(IntakeConstants.intakePos + (Math.sin(t.get() * .1))));
        }
    }
    
}
