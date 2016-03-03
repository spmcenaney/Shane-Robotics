package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.CompassSensor;

/**
 * Created by spmce on 3/1/2016.
 */
public class BigBerthaCompassCalibration extends BigBerthaTelemetry {
/*
    //private boolean run = true;

    @Override
    public void init() {
        super.init();
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
        pauseTime = time + HOLD_POSITION;
    }

    @Override
    public void init_loop() {
        if (time > pauseTime) {
            super.init_loop();
            telemetry.addData("Compass", "Compass in calibration mode");
        }
    }

    @Override
    public void loop() {
        if (time > pauseTime) {
            if (keepTurning) {

                telemetry.addData("Compass", "Calibration mode. Turning the robot...");
                DbgLog.msg("Calibration mode. Turning the robot...");

                motorRightDrive.setPower(-0.3);
                motorLeftDrive.setPower(0.3);
                motorBackLeft.setPower(0.3);
                motorBackRight.setPower(-0.3);

                // Only turn for 20 seconds (plus the two second pause at the beginning)
                if (time > turnTime + HOLD_POSITION) {
                    keepTurning = false;
                    returnToMeasurementMode = true;
                }
            } else if (returnToMeasurementMode) {

                telemetry.addData("Compass", "Returning to measurement mode");
                DbgLog.msg("Returning to measurement mode");

                motorRightDrive.setPower(0.0);
                motorLeftDrive.setPower(0.0);
                motorBackLeft.setPower(0.0);
                motorBackRight.setPower(0.0);

                compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);

                // set a new pauseTime
                pauseTime = time + HOLD_POSITION;

                returnToMeasurementMode = false;
                monitorCalibrationSuccess = true;
                telemetry.addData("Compass", "Waiting for feedback from sensor...");

            } else if (monitorCalibrationSuccess) {

                String msg = calibrationMessageToString(compass.calibrationFailed());
                telemetry.addData("Compass",  msg);

                if (compass.calibrationFailed()) {
                    DbgLog.error("Calibration failed and needs to be re-run");
                } else {
                    DbgLog.msg(msg);
                }
            }
            telemetry.addData("time", time);
            updateTelemetry();
            // set a new pauseTime
            pauseTime = time + HOLD_POSITION;
        }
    }

    private String calibrationMessageToString(boolean failed) {
        if (failed){ return "Calibration Failed!"; }
        else { return "Calibration Succeeded." ; }
    }*/
}
