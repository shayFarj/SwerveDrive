package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Chassis;

public class DriveRoute extends SequentialCommandGroup {

  public DriveRoute(Chassis chassis, Translation2d[] positions) {
    Command command = new InstantCommand();
    for (Translation2d translation : positions) {
      command = command.andThen(new DriveToPoint(chassis, translation, new Rotation2d()));
    }
    addCommands(command);
  }
}
