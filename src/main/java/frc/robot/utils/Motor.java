package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Robot;

import static frc.robot.Constants.*;

public class Motor {
    private TalonFX m;

    private double velocity;
    private double pulses;

    public Motor(int id) {
        if (Robot.isReal())
            m = new TalonFX(id);

        velocity = 0;
        pulses = 0;
    }

    public void setInverted(boolean invert) {
        m.setInverted(invert);
    }

    public void setPower(double p) {
        if (Robot.isReal())
            m.set(ControlMode.PercentOutput, p);
        else
            velocity = p;
    }

    /**
     * 
     * @param v Velocity in m/s
     */
    public void setVelocity(double v) {
        if (Robot.isReal())
            m.set(ControlMode.Velocity, v * PULSES_PER_METER * 0.1);
        else
            velocity = v;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setPosition(double position) {
        if (Robot.isReal())
            m.set(ControlMode.Position, position);
        else
            pulses = position;
    }

    public void update() {
        pulses += velocity * 0.02;
    }

    public double getDistance() {
        return Robot.isReal() ? m.getSelectedSensorPosition() : pulses;
    }
}
