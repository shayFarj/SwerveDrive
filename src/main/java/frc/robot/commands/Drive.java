package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Chassis;

import static frc.robot.Constants.SwerveChassisConstants.*;
import static frc.robot.Constants.*;


public class Drive extends CommandBase {
    private final Chassis chassis;
    private final CommandXboxController controller;

    public Drive(Chassis chassis, CommandXboxController controller) {
        this.chassis = chassis;
        this.controller = controller;

        addRequirements(this.chassis);
    }

    @Override
    public void execute() {
        double leftX = deadband(controller.getRawAxis(0), JOYSTICK_DEADBAND);
        double leftY = deadband(-controller.getRawAxis(1), JOYSTICK_DEADBAND);
        double rightX = controller.getRawAxis(4) - controller.getRawAxis(5);

        ChassisSpeeds speeds = new ChassisSpeeds(leftX * DRIVE_SPEED, leftY * DRIVE_SPEED, Math.toRadians(rightX * ROTATION_SPEED));
        chassis.setVelocity(speeds);
    }

    private double deadband(double x, double deadband) {
        if (Math.abs(x) < deadband) return 0;
        else return (x - (Math.signum(x)*deadband)) / (1 - deadband);
    }
}
