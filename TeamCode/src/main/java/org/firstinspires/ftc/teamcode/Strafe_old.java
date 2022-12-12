package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Strafe_old")
public class Strafe_old extends LinearOpMode {

    private DcMotor right_front;
    private DcMotor left_front;
    private DcMotor left_back;
    private DcMotor right_back;
    private DcMotor inta1;
    private DcMotor inta2;

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

        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        inta1.setDirection(DcMotorSimple.Direction.REVERSE);
        // You will have to determine which motor to reverse for your robot.
        // In this example, the right motor was reversed so that positive
        // applied power makes it move the robot in the forward direction.
        left_front.setDirection(DcMotorSimple.Direction.REVERSE);
        // Reverse one of the drive motors.
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.
                left_front.setPower(-gamepad1.left_stick_y - (-gamepad1.left_stick_x + -gamepad1.right_stick_x));
                right_front.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x);
                // The Y axis of a joystick ranges from -1 in its topmost position
                // to +1 in its bottommost position. We negate this value so that
                // the topmost position corresponds to maximum forward power.
                left_back.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x);
                right_back.setPower(-gamepad1.left_stick_y - (-gamepad1.left_stick_x - -gamepad1.right_stick_x));
                if (gamepad1.dpad_right) {
                } else if (gamepad1.dpad_left) {
                }
                // Intake
                if (gamepad1.right_bumper) {
                    inta1.setPower(1);
                    inta2.setPower(1);
                } else if (gamepad1.left_bumper) {
                    inta1.setPower(-1);
                    inta2.setPower(-1);
                }else{
                    inta1.setPower(0);
                    inta2.setPower(0);
                }
                telemetry.addData("LF POW", left_front.getPower());
                telemetry.addData("LB POW", left_back.getPower());
                telemetry.addData("RF POW", right_front.getPower());
                telemetry.addData("RB POW", right_back.getPower());
                telemetry.update();
            }
        }
    }
}
