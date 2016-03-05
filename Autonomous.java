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

import com.qualcomm.robotcore.hardware.DcMotorController;

public abstract class Autonomous extends RobotOpModes
{
    // 1 rev = 1440 encoder ticks

    private final double wheelDiam = 4.5; // in inches
    private final double PI = 3.141592653589793238462648;
    private final double wheelCirc = wheelDiam * PI;
    private final int encoderCounts = 1440;

    @Override
    public void init()
    {
        super.init();

        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLiftBox.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLiftIn.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorArmTilt.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        telemetry.addData("Setup--- " , "Motor Encoders Reset");

        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLiftBox.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLiftIn.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorArmTilt.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        telemetry.addData("Setup--- " , "Motor Encoders Ready");
    }

    protected void driveForward(double distance) // Distance inches
    {
        // Wheel diameter: 12.2 cm maybe who knows

        private int position = (int)(distance / wheelCirc * encoderCounts);

        motorFrontRight.setMotorTargetPosition(position);
        motorBackRight.setMotorTargetPosition(position);
        motorFrontLeft.setMotorTargetPosition(position);
        motorBackLeft.setMotorTargetPostition(position);
    }

    protected void turn(double radians , double speed)
    {

    }
}
