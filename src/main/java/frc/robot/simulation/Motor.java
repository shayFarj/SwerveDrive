package frc.robot.simulation;

public class Motor {
    private double velocity;
    private double pulses;

    /**
     * Updates the motor velocity
     * @param v Velocity in p/0.1s
     */
    public void setVelocity(double v) {
        velocity = v;
    }

    /**
     * Returns the motor velocity
     * @return Velocity in p/0.1s
     */
    public double getVelocity() {
        return velocity;
    }

    public void setPosition(double pulses) {
        this.pulses = pulses;
    }

    public void update() {
        pulses += velocity * 10 * 0.02;
    }

    /**
     * Returns the amount of distance the motor passed
     * @return Distance in pulses
     */
    public double getDistance() {
        return pulses;
    }
}
