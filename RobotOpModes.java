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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

abstract class RobotOpModes extends OpMode
{

    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorLiftBox;
    DcMotor motorLiftIn;
    DcMotor motorArmTilt;

    Servo rightArm;
    Servo leftArm;
    Servo centerArm;

    double rightArmPos = 0.0;
    double leftArmPos = 1.00;
    double centerArmPos = 0.0;
    double armRate = 0.01;

    boolean halfSpeed = false;
    boolean buttonStart = true;
    boolean isTankDrive = true;

    @Override
    public void init()
    {
        // Hardware Map //
        hardwareMap();
        telemetry.addData("Setup--- ", "Robot Hardware Mapped");
    }

    // No use in overloading loop(), that's like calling an infinite loop inside an infinite loop

    // Called when opmode terminated, basically destructor
    @Override
    public void stop()
    {

    }

    // Hardware Map
    protected void hardwareMap()
    {
        motorFrontRight = hardwareMap.dcMotor.get("motor_frontRight");
        motorBackRight = hardwareMap.dcMotor.get("motor_backRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motor_frontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motor_backLeft");
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);		//reverses these two motors
        motorLiftBox = hardwareMap.dcMotor.get("motor_liftBox");
        motorLiftIn = hardwareMap.dcMotor.get("motor_liftIn");
        motorLiftIn.setDirection(DcMotor.Direction.REVERSE);
        motorArmTilt = hardwareMap.dcMotor.get("motor_armTilt");
        rightArm = hardwareMap.servo.get("servo_rightArm");
        leftArm = hardwareMap.servo.get("servo_leftArm");
        centerArm = hardwareMap.servo.get("servo_centerArm");

    }

    // Resets motor power and servo positions
    protected void reset()
    {
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
        motorBackRight.setPower(0);
        motorLiftBox.setPower(0);
        motorLiftIn.setPower(0);
        motorArmTilt.setPower(0);
        rightArm.setPosition(0.2);
        centerArm.setPosition(0.2);
        leftArm.setPosition(0.2);
        rightArmPos = 0.2;
        leftArmPos = 0.2;
        centerArmPos = 0.2;
        armRate = 0.1;
    }

    // Scales joystick input to give the robot the correct "feel" (accelerates slowly at first and then quickly)
    protected double scaleInput(double value)
    {
        double motorPower;

        motorPower = Math.pow(value, 3); // Scales input to make the velocity graph y=x^3. Easier to understand and more intuative than the array that was here before.

        return motorPower;
    }

    // Telemetry method, overloaded in subclasses
    protected void telmet()
    {
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo Power--- " , "Center Arm Position: " + centerArmPos);
        telemetry.addData("Servo Power--- " , "Right Arm Position: " + rightArmPos);
        telemetry.addData("Servo Power--- " , "Left Arm Position: " + leftArmPos);
    }
}