package frc.robot.util;

public class DistanceMap {

    /*
    50 inches, 35 rps
    64 inches, 46 rps
    89 inches, 54 rps
    124 inches, 66 rps
    158 inches, 80 rps
    */
   
    public static double getRps(double distInches) {
        return 2.70532 * Math.pow(distInches, 0.667252);
    }
    
}
