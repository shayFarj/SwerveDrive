// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import edu.wpi.first.math.geometry.Translation2d;

/** Add your docs here. */
public class Spline {
    Translation2d p0;
    Translation2d p1;
    Translation2d p2;
    Translation2d p3;


    Translation2d[] coeff;//a, b, c, d

    public Spline(Translation2d p0, Translation2d p1, Translation2d p2,Translation2d p3)
    {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        this.coeff[0] = (this.p1.minus(this.p2)).times(3).plus(this.p3.minus(this.p1));
        this.coeff[1] = (this.p0.plus(this.p2)).times(3).minus(this.p1.times(6));
        this.coeff[]
    }
}
