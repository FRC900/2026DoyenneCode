package frc.robot.subsystems.Spindexer;

public class SpindexerSim implements SpindexerIO{

    public SpindexerSim(){
        
    }
    
    //Spins the spindexer at a given duty cycle
    /*
    1 meaning 100% in positive direction,
    -1 meaning 100% in the opposite direction, 
    and 0 being no movement at all
    */
    @Override
    public void spinSpindexer(double speed){
        //clamps speed between -1 & 1
        System.out.println(Math.max(Math.min(1, speed), -1));
    }
}
