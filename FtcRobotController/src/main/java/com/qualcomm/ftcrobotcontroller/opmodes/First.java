package com.qualcomm.ftcrobotcontroller.opmodes.mainCode;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by RyanZhu on 10/20/2015.
 */
public class First extends MechaTechOp {

    @Override
    public void init() {
        super.init();
        GripM.setPosition(0.5);
        Cleaner.setPosition(0.9);
        GripR.setPosition(gripRPosition);
        GripL.setPosition(gripLPosition);
        TriggerR.setPosition(triggerRPosition);
        TriggerL.setPosition(triggerLPosistion);
    }

    @Override
    public void loop() {

        try {

            // Left motor ("motorL") moves. GamePad1 Left Joystick controls
            float leftY = -gamepad1.left_stick_y;
            leftY = (float)scaleInput((double)leftY);
            Left.setPower(leftY);

            // Right motor ("motorR") moves. GamePad1 Right Joystick controls
            float rightY = -gamepad1.right_stick_y;
            rightY = (float)scaleInput((double)rightY);
            Right.setPower(rightY);

            // Arm motor ("motorA") moves. GamePad2 Left Joystick controls
            float armY = -gamepad2.left_stick_y;
            armY = Range.clip(armY, (float)-0.3,(float) 0.3);
            Arm.setPower(armY);

            // Slider motor ("motorS") moves. GamePad2 Right Joystick controls.
            float slideY = -gamepad2.right_stick_y;
            slideY = Range.clip(slideY, (float)-0.15,(float) 0.15);
            Slide.setPower(slideY);

            // Left Trigger Bar ("TriggerL"). GamePad1 x/y control
            if(gamepad2.x)
            {
                if (triggerLPosistion > 0.0 ) {
                    triggerLPosistion -= gripDelta;
                }
                gamepad2.x = false;
            }
            if(gamepad2.y)
            {
                if ( triggerLPosistion < 1.0 ) {
                    triggerLPosistion += gripDelta;
                }
                gamepad2.y = false;
            }
            TriggerL.setPosition(Range.clip(triggerLPosistion, 0.0, 1.0));

            // Right Trigger Bar ("TriggerR") moves. GamePad1 a/b control
            if(gamepad2.a)
            {
                if (triggerRPosition > 0.0 ) {
                    triggerRPosition -= gripDelta;
                }
                gamepad2.a = false;
            }
            if(gamepad2.b)
            {
                if (triggerRPosition < 1.0 ) {
                    triggerRPosition += gripDelta;
                }
                gamepad2.b = false;
            }
            TriggerR.setPosition(Range.clip(triggerRPosition, 0.0, 1.0));

            // Cleaner ("Cleaner") moves. GamePad1 left/right bumper
            if(gamepad1.left_bumper)
            {
                Cleaner.setPosition(0.02);
                gamepad1.left_bumper = false;
            }
            if(gamepad1.right_bumper)
            {
                Cleaner.setPosition(0.9);
                gamepad1.right_bumper = false;
            }

            // Grip Main ("GripM") moves. GamePad2 left/right bumper control
            if(gamepad2.left_bumper) {
                gripMPosition = 0.51;//+= gripDelta;
                gripMPosition = Range.clip(gripMPosition, GRIP_M_MIN_POSITION, GRIP_M_MAX_POSITION);
                GripM.setPosition(gripMPosition);
                gamepad2.left_bumper = false;
            }
            if(gamepad2.right_bumper) {
                gripMPosition = 0.0; //-= gripDelta;
                gripMPosition = Range.clip(gripMPosition, GRIP_M_MIN_POSITION, GRIP_M_MAX_POSITION);
                GripM.setPosition(gripMPosition);
                gamepad2.right_bumper = false;
            }


            // Grips ("GripL", "GripR"). GamePad1 dpad left/right control.
            if(gamepad2.dpad_right) {
                gripLPosition += gripDelta;
                gripRPosition -= gripDelta;
                gamepad2.dpad_right = false;
            }
            if(gamepad2.dpad_left) {
                gripLPosition -= gripDelta;
                gripRPosition += gripDelta;
                gamepad2.dpad_left = false;
            }
            gripLPosition = Range.clip(gripLPosition, GRIP_L_MIN_POSITION, GRIP_L_MAX_POSITION);
            gripRPosition = Range.clip(gripRPosition, GRIP_R_MIN_POSITION, GRIP_R_MAX_POSITION);
            GripL.setPosition(gripLPosition);
            GripR.setPosition(gripRPosition);
        }
        catch (Exception ex )
        {
            telemetry.addData("Error", "" + ex.getMessage());
            telemetry.addData("Message", "Restart Robot. ");
        }
    }

}
