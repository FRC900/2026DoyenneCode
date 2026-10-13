package frc.robot.subsystems.vision;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.swerve.CommandSwerveDrivetrain;

@SuppressWarnings("unused")
public class Vision extends SubsystemBase{

    private AprilTagFieldLayout fieldLayout;

    private final PhotonCamera turretCam = new PhotonCamera("turretCam");

    //60 degrees pitch
    //19.5 inches up
    //13 inches between center and camera lens

    private final Transform3d turretCamTransform = new Transform3d(
        new Translation3d(Units.inchesToMeters(0), Units.inchesToMeters(0), Units.inchesToMeters(19.5)),
        new Rotation3d(Units.degreesToRadians(0), Units.degreesToRadians(60), Units.degreesToRadians(0))
    );

    private PhotonPoseEstimator turretCamEstimator;


    public Vision(){
        try {
            fieldLayout = AprilTagFieldLayout.loadFromResource("/frc/robot/2026AprilTags.json");
        } catch (IOException e) {
            fieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);
            // throw new RuntimeException("Failed to load AprilTag layout", e);
        }

        turretCamEstimator = new PhotonPoseEstimator(fieldLayout, turretCamTransform);

    }

    private Optional<EstimatedRobotPose> getEstimate(PhotonCamera cam, PhotonPoseEstimator estimator) {
        Optional<EstimatedRobotPose> result = Optional.empty();

        for (var frame : cam.getAllUnreadResults()) {
            result = estimator.estimateCoprocMultiTagPose(frame);
            if (result.isEmpty()) {
                result = estimator.estimateLowestAmbiguityPose(frame);
            }
        }

        return result;
    }


    public Optional<Pose3d> getPose() {

        List<EstimatedRobotPose> estimates = new ArrayList<>();
        getEstimate(turretCam, turretCamEstimator).ifPresent(estimates::add);

        EstimatedRobotPose best = null;
        double bestAmbiguity = Double.MAX_VALUE;

        for (EstimatedRobotPose pose : estimates) {

            double ambiguity = pose.targetsUsed.stream()
                    .mapToDouble(t -> t.getPoseAmbiguity())
                    .average()
                    .orElse(1.0);

            if (ambiguity < bestAmbiguity) {
                bestAmbiguity = ambiguity;
                best = pose;
            }
        }

        return best == null ? Optional.empty() : Optional.of(best.estimatedPose);
    }

    public Optional<EstimatedRobotPose> getBestEstimate() {

        List<EstimatedRobotPose> estimates = new ArrayList<>();
        getEstimate(turretCam, turretCamEstimator).ifPresent(estimates::add);

        EstimatedRobotPose best = null;
        double bestAmbiguity = Double.MAX_VALUE;

        for (EstimatedRobotPose pose : estimates) {

            double ambiguity = pose.targetsUsed.stream()
                    .mapToDouble(t -> t.getPoseAmbiguity())
                    .average()
                    .orElse(1.0);

            if (ambiguity < bestAmbiguity) {
                bestAmbiguity = ambiguity;
                best = pose;
            }
        }

        return Optional.ofNullable(best);
    }

    public double getDistance() {
        Pose3d pose = getPose().isPresent() ? getPose().get() : new Pose3d(); 
        if (pose.equals(new Pose3d())) {
            return -1;
        }
        Pose2d pose2 = pose.toPose2d();

        boolean isRed = pose2.getX() > 8.25;

        Pose2d targetPose = isRed ? VisionConstants.redHubPos : VisionConstants.hubPos;

        Translation2d cameraTranslation = pose2.getTranslation();
        Translation2d targetTranslation = targetPose.getTranslation();

        double distance = Units.metersToInches(cameraTranslation.getDistance(targetTranslation));

        return distance - 13;
    }

    public double getDesiredHeading() {
        Pose3d pose = getPose().isPresent() ? getPose().get() : new Pose3d(); 
        if (pose.equals(new Pose3d())) {
            return -1;
        }
        Pose2d pose2 = pose.toPose2d();

        boolean isRed = pose2.getX() > 8.25;

        Pose2d targetPose = isRed ? VisionConstants.redHubPos : VisionConstants.hubPos;

        Translation2d displacement = targetPose.getTranslation().minus(pose2.getTranslation());

        Rotation2d headingToTarget = displacement.getAngle();

        return headingToTarget.getRadians();
    }

    @Override
    public void periodic() {
    }
}
