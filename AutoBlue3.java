package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by team on 11/8/2015.
 */


    /*
                            Autonomous Plan:
     1. Set up to drive the robot backwards.
     2. 15 seconds after the autonomous period starts, drive the robot backwards into the parking zone.
     The robot will push derbis into the parking zone while driving backwards.
     Final Goal: Score derbis in the alliance's parking zone to score around 10 points.

     */



public class AutoBlue3 extends Autonomous{
/*
    //Motor Definitions

    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorLiftBox;
    DcMotor motorLiftIn;
    DcMotor motorArmTilt;
*/
    //Variables

    int currentState = 10;                                           //start on the wait case
    double Erotation = 1440;                                         //1 rotation of the motor

    @Override
    public void init() {
/*
        //Initialize Code
        motorFrontRight = hardwareMap.dcMotor.get("motor_frontRight");
        motorBackRight = hardwareMap.dcMotor.get("motor_backRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motor_frontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motor_backLeft");
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        motorLiftBox = hardwareMap.dcMotor.get("motor_liftBox");
        motorLiftIn = hardwareMap.dcMotor.get("motor_liftIn");
        motorLiftIn.setDirection(DcMotor.Direction.REVERSE);			//reversed motor on a theory
        motorArmTilt = hardwareMap.dcMotor.get("motor_armTilt");

        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLiftBox.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLiftIn.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorArmTilt.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        telemetry.addData("Text","***Resetting***");
        */

        super.init();
    }
/*
    @Override
    public void start() {
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLiftBox.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorLiftIn.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorArmTilt.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        resetStartTime();
    }
*/
    @Override
    public void loop() {

        //Repeatedly Loop this code
        if(currentState==11&&this.getRuntime()>5)   //if 15 seconds has passed and we are set up, start driving
        {
            telemetry.addData("Text", "***Starting***");
            currentState = 0;
        }

        int currentPosR = motorBackRight.getCurrentPosition();
        int currentPosL = motorBackLeft.getCurrentPosition();

        telemetry.addData("currentState", currentState);
        telemetry.addData("currentPosL", currentPosL);
        telemetry.addData("targetPositionL", motorBackLeft.getTargetPosition());

        switch (currentState) {
            case 0:                              //set up drive forward
                motorFrontRight.setPower(.3);
                motorBackRight.setPower(.3);
                motorFrontLeft.setPower(.3);
                motorBackLeft.setPower(.3);
                //int rev = 1;
                double rev = 4260;   //half a revolution of the motor
                motorFrontRight.setTargetPosition(((int) rev) + motorBackRight.getCurrentPosition());
                motorBackRight.setTargetPosition(((int) rev) + motorBackRight.getCurrentPosition());
                motorFrontLeft.setTargetPosition(((int) rev) + motorBackLeft.getCurrentPosition());
                motorBackLeft.setTargetPosition(((int) rev) + motorBackLeft.getCurrentPosition());

                currentState = 1;
                break;

            case 1:                              //drive forward

                if(motorBackLeft.getCurrentPosition() <= motorBackLeft.getTargetPosition())
                {
                    telemetry.addData("currentState", currentState);
                }

                else {
                    motorFrontRight.setPower(0);
                    motorBackRight.setPower(0);
                    motorFrontLeft.setPower(0);
                    motorBackLeft.setPower(0);

                    currentState = 2;
                }

                break;

            case 2:
                motorArmTilt.setPower(.75);
                //int rev = 1;
                double rev2 = 3*Erotation;   //half a revolution of the motor
                motorArmTilt.setTargetPosition(-((int)rev2)+ motorArmTilt.getCurrentPosition());

                currentState = 3;
                break;

            case 3:                              //drive forward

                if(motorArmTilt.getCurrentPosition() >= motorArmTilt.getTargetPosition())
                {
                    telemetry.addData("currentState", currentState);
                }

                else {
                    motorArmTilt.setPower(0);

                    currentState = 1000;
                }

                break;

            case 10:
                telemetry.addData("Text", "***Waiting***");
                resetStartTime();
                currentState = 11;
                break;

            case 11:
                break;

            default:                            //stop

                telemetry.addData("Text","***Finished***");
                break;
        }
    }

}
