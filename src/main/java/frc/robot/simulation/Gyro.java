package frc.robot.simulation;

public class Gyro {
    private double velocity;
    private double angle;

    /**
     * Updates the gyro velocity
     * @param v Velocity in d/s
     */
    public void setVelocity(double v) {
        velocity = v;
    }

    public void setAngle(double d) {
        angle = d;
    }

    public void update() {
        angle += velocity * 0.02;
    }

    /**
     * Returns the angle of the gyro
     * @return Angle in degrees
     */
    public double getAngle() {
        return angle;
    }
}
