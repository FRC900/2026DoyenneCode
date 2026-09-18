package frc.robot.subsystems.Vision;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@SuppressWarnings("unused")
public class Vision extends SubsystemBase{

    private AprilTagFieldLayout fieldLayout;

    private final PhotonCamera turretCam = new PhotonCamera("turretCam");
    private final PhotonCamera topRightCam = new PhotonCamera("topRightCam");
    private final PhotonCamera rightCam = new PhotonCamera("rightCam");
    private final PhotonCamera leftCam = new PhotonCamera("leftCam");

    private final Transform3d robotToTopRightCam = new Transform3d(
        new Translation3d(Units.inchesToMeters(-11.5), Units.inchesToMeters(-2.9), Units.inchesToMeters(-11)),
        new Rotation3d(Units.degreesToRadians(0), Units.degreesToRadians(105), Units.degreesToRadians(0))
    );
    private final Transform3d robotToRightCam = new Transform3d(
        new Translation3d(Units.inchesToMeters(0), Units.inchesToMeters(0), Units.inchesToMeters(0)),
        new Rotation3d(Units.degreesToRadians(0), Units.degreesToRadians(0), Units.degreesToRadians(0))
    );
    private final Transform3d robotToLeftCam = new Transform3d(
        new Translation3d(Units.inchesToMeters(0), Units.inchesToMeters(0), Units.inchesToMeters(0)),
        new Rotation3d(Units.degreesToRadians(0), Units.degreesToRadians(0), Units.degreesToRadians(0))
    );
    
    private PhotonPoseEstimator topRightCamEstimator;
    private PhotonPoseEstimator rightCamEstimator;
    private PhotonPoseEstimator leftCamEstimator;

    public Vision(){


    }

    @Override
    public void periodic(){

    }

    @Override
    public void simulationPeriodic(){

    }
}
