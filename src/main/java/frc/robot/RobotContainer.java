package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.CurveCommand;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveRoute;
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
    controller.b().onTrue(new DriveRoute(chassis, new Translation2d[] {
      new Translation2d(3, 5),
      new Translation2d(2, 8),
      new Translation2d(5, 1),
      new Translation2d(7, 2),
    }));
    controller.x().onTrue(new CurveCommand(chassis, 3));
  }

  public Command getAutonomousCommand() {
    return new CurveCommand(chassis, 4);
  }
}
