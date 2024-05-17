package frc.robot.commands;

import java.sql.Time;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class CurveCommand extends CommandBase {
    private Chassis chassis;
    private double maxVelocity;
    private Translation2d p0;
    private Translation2d p1;
    private Translation2d handle;

    private double time;
    private double startVelocity;
    private double endVelocity;

    public CurveCommand(Chassis chassis, double maxVelocity) {
        this.chassis = chassis;
        this.maxVelocity = maxVelocity;

        p0 = new Translation2d(0, 0);
        p1 = new Translation2d(3.5, 0.5);
        handle = new Translation2d(-0.5, 2);
    }
    
    @Override
    public void initialize() {
        time = 0;
        startVelocity = 0;
        endVelocity = 0;
    }

    @Override
    public void execute() {
        Translation2d b = calculatePoint(p0, p1, handle, time);
        Translation2d dx = b.minus(chassis.getPose().getTranslation());
        chassis.setVelocity(new ChassisSpeeds(dx.getX() / 0.02, dx.getY() / 0.02, 0));

        time += 0.02;
    }

    @Override
    public boolean isFinished() {
        return chassis.getPose().getTranslation().getDistance(p1) <= 0.2;
    }

    private double getTotalTime(double velocity) {
        return getCurveDistance(p0, p1, handle) / maxVelocity;
    }

    private double getSlope(Translation2d p0, Translation2d p1) {
        return (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
    }

    private double getFunction(double x, Translation2d p0, Translation2d p1) {
        return getSlope(p0, p1) * (x - p0.getX()) + p0.getY();
    }

    private Translation2d calculatePoint(Translation2d p0, Translation2d p1, Translation2d anchor, double time) {
        Translation2d q0 = p0.interpolate(handle, time);
        Translation2d q1 = handle.interpolate(p1, time);
        Translation2d b = q0.interpolate(q1, time);
        return b;
    }

    private double getCurveDistance(Translation2d p0, Translation2d p1, Translation2d anchor) {
        double dist = 0;
        Translation2d lastPoint = p0;
        for (int i = 1; i <= 50; i++) {
            Translation2d p = calculatePoint(p0, p1, anchor, i * 0.02);
            dist += p.minus(lastPoint).getNorm();
            lastPoint = p;
        }
        return dist;
    }
}
