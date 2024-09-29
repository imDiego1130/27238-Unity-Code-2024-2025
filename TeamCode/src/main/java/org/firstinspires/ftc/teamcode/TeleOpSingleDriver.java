package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.util.Range.scale;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static com.qualcomm.robotcore.util.Range.scale;
import static java.lang.Math.abs;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp Single Driver", group="TeleOp")
public class TeleOpSingleDriver extends OpMode {
    /* Declare OpMode members. */
    Hardware robot = new Hardware();
double MAX_SPEED = 1;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.rB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.rF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.lB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.lF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hey Driver");

    }
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }
    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @SuppressLint("SuspiciousIndentation")
    @Override
    public void loop() {
        if (gamepad1.left_stick_button) {
            MAX_SPEED = .35;
        }
        if (gamepad1.right_stick_button) {
            MAX_SPEED = .7;
        }
        double Speed = -gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double Strafe = -gamepad1.left_stick_x;
        holonomic(Speed, Turn, Strafe, MAX_SPEED);
        telemetry.addData("MAX Speed", "%.2f", MAX_SPEED);

    }

    // Update telemetry or perform other actions as needed
       //Ex. telemetry.addData("LIFT", robot.liftL.getCurrentPosition());
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
    public void holonomic(double Speed, double Turn, double Strafe, double MAX_SPEED) {
//      Left Front = +Speed + Turn - Strafe      Right Front = +Speed - Turn + Strafe
//      Left Rear  = +Speed + Turn + Strafe      Right Rear  = +Speed - Turn - Strafe
        double Magnitude = abs(Speed) + abs(Turn) + abs(Strafe);
        Magnitude = (Magnitude > 1) ? Magnitude : 1; //Set scaling to keep -1,+1 range

        robot.lF.setPower(scale((Speed + Turn - Strafe),
                -Magnitude, +Magnitude, -MAX_SPEED, +MAX_SPEED));

        if (robot.lB != null) {
            robot.lB.setPower(scale((Speed + Turn + Strafe),
                    -Magnitude, +Magnitude, -MAX_SPEED, +MAX_SPEED));
        }
        robot.rF.setPower(scale((Speed - Turn + Strafe),
                -Magnitude, +Magnitude, -MAX_SPEED, +MAX_SPEED));
        if (robot.rB != null) {
            robot.rB.setPower(scale((Speed - Turn - Strafe),
                    -Magnitude, +Magnitude, -MAX_SPEED, +MAX_SPEED));
        }
    }
}
