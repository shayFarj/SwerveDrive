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

    public CurveCommand(Chassis chassis, double timeSeconds) {
        this.chassis = chassis;
        this.timeSeconds = timeSeconds;

        p0 = new Translation2d(0, 0);
        p1 = new Translation2d(3.5, 1.5);
        handle = new Translation2d(2, 2.5);

        addRequirements(chassis);
    }
    
    @Override
    public void initialize() {
        time = 0;
    }

    @Override
    public void execute() {
        Translation2d b = calculatePoint(p0, p1, handle, time);
        Translation2d dx = b.minus(chassis.getPose().getTranslation());
        System.out.println("b = " + b + " dx = " + dx + " t = " + time);
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
