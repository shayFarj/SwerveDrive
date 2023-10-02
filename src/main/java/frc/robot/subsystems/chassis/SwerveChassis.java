package frc.robot.subsystems.chassis;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants.SwerveModuleConstants;
import frc.robot.subsystems.chassis.utils.SwerveModule;
import frc.robot.utils.Gyro;

import static frc.robot.Constants.ChassisConstants.*;

import java.util.Arrays;

public class SwerveChassis extends SubsystemBase {
    private SwerveModule[] modules;
    private Gyro gyro;

    private SwerveDrivePoseEstimator poseEstimator;
    private Field2d field;

    public SwerveChassis() {
        modules = new SwerveModule[] {
            new SwerveModule(SwerveModuleConstants.FRONT_LEFT),
            new SwerveModule(SwerveModuleConstants.FRONT_RIGHT),
            new SwerveModule(SwerveModuleConstants.BACK_LEFT),
            new SwerveModule(SwerveModuleConstants.BACK_RIGHT),
        };
        gyro = new Gyro(GYRO_ID);

        poseEstimator = new SwerveDrivePoseEstimator(KINEMATICS, getAngle(), getModulePositions(), new Pose2d());
        field = new Field2d();
        SmartDashboard.putData(field);
    }

    public void stopMovement() {
        for (SwerveModule module : modules) module.stopMovement();
    }

    public void stopRotation() {
        for (SwerveModule module : modules) module.stopRotation();
    }

    public void setVelocity(ChassisSpeeds speeds) {
        SwerveModuleState[] states = KINEMATICS.toSwerveModuleStates(speeds);
        for (int i = 0; i < modules.length; i++) {
            modules[i].setVelocity(states[i].speedMetersPerSecond);
            modules[i].setAngle(states[i].angle);
            gyro.setAngle(states[i].angle);
        }
    }

    public SwerveModulePosition[] getModulePositions() {
        return Arrays.stream(modules).map(SwerveModule::getPosition).toArray(SwerveModulePosition[]::new);
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(gyro.getDirection());
    }

    @Override
    public void periodic() {
        for (SwerveModule module : modules) module.update();
        gyro.update();

        poseEstimator.update(getAngle(), getModulePositions());
        field.setRobotPose(poseEstimator.getEstimatedPosition());
    }
}
