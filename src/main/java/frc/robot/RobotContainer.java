package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.chassis.SwerveChassis;

public class RobotContainer {
  private XboxController controller;
  private SwerveChassis swerveChassis;
  private SwerveDriveCommand swerveDrive;

  public RobotContainer() {
    controller = new XboxController(0);
    swerveChassis = new SwerveChassis();
    swerveDrive = new SwerveDriveCommand(swerveChassis, controller);

    swerveChassis.setDefaultCommand(swerveDrive);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
