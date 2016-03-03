package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.CompassSensor;

/**
 * Created by spmce on 3/1/2016.
 */
public class BigBerthaCompassCalibration extends BigBerthaTelemetry {

    private boolean keepTurning = true;
    private boolean returnToMeasurementMode = false;
    private boolean run = true;

    @Override
    public void init() {
        super.init();
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Compass", "Compass in calibration mode");
    }

    @Override
    public void loop() {
        if (run) {
            if (keepTurning) {
                telemetry.addData("Compass", "Calibration mode. Turning the robot...");
                motorRightDrive.setPower(-0.3);
                motorLeftDrive.setPower(0.3);
                motorBackLeft.setPower(0.3);
                motorBackRight.setPower(-0.3);
            }
            if (time > 22) {
                keepTurning = false;
                returnToMeasurementMode = true;
            }
            if (returnToMeasurementMode) {
                telemetry.addData("Compass", "Returning to measurement mode");
                motorRightDrive.setPower(0.0);
                motorLeftDrive.setPower(0.0);
                motorBackLeft.setPower(0.0);
                motorBackRight.setPower(0.0);
                compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                telemetry.addData("compass01",compass.calibrationFailed());
                run = false;
            }
        }
        else {
            telemetry.addData("compass01",compass.calibrationFailed());
            telemetry.addData("compass1", compass.getDirection());
            telemetry.addData("compass2", compass.status());
        }
        telemetry.addData("time" , time);
        updateTelemetry();
    }
}
