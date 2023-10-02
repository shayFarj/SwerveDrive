package frc.robot.subsystems.chassis;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.Gyro;
import frc.robot.utils.Motor;

public class TankChassis extends SubsystemBase {
  private Motor mLeft;
  private Motor mRight;
  private Gyro gyro;
  
  private DifferentialDriveKinematics kinematics;
  private DifferentialDrivePoseEstimator poseEstimator;
  private Field2d field;

  public TankChassis() {
    mLeft = new Motor(0);
    mRight = new Motor(0);
    gyro = new Gyro(0);

    kinematics = new DifferentialDriveKinematics(1);
    poseEstimator = new DifferentialDrivePoseEstimator(kinematics, getAngle(), mLeft.getDistance(), mRight.getDistance(), new Pose2d());
    field = new Field2d();
    SmartDashboard.putData(field);
  }
  
  public void setVelocity(ChassisSpeeds speeds) {
    DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);
    mLeft.setVelocity(wheelSpeeds.leftMetersPerSecond);
    mRight.setVelocity(wheelSpeeds.rightMetersPerSecond);
    gyro.setVelocity(new Rotation2d(speeds.omegaRadiansPerSecond));

    System.out.println(gyro.getVelocity());
  }

  public Rotation2d getAngle() {
    return Rotation2d.fromDegrees(gyro.getDirection());
  }


  @Override
  public void periodic() {
    mLeft.update();
    mRight.update();
    gyro.update();

    poseEstimator.update(getAngle(), mLeft.getDistance(), mRight.getDistance());
    field.setRobotPose(poseEstimator.getEstimatedPosition());
  }
}
