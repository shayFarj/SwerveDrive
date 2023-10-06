package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public final class Constants {
  public static final double JOYSTICK_DEADBAND = 0.1;
  public static final double PULSES_PER_METER = 1;

  public static class DifferentialChassisConstants {
    public static final double DRIVE_SPEED = 4;
    public static final double ROTATION_SPEED = 270;
    public static final double TRACK_WIDTH = 1;
    public static DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH);
  }
  public static class SwerveChassisConstants {
    public static final SwerveModuleConstants FRONT_LEFT = new SwerveModuleConstants(new Translation2d(-1, 1));
    public static final SwerveModuleConstants FRONT_RIGHT = new SwerveModuleConstants(new Translation2d(1, 1));
    public static final SwerveModuleConstants BACK_LEFT = new SwerveModuleConstants(new Translation2d(-1, -1));
    public static final SwerveModuleConstants BACK_RIGHT = new SwerveModuleConstants(new Translation2d(1, -1));

    public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
            FRONT_LEFT.modulePosition, FRONT_RIGHT.modulePosition,
            BACK_LEFT.modulePosition, BACK_RIGHT.modulePosition
    );

    public static final double PULSE_PER_DEGREE = 1;

    public static final double DRIVE_SPEED = 4.5;
    public static final double ROTATION_SPEED = 270;

    public static class SwerveModuleConstants {
      public final Translation2d modulePosition;

      public SwerveModuleConstants(Translation2d modulePosition) {
        this.modulePosition = modulePosition;
      }
    }
  }
}
