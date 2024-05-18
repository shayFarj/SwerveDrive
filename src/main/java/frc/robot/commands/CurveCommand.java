package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class CurveCommand extends CommandBase {
    private Chassis chassis;
    private double timeSeconds;

    private Translation2d p0;
    private Translation2d p1;
    private Translation2d handle;

    private double time;

    public CurveCommand(Chassis chassis, double timeSeconds, Translation2d p0, Translation2d p1, Translation2d handle) {
        this.chassis = chassis;
        this.timeSeconds = timeSeconds;
        this.p0 = p0;
        this.p1 = p1;
        this.handle = handle;

        addRequirements(chassis);
    }
    
    @Override
    public void initialize() {
        p0 = chassis.getPose().getTranslation();
        time = 0;
    }

    @Override
    public void execute() {
        Translation2d b = calculatePoint(p0, p1, handle, time);
        Translation2d dx = b.minus(chassis.getPose().getTranslation());
        chassis.setVelocity(new ChassisSpeeds(dx.getX() / 0.02 / timeSeconds, dx.getY() / timeSeconds / 0.02, 0));
        
        time += 0.02 / timeSeconds;
    }

    @Override
    public boolean isFinished() {
        return chassis.getPose().getTranslation().getDistance(p1) <= 0.2;
    }

    private Translation2d calculatePoint(Translation2d p0, Translation2d p1, Translation2d anchor, double time) {
        Translation2d q0 = p0.interpolate(handle, time);
        Translation2d q1 = handle.interpolate(p1, time);
        Translation2d b = q0.interpolate(q1, time);
        return b;
    }
    
    private double getCurveLength(Translation2d p0, Translation2d p1, Translation2d anchor) {
        double dist = 0;
        Translation2d lastPoint = p0;
        final int sampleCount = 32;
        for (int i = 1; i <= sampleCount; i++) {
            Translation2d p = calculatePoint(p0, p1, anchor, (1.0/sampleCount) * i);
            dist += p.minus(lastPoint).getNorm();
            lastPoint = p;
        }
        return dist;
    }
}
