package frc.robot.subsystems.Turret;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase{

    public static final TalonFX turretMotor = new TalonFX(TurretConstants.turretMotorID);
    public static final TalonFXConfiguration turretMotorConfig = TurretConstants.turretMotorConfig.clone();
    
    public static final CANcoder canCoder33To1 = new CANcoder(32, "mech");
    public static final CANcoder canCoder29To1 = new CANcoder(31, "mech");


    public static final PositionVoltage turretPos = new PositionVoltage(0).withSlot(0);    

    public Turret(){
        
        turretMotorConfig.Slot0.kP = TurretConstants.turret_kP;
        turretMotorConfig.Slot0.kI = TurretConstants.turret_kI;
        turretMotorConfig.Slot0.kD = TurretConstants.turret_kD;

        turretMotor.getConfigurator().apply(turretMotorConfig);

        turretMotor.setPosition(getTurretAngleOffset());

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

    //grabs absoulute angle offset, math from old code
    /**
     * Uses the CRT(Chinese Remainder Theorem) to use 2 CANCoder readings into a absolute turret pos
     *
     * <p>Each CANCoder reads rotations from 0-1 (it is fractional within one CANCoder revolution)
     *
     * <p>33:1 ratio - CANCoder wraps every 1/33rd of a turret rotation 29:1 ratio - CANCoder wraps
     * every 1/29rd of a turret rotation 29 and 33 are coprime, the combination of readings is
     * unique across 29*33 = 957 sectors.
     *
     * <p>CRT formula: x = (a1 * M1 * y1 + a2 * M2 * y2) mod M where a1, a2 are the remainders, M =
     * M1 * M2, and y1, y2 are the modular inverses.
     *
     * <p>Returns turret position in rotor rotations
     */
    private double getTurretAngleOffset() {
        int n1 = 33;
        int n2 = 29;

        // CANCoder, [0, 1), Convert to index of sector [0,n)
        double raw33 = canCoder33To1.getAbsolutePosition().getValueAsDouble();
        double raw29 = canCoder29To1.getAbsolutePosition().getValueAsDouble();

        // Wrap to [0,1) then convert to the index of the sector
        int a1 = (int) Math.round(((raw33 % 1.0) + 1.0) % 1.0 * n1) % n1;
        int a2 = (int) Math.round(((raw29 % 1.0) + 1.0) % 1.0 * n2) % n2;

        // CRT
        int M = n1 * n2; // 957
        int M1 = n2; // 29
        int M2 = n1; // 33
        int y1 = modInverse(M1, n1); // inverse of 29 mod 33
        int y2 = modInverse(M2, n2); // inverse of 33 mod 29

        int sector = ((a1 * M1 * y1 + a2 * M2 * y2) % M + M) % M;

        // Convert sector to turret rotations
        // Sector 0 = pos 0 (forward).
        // Each sector is 1/M of a turret rotation.
        double turretRotations = (double) sector / M;

        // Center around 0, so if turretRotations > 0.5, subtract 1 full rotation
        // so the range is [-0.5, 0.5) turret rotations instead of [0, 1)
        if (turretRotations > 0.5) {
            turretRotations -= 1.0;
        }

        // Return in rotor rotations (what the TalonFX encoder expects)
        return turretRotations / TurretConstants.turretGearRatio;
    }

    /** Computes the modular inverse of a mod m using the extended Euclidean algorithm. */
    private static int modInverse(int a, int m) {
        a = ((a % m) + m) % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        // Should never happen if a and m are coprime (29 and 33 are)
        throw new ArithmeticException("No modular inverse exists for " + a + " mod " + m);
    }
}
