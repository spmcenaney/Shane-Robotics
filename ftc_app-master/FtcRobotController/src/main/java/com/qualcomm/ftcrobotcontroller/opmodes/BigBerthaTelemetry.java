/**
 * Created by spmce on 12/1/2015.
 */
package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Provide telemetry provided by the BigBerthaHardware class.
 * @author SSI Robotics and revised by Shane McEnaney
 * @version 2015-08-02-13-57-----2015-12-01
 */
public class BigBerthaTelemetry extends BigBerthaHardware {
    /**
     * Construct the class.
     * The system calls this member when the class is instantiated.
     */
    public BigBerthaTelemetry () {
        // Initialize base classes and class members.
        // All via self-construction.
    } //--------------------------------------------------------------------------BigBerthaTelemetry
    public void init_loop () {
        /*telemetry.addData("01" , "Init Bucket Door Servo Position: "+ getBucketDoorPosition());
        telemetry.addData("02" , "Init Hook Servo Position: "       +  getHookPosition());
        telemetry.addData("03" , "Init Man Servo Position: "        + getManPosition());
        telemetry.addData("04" , "Init Left Drive Power: " + getLeftDrivePower());
        telemetry.addData("05" , "Init Right Drive Power: "+ getRightDrivePower());
        telemetry.addData("06" , "Init Lift Arm Power: "   + getLiftArmPower());
        telemetry.addData("07" , "Init Lift Power: "       + getLiftPower());
        telemetry.addData("08" , "Init Chain Hooks Power: "+ getChainHooksPower());
        telemetry.addData("09" , "Init Sweeper Power: "    + getSweeperPower());
        telemetry.addData("10" , "Init Bucket Power: "     + getBucketPower());
        telemetry.addData("11" , "Init Spinner Power: "    + getSpinnerPower());*/
        if (acceleration != null) {
            telemetry.addData("01" , "Init Acceleration: " + acceleration.getAcceleration());
            telemetry.addData("02" , "Init Acceleration Status: " + acceleration.status());
         }
        if (color != null) {
            telemetry.addData("02" , "Init Color alpha: " + color.alpha());
            telemetry.addData("03" , "Init Color argb: " + color.argb());
            telemetry.addData("04" , "Init Color blue: " + color.blue());
            telemetry.addData("05" , "Init Color green: " + color.green());
            telemetry.addData("06" , "Init Color red: " + color.red());
            telemetry.addData("07" , "Init Color I2cAddress: " + color.getI2cAddress());
        }
        /*if (color2 != null) {
            telemetry.addData("19" , "Color2 alpha: " + color2.alpha());
            telemetry.addData("20" , "Color2 argb: " + color2.argb());
            telemetry.addData("21" , "Color2 blue: " + color2.blue());
            telemetry.addData("22" , "Color2 green: " + color2.green());
            telemetry.addData("23" , "Color2 red: " + color2.red());
        }*/
        if (compass != null) {
            telemetry.addData("08" , "Init Compass Calibration Failed: " + compass.calibrationFailed());
            telemetry.addData("09" , "Init Compass Direction: " + compass.getDirection());
        }
        if (motorController!= null)
            telemetry.addData("10", "Init Motor Controller: " + motorController.getConnectionInfo());
        if (gyro != null)
            telemetry.addData("11", "Init Gyro: " + gyro.getRotation());
        if (ir != null)
            telemetry.addData("12", "Init IR Seeker: " + ir.getAngle());
        if (light != null)
            telemetry.addData("13" , "Init Light: " + light.getLightDetected());
        if (servoController != null)
            telemetry.addData("14" , "Init Servo Controller: " + servoController.getConnectionInfo());
        if (touch != null)
            telemetry.addData("15" , "Init Touch: " + touch.isPressed());
        if (multi != null) {
            telemetry.addData("16", "Init Touch Multiplexer: " + multi.getSwitches());
            telemetry.addData("17", "Init Touch Port 1: " + multi.isTouchSensorPressed(1));
            telemetry.addData("18", "Init Touch Port 2: " + multi.isTouchSensorPressed(2));
            telemetry.addData("19", "Init Touch Port 2: " + multi.isTouchSensorPressed(3));
            telemetry.addData("20", "Init Touch Port 4: " + multi.isTouchSensorPressed(4));
        }
        if (sonar != null)
            telemetry.addData("21" , "Init Sonar: " + sonar.getUltrasonicLevel());
    }
    /**
     * Update the telemetry with current gamepad readings.
     */
    public double zero (double value) {
        if (value == -0.0)
            return 0.0;
        return value;
    }
    /**
     * Update the telemetry with current values from the base class.
     */
    public void updateTelemetry () {
        if (getDriveWarningGenerated())
            setDriveFirstMessage(getDriveWarningMessage());
        if (getWarningGenerated())
            setFirstMessage(getWarningMessage());
        String game1 = gamepad1.toString();
        String game2 = gamepad2.toString();
        telemetry.addData("01" , "Robot:");
        if (acceleration != null) {
            telemetry.addData("02" , "Loop Acceleration: " + acceleration.getAcceleration());
            telemetry.addData("03" , "Loop Acceleration Status: " + acceleration.status());
        }
        if (color != null) {
            telemetry.addData("04" , "Loop Color alpha: " + color.alpha());
            telemetry.addData("05" , "Loop Color argb: " + color.argb());
            telemetry.addData("06" , "Loop Color blue: " + color.blue());
            telemetry.addData("07" , "Loop Color green: " + color.green());
            telemetry.addData("08" , "Loop Color red: " + color.red());
            telemetry.addData("09" , "Loop Color I2cAddress: " + color.getI2cAddress());
        }
        /*if (color2 != null) {
            telemetry.addData("19" , "Color2 alpha: " + color2.alpha());
            telemetry.addData("20" , "Color2 argb: " + color2.argb());
            telemetry.addData("21" , "Color2 blue: " + color2.blue());
            telemetry.addData("22" , "Color2 green: " + color2.green());
            telemetry.addData("23" , "Color2 red: " + color2.red());
        }*/
        if (compass != null) {
            telemetry.addData("10" , "Loop Compass Direction: " + compass.getDirection());
            telemetry.addData("101" , "Init Compass Calibration Failed: " + compass.calibrationFailed());
        }
        if (motorController!= null)
            telemetry.addData("11", "Loop Motor Controller: " + motorController.getConnectionInfo());
        if (gyro != null)
            telemetry.addData("12", "Loop Gyro: " + gyro.getRotation());
        if (ir != null)
            telemetry.addData("13", "Loop IR Seeker: " + ir.getAngle());
        if (light != null)
            telemetry.addData("14" , "Loop Light: " + light.getLightDetected());
        if (servoController != null)
            telemetry.addData("15" , "Loop Servo Controller: " + servoController.getConnectionInfo());
        if (touch != null)
            telemetry.addData("16" , "Loop Touch: " + touch.isPressed());
        if (multi != null) {
            telemetry.addData("17", "Loop Touch Multiplexer: " + multi.getSwitches());
            telemetry.addData("18", "Loop Touch Port 1: " + multi.isTouchSensorPressed(1));
            telemetry.addData("19", "Loop Touch Port 2: " + multi.isTouchSensorPressed(2));
            telemetry.addData("20", "Loop Touch Port 2: " + multi.isTouchSensorPressed(3));
            telemetry.addData("21", "Loop Touch Port 4: " + multi.isTouchSensorPressed(4));
        }
        if (sonar != null)
            telemetry.addData("22" , "Loop Sonar: " + sonar.getUltrasonicLevel());

        telemetry.addData("23" , "Gamepad 1 Configuration: " + game1config);
        telemetry.addData("24" ,  game1);
        telemetry.addData("25" , "Gamepad 2 Configuration: " + game2config);
        telemetry.addData("26" ,  game2);
        telemetry.addData("27" , " ");
        //telemetry.addData("07" , "Servo Position:");
        //telemetry.addData("08" , "Bucket Door Servo Position: "+ getBucketDoorPosition());
        //telemetry.addData("08" , "Climbers Servo Position: "   + getRightClimberPosition() + ", " + getLeftClimberPosition());
        //telemetry.addData("09" , "Hook Servo Position: "       + getHookPosition());
        //telemetry.addData("10" , "Man Servo Position: " + getManPosition());
        //telemetry.addData("11" , "Flag Servo Position: "       + getRightFlagPosition() + ", " + getLeftFlagPosition());
        telemetry.addData("28" , "Motor Power:");
        telemetry.addData("29" , "Right Drive Power: "+ getRightDrivePower()+ ", " + getRightEncoderCount());
        telemetry.addData("30" , "Left Drive Power: " + getLeftDrivePower() + ", " + getLeftEncoderCount() + ", " + leftDrivePower);
        telemetry.addData("31" , "Back Right Power: " + getBackRightPower() + ", " + getRightEncoderCount());
        telemetry.addData("32" , "Back Left Power: "  + getBackLeftPower()  + ", " + getLeftEncoderCount());
        /*telemetry.addData("17" , "Right Arm Power: "  + getRightArmPower()  + ", " + getLiftArmEncoderCount());
        telemetry.addData("18" , "Left Arm Power: "   + getLeftArmPower()   + ", " + getLiftArmEncoderCount());
        telemetry.addData("19" , "Right Lift Power: " + getRightLiftPower() + ", " + getLiftEncoderCount());
        telemetry.addData("20" , "Left Lift Power: "  + getLeftLiftPower()  + ", " + getLiftEncoderCount());
        telemetry.addData("21" , "Chain Hooks Power: "+ getChainHooksPower()+ ", " + getChainHooksEncoderCount());
        telemetry.addData("22" , "Sweeper Power: "    + getSweeperPower()   + ", " + getSweeperEncoderCount());
        telemetry.addData("23" , "Bucket Power: "     + getBucketPower()    + ", " + getBucketEncoderCount());
        telemetry.addData("24" , "Spinner Power: "    + getSpinnerPower()   + ", " + getSpinnerEncoderCount());*/
        /*telemetry.addData("2401" , "Sweeper Off: "+ sweeperOff);
        telemetry.addData("2402" , "Aux 1 Scale: "+ aux1ScaleOff);
        telemetry.addData("2403" , "Left Drive Off: "+ leftDriveOff);
        telemetry.addData("2404" , "Right Drive Off: "+ rightDriveOff);
        telemetry.addData("2405" , "Back Left Drive Off: "+ backLeftOff);
        telemetry.addData("2406" , "Back Right Drive Off: "+ backRightOff);
        telemetry.addData("2407" , "Full Drive Off: "+ fullDriveOff);
        telemetry.addData("2408" , "Front Drive Off: "+ driveOff);
        telemetry.addData("2409" , "Back Drive Off: "+ backDriveOff);
        telemetry.addData("2410" , "Left Fast Drive Off: "+ leftFastDriveOff);
        telemetry.addData("2411" , "Right Fast Drive Off: "+ rightFastDriveOff);
        telemetry.addData("2412" , "Fast Drive Off: "+ fastDriveOff);
        telemetry.addData("2410" , "Left Slow Drive Off: " + leftSlowDriveOff);
        telemetry.addData("2411" , "Right Slow Drive Off: "+ rightSlowDriveOff);
        telemetry.addData("2412" , "Slow Drive Off: "      + slowDriveOff);
        telemetry.addData("2413" , "Left Climber Off: "    + leftClimberOff);
        telemetry.addData("2414" , "Right Climber Off: "   + rightClimberOff);
        telemetry.addData("2415" , "Left Arm Off: "        + leftArmOff);
        telemetry.addData("2416" , "Right Arm Off: "       + rightArmOff);
        telemetry.addData("2417" , "Left Lift Off: "       + leftLiftOff);
        telemetry.addData("2418" , "Right Lift Off: "      + rightLiftOff);*/
        //telemetry.addData("2419" , ": "+ Off);
        //telemetry.addData("24" , "Bucket Off: " + BigBerthaTeleOp.isBucketOff());
        //telemetry.addData("25" , "Aux 2 Scale: "+ BigBerthaTeleOp.isAux2ScaleOff());
    } //--------------------------------------------------------------------------updateTelemetry

    public void updateGamepadTelemetry () {
        // Send telemetry data concerning gamepads to the driver station.
        telemetry.addData("35" , " ");
        telemetry.addData("36" , " ");
        telemetry.addData("37" , "Robot Controllers: ");
        telemetry.addData("38" , "Gamepad 1: ");
        telemetry.addData("39" , "Servos:");
        telemetry.addData("40" , "Flag A: "       + gamepad1.a);
        telemetry.addData("41" , "Flag B: "       + gamepad1.b);
        telemetry.addData("42" , "Bucket Door Y: "+ gamepad1.y);
        telemetry.addData("43" , "Bucket Door X: "+ gamepad1.x);
        telemetry.addData("44" , "Motors:");
        telemetry.addData("45" , "Right Drive Y Stick: "+zero(-gamepad1.right_stick_y));
        telemetry.addData("46" , "Left Drive Y Stick: " +zero(-gamepad1.left_stick_y));
        telemetry.addData("47" , "Right X Stick: "           + gamepad1.right_stick_x);
        telemetry.addData("48" , "Left X Stick: "            + gamepad1.left_stick_x);
        telemetry.addData("49" , "Dpad Up: "                 + gamepad1.dpad_up);
        telemetry.addData("50" , "Dpad Down: "               + gamepad1.dpad_down);
        telemetry.addData("51" , "Dpad Right: "              + gamepad1.dpad_right);
        telemetry.addData("52" , "Dpad Left: "               + gamepad1.dpad_left);
        telemetry.addData("53" , "Chain Right Bumper: "      + gamepad1.right_bumper);
        telemetry.addData("54" , "Rev Chain Left Bumper: "   + gamepad1.left_bumper);
        telemetry.addData("55" , "Sweeper Right Trigger: "   + gamepad1.right_trigger);
        telemetry.addData("56" , "Rev Sweeper Left Trigger: "+-gamepad1.left_trigger);
        telemetry.addData("57" , "Scale/Sweeper Off Start: " + gamepad1.start);
        telemetry.addData("58" , "Scale/Sweeper Res Guide: " + gamepad1.guide);
        telemetry.addData("59" , "Back: "                    + gamepad1.back);
        telemetry.addData("60" , "Gamepad 2:");
        telemetry.addData("61" , " ");
        telemetry.addData("62" , "Servos:");
        telemetry.addData("63" , "Man A: " + gamepad2.a);
        telemetry.addData("64" , "Man B: " + gamepad2.b);
        telemetry.addData("65" , "Hook Y: "+ gamepad2.y);
        telemetry.addData("66" , "Hook X: "+ gamepad2.x);
        telemetry.addData("67" , "Motors:");
        telemetry.addData("68" , "Right Arm Y Stick: "+zero(-gamepad2.right_stick_y));
        telemetry.addData("69" , "Left Arm Y Stick: " +zero(-gamepad2.left_stick_y));
        telemetry.addData("70" , "Right X Stick: "         + gamepad2.right_stick_x);
        telemetry.addData("71" , "Left X Stick: "          + gamepad2.left_stick_x);
        telemetry.addData("72" , "Bucket Dpad Up: "        + gamepad2.dpad_up);
        telemetry.addData("73" , "Bucket Dpad Down: "      + gamepad2.dpad_down);
        telemetry.addData("74" , "Dpad Right: "            + gamepad2.dpad_right);
        telemetry.addData("75" , "Dpad Left: "             + gamepad2.dpad_left);
        telemetry.addData("76" , "Rev Right Lift Bumper: " + gamepad2.right_bumper);
        telemetry.addData("77" , "Rev Left Lift Bumper: "  + gamepad2.left_bumper);
        telemetry.addData("78" , "Right Lift Trigger: "    + gamepad2.right_trigger);
        telemetry.addData("79" , "Left Lift Trigger: "     +-gamepad2.left_trigger);
        telemetry.addData("80" , "Start: "                 + gamepad2.start);
        telemetry.addData("81" , "Guide: "                 + gamepad2.guide);
        telemetry.addData("82" , "Back: "                  + gamepad2.back);
    } //--------------------------------------------------------------------------updateGamepadTelemetry
    /**
     * Update the telemetry's first message with the specified message.
     */
    public void setFirstMessage (String pMessage) {telemetry.addData("00", pMessage);}
    public void setDriveFirstMessage (String pMessage) {telemetry.addData("001", pMessage);}
    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void setErrorMessage (String pMessage) {setFirstMessage("ERROR: " + pMessage);}
} //------------------------------------------------------------------------------BigBerthaTelemetry
