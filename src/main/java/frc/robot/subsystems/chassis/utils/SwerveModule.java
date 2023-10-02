package frc.robot.subsystems.chassis.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import frc.robot.Constants;
import frc.robot.Constants.ChassisConstants.SwerveModuleConstants;
import frc.robot.utils.Motor;

public class SwerveModule {
    private Motor moveMotor;
    private Motor angleMotor;

    public SwerveModule(SwerveModuleConstants constants) {
        moveMotor = new Motor(constants.moveMotorId);
        angleMotor = new Motor(constants.angleMotorId);
    }

    public void update() {
        moveMotor.update();
        angleMotor.update();
    }

    public void stopMovement() {
        angleMotor.setPower(0);
    }

    public void stopRotation() {
        angleMotor.setPower(0);
    }

    public void setVelocity(double v) {
        moveMotor.setVelocity(v);
    }

    public void setAngularVelocity(Rotation2d angle) {
        angleMotor.setVelocity(angle.getDegrees());
    }

    public void setAngle(Rotation2d angle) {
        angleMotor.setPosition(normalizeDegrees(angle.getDegrees()) * Constants.PULSES_PER_DEGREE);
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getDistance(), getAngle());
    }

    public double getDistance() {
        return moveMotor.getDistance();
    }
    
    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(normalizeDegrees(angleMotor.getDistance()));
    }

    public static double normalizeDegrees(double angle) {
        return ((angle % 360) + 360) % 360;
    }
}
