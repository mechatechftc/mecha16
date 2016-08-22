package com.qualcomm.ftcrobotcontroller.opmodes.mechatech;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by RyanZhu on 11/17/2015.
 */
public class MechaTechOp extends OpMode {

    protected DcMotor Left;
    protected DcMotor Right;
    protected DcMotor Arm;
    protected DcMotor Slide;

    protected Servo GripR;
    protected Servo GripL;
    protected Servo GripM;
    protected Servo TriggerL;
    protected Servo TriggerR;
    protected Servo Cleaner;

    double gripLPosition = 1.0;
    double gripRPosition = 1.0;
    double gripMPosition = 0.6;

    final static double GRIP_L_MIN_POSITION = 0.0;
    final static double GRIP_L_MAX_POSITION = 1.0;
    final static double GRIP_R_MIN_POSITION = 0.0;
    final static double GRIP_R_MAX_POSITION = 1.0;
    final static double GRIP_M_MIN_POSITION = 0.0;
    final static double GRIP_M_MAX_POSITION = 1.0;

    double gripDelta = .02;

    double triggerLPosistion = 0.0;
    double triggerRPosition = 1.0;

    @Override
    public void init() {
        Right = hardwareMap.dcMotor.get("motorR");
        Left = hardwareMap.dcMotor.get("motorL");
        Arm = hardwareMap.dcMotor.get("motorA");
        Slide = hardwareMap.dcMotor.get("motorS");

        GripR = hardwareMap.servo.get("GripR");
        GripL = hardwareMap.servo.get("GripL");
        GripM = hardwareMap.servo.get("GripM");
        TriggerL = hardwareMap.servo.get("TriggerL");
        TriggerR = hardwareMap.servo.get("TriggerR");
        Cleaner = hardwareMap.servo.get("Cleaner");

        Right.setDirection(DcMotor.Direction.REVERSE);
        Arm.setDirection(DcMotor.Direction.REVERSE);

        GripL.setDirection(Servo.Direction.FORWARD);
        GripR.setDirection(Servo.Direction.FORWARD);
        GripM.setDirection(Servo.Direction.FORWARD);

    }


    @Override
    public void loop() {

    }

    protected double scaleInput(double dVal)  {

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        return scaleByIndex(index);
    }

    protected double scaleByIndex(int index) {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        // index should be positive.
        int arrayIndex = index;
        if (index < 0) {
            arrayIndex = -index;
        }

        // index cannot exceed size of array minus 1.
        if (arrayIndex > 16) {
            arrayIndex = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (index < 0) {
            dScale = -scaleArray[arrayIndex];
        } else {
            dScale = scaleArray[arrayIndex];
        }

        // return scaled value.
        return dScale;
    }
}
