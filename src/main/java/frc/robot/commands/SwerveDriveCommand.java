package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.chassis.SwerveChassis;

public class SwerveDriveCommand extends CommandBase {
  private SwerveChassis chassis;
  private XboxController controller;

  public SwerveDriveCommand(SwerveChassis chassis, XboxController controller) {
    this.chassis = chassis;
    this.controller = controller;

    addRequirements(chassis);
  }

  @Override
  public void execute() {
    double leftX = controller.getLeftX();
    double leftY = controller.getLeftY();
    double rightX = controller.getRightX();
    
    ChassisSpeeds speeds = new ChassisSpeeds(leftY, leftX, Math.toRadians(rightX));
    chassis.setVelocity(speeds);

    if (Math.abs(leftX) < 0.2 && Math.abs(leftY) < 0.2) chassis.stopMovement();
    if (Math.abs(rightX) < 0.2) chassis.stopRotation();
  }
}
