package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class Constants {
    public static class ChassisConstants {
        public static final int GYRO_ID = 0;

        public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
            SwerveModuleConstants.FRONT_LEFT.offset,
            SwerveModuleConstants.FRONT_RIGHT.offset,
            SwerveModuleConstants.BACK_LEFT.offset,
            SwerveModuleConstants.BACK_RIGHT.offset
        );

        public static class SwerveModuleConstants {
            public static final SwerveModuleConstants FRONT_LEFT = new SwerveModuleConstants(0, 0, new Translation2d(-0.5, 0.5));
            public static final SwerveModuleConstants FRONT_RIGHT = new SwerveModuleConstants(0, 0, new Translation2d(0.5, 0.5));
            public static final SwerveModuleConstants BACK_LEFT = new SwerveModuleConstants(0, 0, new Translation2d(-0.5, -0.5));
            public static final SwerveModuleConstants BACK_RIGHT = new SwerveModuleConstants(0, 0, new Translation2d(0.5, -0.5));

            public final int moveMotorId;
            public final int angleMotorId;
            public final Translation2d offset;

            public SwerveModuleConstants(int moveMotorId, int angleMotorId, Translation2d offset) {
                this.moveMotorId = moveMotorId;
                this.angleMotorId = angleMotorId;
                this.offset = offset;
            }
        }
    }
    public static final double PULSES_PER_METER = 50000;
    public static final double PULSES_PER_DEGREE = 1;
}
