package frc.robot.simulation;

import static frc.robot.Constants.*;

public class Motor {
    private double velocity;
    private double pulses;

    /**
     * Updates the motor velocity
     * @param v Velocity in m/s
     */
    public void setVelocity(double v) {
        velocity = v;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setPosition(double pulses) {
        this.pulses = pulses;
    }

    public void update() {
        pulses += velocity * 0.02 * PULSES_PER_METER;
    }

    /**
     * Returns the amount of distance the motor passed
     * @return Distance in pulses
     */
    public double getDistance() {
        return pulses;
    }
}
