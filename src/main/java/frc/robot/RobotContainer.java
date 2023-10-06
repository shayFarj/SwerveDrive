package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveToPoint;
import frc.robot.subsystems.Chassis;

public class RobotContainer {
  private CommandXboxController controller;
  private Chassis chassis;
  private Drive drive;

  public RobotContainer() {
    controller = new CommandXboxController(0);
    chassis = new Chassis();
    drive = new Drive(chassis, controller);

    configureBindings();

    chassis.setDefaultCommand(drive);
  }

  private void configureBindings() {
    controller.a().onTrue(new DriveToPoint(chassis, new Translation2d(), new Rotation2d()));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
