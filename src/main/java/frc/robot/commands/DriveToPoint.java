package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.utils.Trapezoid;

public class DriveToPoint extends CommandBase {
  private final Chassis chassis;
  private final Translation2d destination;
  private final Rotation2d desiredAngle;
  
  private final Trapezoid trapezoid;

  public DriveToPoint(Chassis chassis, Translation2d destination, Rotation2d desiredAngle) {
    this.chassis = chassis;
    this.destination = destination;
    this.desiredAngle = desiredAngle;

    trapezoid = new Trapezoid(6, 8, 0.8, 0);

    addRequirements(chassis);
  }

  @Override
  public void initialize() {
      chassis.stop();
  }
  
  @Override
  public void execute() {
      Pose2d chassisPose = chassis.getPose();
      Translation2d trackVec = destination.minus(chassisPose.getTranslation());
      Translation2d trackVecNormalized = trackVec.div(trackVec.getNorm());
      double angleError = desiredAngle.minus(chassisPose.getRotation()).getDegrees();
      double remainingDistance = trackVec.getNorm();
      double velocity = trapezoid.calculate(remainingDistance, chassis.getVelocity().getNorm());

      ChassisSpeeds speeds = new ChassisSpeeds(trackVecNormalized.getX() * velocity, trackVecNormalized.getY() * velocity, angleError * 0.09);
      chassis.setVelocity(speeds);
  }

  @Override
  public void end(boolean interrupted) {
      chassis.stop();
  }

  @Override
  public boolean isFinished() {
    Pose2d pose = chassis.getPose();
    double angleError = desiredAngle.minus(pose.getRotation()).getDegrees();
    return pose.getTranslation().getDistance(destination) <= 0.07 && Math.abs(angleError) <= 2;
  }
}
