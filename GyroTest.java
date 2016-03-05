package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by team on 2/29/2016.
 */
public class GyroTest extends Autonomous
{
    GyroSensor gyro;

    @Override
    public void init()
    {
        //super.init();

        gyro = hardwareMap.gyroSensor.get("hi");
    }

    @Override
    public void loop()
    {
        telemetry.addData("Rotation--- " , gyro.getRotation());
        /*
        telemetry.addData("X--- " , gyro.rawX());
        telemetry.addData("Y--- " , gyro.rawY());
        telemetry.addData("Z--- ", gyro.rawZ());*/
    }

    @Override
    public void stop()
    {
        // nothing lolololol
    }
}
