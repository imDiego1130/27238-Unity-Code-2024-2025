package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Hardware
{
    /* Public OpMode members. */

    public DcMotor  lF  = null;
    public DcMotor  lB  = null;
    public DcMotor  rF  = null;
    public DcMotor  rB  = null;

    public Servo arm = null;

    /* local OpMode members. */
    HardwareMap hwMap =  null;

    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Hardware(){

    }

    /* Initialize standard Techi_Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Techi_Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        lF = hwMap.get(DcMotor.class, "lF");
        lB = hwMap.get(DcMotor.class, "lB");
        rF = hwMap.get(DcMotor.class, "rF");
        rB = hwMap.get(DcMotor.class, "rB");

        arm = hwMap.get(Servo.class, "arm");

        lF.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        lB.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        rF.setDirection(DcMotor.Direction.FORWARD);
        rB.setDirection(DcMotor.Direction.FORWARD);


        // Set all motors to zero power
        lF.setPower(0);
        lB.setPower(0);
        rF.setPower(0);
        rB.setPower(0);


        // Set all motors to run without encoders.
        lF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
