package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.chassis.TankChassis;

public class TankDriveCommand extends CommandBase {
  private TankChassis chassis;
  private XboxController controller;

  public TankDriveCommand(TankChassis chassis, XboxController controller) {
    this.chassis = chassis;
    this.controller = controller;

    addRequirements(chassis);
  }

  @Override
  public void execute() {
    double leftY = -controller.getLeftY();
    double leftX = -controller.getLeftX();

    ChassisSpeeds speeds = new ChassisSpeeds(leftY, 0, leftX * 3);
    chassis.setVelocity(speeds);
  }
}
