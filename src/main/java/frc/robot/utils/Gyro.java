package frc.robot.utils;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Robot;

public class Gyro {
    private PigeonIMU m;

    private double velocity;
    private double direction;

    public Gyro(int id) {
        if (Robot.isReal())
            m = new PigeonIMU(id);
    }

    public void setVelocity(Rotation2d v) {
        if (!Robot.isReal())
            velocity = v.getDegrees();
    }

    public double getVelocity() {
        return velocity;
    }

    public void setAngle(Rotation2d angle) {
        direction = angle.getDegrees();
    }

    public void update() {
        direction += velocity * 0.02;
    }

    public double getDirection() {
        return Robot.isReal() ? m.getFusedHeading() : direction;
    }
}
