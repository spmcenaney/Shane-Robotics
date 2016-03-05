/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class TeleOpMain extends RobotOpModes
{
    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void loop()
    {
        // Conditionals allow driver one to switch between two drivemodes
        if(gamepad1.start)
        {
            isTankDrive = !isTankDrive;
        }

        // Determines which drive mode to use
        if(isTankDrive)
        {
            tankDrive();
        }
        else
        {
            haloDrive();
        }

        runServos();
        extendArm();
        pivotArm();
    }

    @Override
    public void stop()
    {
        super.stop();
    }

    // Drivetrain mode- each joystick y-value controls the drivetrain motors of one side
    private void tankDrive()
    {
        // Declare and initialize variables
        double leftThrottle = gamepad1.left_stick_y;
        double rightThrottle = gamepad1.right_stick_y;
        double frontLeft = gamepad1.left_trigger;
        double frontRight = gamepad1.right_trigger;

        // Clip values to make them easier to work with.
        leftThrottle = Range.clip(leftThrottle, -1, 1);
        rightThrottle = Range.clip(rightThrottle, -1, 1);
        frontLeft = Range.clip(frontLeft, 0,1);
        frontRight = Range.clip(frontRight, 0,1);

        // Pass values through a scale(y = x^3) to acheive desired movement "feel" (accelerates slowly then quickly).
        leftThrottle = scaleInput(leftThrottle);
        rightThrottle = scaleInput(rightThrottle);
        frontLeft = (double)scaleInput(frontLeft);
        frontRight = (double)scaleInput(frontRight);

        // set power with triggers
        if(gamepad1.left_trigger > 0 || gamepad1.right_trigger > 0)
        {
            motorFrontLeft.setPower(-frontLeft);
            motorFrontRight.setPower(-frontRight);
        }

        else
        {
            motorFrontLeft.setPower(leftThrottle);
            motorFrontRight.setPower(rightThrottle);
        }

        if(gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0)
        {
            motorFrontLeft.setPower(leftThrottle);
            motorFrontRight.setPower(rightThrottle);
        }

        // Power the motors
        motorBackLeft.setPower(leftThrottle);
        motorBackRight.setPower(rightThrottle);

        // Output telemetry
        telmet(leftThrottle, rightThrottle);
    }

    // Drivetrain mode- values are manipulated to allow turning with the center on rotation either in the middle of the robot
    // or on one of the back wheels- somewhat like a car
    // We dubbed this mode as "haloDrive" because the way its controls are arranged
    // resembles first person games on a video game console
    private void haloDrive()
    {
        // Declare and initialize variables
        double forwardBackward = gamepad1.left_stick_y;
        double leftRight = gamepad1.right_stick_x;

        double frontLeft = forwardBackward - leftRight;
        double backLeft = forwardBackward - leftRight;
        double frontRight = forwardBackward + leftRight;
        double backRight = forwardBackward + leftRight;

        frontLeft = Range.clip(frontLeft , -1 , 1)

        ;
        backLeft = Range.clip(backLeft , -1 , 1);
        frontRight = Range.clip(frontRight , -1 , 1);
        backRight = Range.clip(backRight , -1 , 1);

        frontLeft = scaleInput(frontLeft);
        backLeft = scaleInput(backLeft);
        frontRight = scaleInput(frontRight);
        backRight = scaleInput(backRight);

        motorFrontLeft.setPower(frontLeft);
        motorBackLeft.setPower(backLeft);
        motorFrontRight.setPower(frontRight);
        motorBackRight.setPower(backRight);

        telmet(frontLeft , backLeft , frontRight , backRight);
    }

    private void extendArm()
    {
        // Declare and initialize variables
        double liftInPower;
        double liftBoxPower;

        liftInPower = gamepad2.left_stick_y;
        liftBoxPower = gamepad2.right_stick_y;

        // Clip values to make them easier to work with
        liftInPower = Range.clip(liftInPower, -1, 1);
        liftBoxPower = Range.clip(liftBoxPower, -1, 1);

        // Pass values through a scale(y = x^3) to acheive desired movement "feel" (accelerates slowly then quickly).
        liftInPower = scaleInput(liftInPower);
        liftBoxPower = scaleInput(liftBoxPower);

        // Set motor power
        motorLiftIn.setPower(liftInPower);
        motorLiftBox.setPower(liftBoxPower);
    }

    // Function pivots the arms
    private void pivotArm()
    {
        // Declare and initialize variables
        double Tilt = gamepad2.right_trigger;
        double Tilt2 = gamepad2.left_trigger;

        // Clip values to make them easier to work with
        Tilt = Range.clip(Tilt, 0,1);
        Tilt2 = Range.clip(Tilt2, 0,1);

        // Pass values through a scale(y = x^3) to acheive desired movement "feel" (accelerates slowly then quickly).
        Tilt = (double)scaleInput(Tilt);
        Tilt2 = (double)scaleInput(Tilt2);

        Tilt2 = -Tilt2;

        if(Tilt > 0)
        {
            motorArmTilt.setPower(Tilt);
        }

        else
        {
            motorArmTilt.setPower(Tilt2);
        }

        if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0)
        {
            motorArmTilt.setPower(0);
        }
    }

    // Runs servos
    private void runServos()
    {
        if (gamepad1.right_bumper)
        {
            leftArmPos = 1;
        }

        else if (gamepad1.left_bumper)
        {
            leftArmPos = 0.42;
        }

        if (gamepad1.b)
        {
            rightArmPos = 0;
        }

        else if (gamepad1.a)
        {
            rightArmPos = .49;
        }

        if (gamepad2.y)
        {
            centerArmPos += armRate;
        }
        else if (gamepad2.a)
        {
            centerArmPos -= armRate;
        }

        // Clip values to make them easier to work with
        leftArmPos = Range.clip(leftArmPos, 0, 1);
        rightArmPos = Range.clip(rightArmPos, 0, 1);
        centerArmPos = Range.clip(centerArmPos, 0, 1);

        leftArm.setPosition(leftArmPos);
        rightArm.setPosition(rightArmPos);
        centerArm.setPosition(centerArmPos);
    }


    // Telemetry methods for drive motors. One is overloaded for tankDrive()
    // while the other is overloaded for haloDrive()
    private void telmet(double leftSide , double rightSide)
    {
        super.telmet();
        telemetry.addData("Motor Power--- " , "Left Side Power: " + String.format("%.2f", leftSide));
        telemetry.addData("Motor Power--- " , "Right Side Power: " + String.format("%.2f", rightSide));
        telemetry.addData("Tank Drive is", isTankDrive);
    }

    private void telmet(double frontLeft , double backLeft , double frontRight , double backRight)
    {
        super.telmet();
        telemetry.addData("Motor Power--- " , "Front Left Power: " + String.format("%.2f", frontLeft));
        telemetry.addData("Motor Power--- " , "Back Left Power: " + String.format("%.2f", backLeft));
        telemetry.addData("Motor Power--- " , "Front Right Power: " + String.format("%.2f" , frontRight));
        telemetry.addData("Motor Power--- " , "Back Right Power: " + String.format("%.2f" , backRight));
        telemetry.addData("Tank Drive is", isTankDrive);
    }
}
