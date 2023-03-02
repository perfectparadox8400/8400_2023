package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Strafe")
public class Strafe extends LinearOpMode {

    private DcMotor right_front;
    private DcMotor left_front;
    private DcMotor left_back;
    private DcMotor right_back;
    private DcMotor inta1;
    private DcMotor inta2;
    private DcMotor slide;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        right_front = hardwareMap.get(DcMotor.class, "right_front");
        left_front = hardwareMap.get(DcMotor.class, "left_front");
        left_back = hardwareMap.get(DcMotor.class, "left_back");
        right_back = hardwareMap.get(DcMotor.class, "right_back");
        inta1 = hardwareMap.get(DcMotor.class, "inta1");
        inta2 = hardwareMap.get(DcMotor.class, "inta2");
        slide = hardwareMap.get(DcMotor.class, "slide");

        right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        inta1.setDirection(DcMotorSimple.Direction.REVERSE);
        left_front.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialize encoders
        right_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Set target encoder positions and power for each motor
                left_front.setTargetPosition((int) (-gamepad1.left_stick_y - (-gamepad1.left_stick_x + -gamepad1.right_stick_x)));
                left_front.setPower(-gamepad1.left_stick_y - (-gamepad1.left_stick_x + -gamepad1.right_stick_x));
                right_front.setTargetPosition((int) (-gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
                right_front.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x);
                left_back.setTargetPosition((int) (-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x));
                left_back.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x);
                right_back.setTargetPosition((int) (-gamepad1.left_stick_y - (-gamepad1.left_stick_x - -gamepad1.right_stick_x)));
                right_back.setPower(-gamepad1.left_stick_y - (-gamepad1.left_stick_x - -gamepad1.right_stick_x));

                right_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                if (gamepad1.dpad_up) {
                    slide.setPower(1);
                } else if (gamepad1.dpad_down) {
                    slide.setPower(-1);
                } else{
                    slide.setPower(0);
                }

                // Intake
                if (gamepad1.right_bumper) {
                    inta1.setPower(1);
                    inta2.setPower(1);
                } else if (gamepad1.left_bumper) {
                    inta1.setPower(-1);
                    inta2.setPower(-1);
                } else {
                    inta1.setPower(0);
                    inta2.setPower(0);
                }
                telemetry.addData("LF Encoder", left_front.getCurrentPosition());
                telemetry.addData("LB Encoder", left_back.getCurrentPosition());
                telemetry.addData("RF Encoder", right_front.getCurrentPosition());
                telemetry.addData("RB Encoder", right_back.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}