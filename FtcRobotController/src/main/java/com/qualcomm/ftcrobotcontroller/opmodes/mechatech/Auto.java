package com.qualcomm.ftcrobotcontroller.opmodes.mechatech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by RyanZhu on 12/1/2015.
 */
public class Auto extends MechaTechOp {

    @Override
    public void init() {
        super.init();
         // ready to clean the debris
        Cleaner.setPosition(0.9);
        GripM.setPosition(0.5);
        GripR.setPosition(gripRPosition);
        GripL.setPosition(gripLPosition);
        TriggerR.setPosition(triggerRPosition);
        TriggerL.setPosition(triggerLPosistion);
    }

    @Override


    public void loop() {

        if (! isStarted ) {
            this.time = 0.0;
            this.resetStartTime();
            Cleaner.setPosition(0.02);
            isStarted = true;
        }
        _runTime = this.getRuntime();

        double left=0.0, right = 0.0, arm =0.0, slider=0.0;

        /*
         * Use the 'time' variable of this op mode to determine
         * how to adjust the motor power.
         */
        if (_runTime <= 1) {
            // from 0 to 1 seconds, don't do anything.
        }
        else if (_runTime > 10 && _runTime <=25)  // move backward
        {
            left = -0.5;
            right = -0.5;
        }
        else if (_runTime > 25 && _runTime <=25.5)  // turn
        {
            left = -0.5;
            right = 0.5  ;
        }
        else if (_runTime > 25.5 && _runTime <= 26) {
            // move backward
            left = 0.5;
            right = 0.5;
        } else {
            // after 26.5 seconds, stop.
            left = 0.0;
            right = 0.0;
        }

        Left.setPower(left);
        Right.setPower(right);
        Arm.setPower(arm);
        Slide.setPower(slider);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("time", "elapsed time: " + Double.toString(this.time));
    }
    boolean isStarted = false;
    double _runTime = 0.0D;
}
