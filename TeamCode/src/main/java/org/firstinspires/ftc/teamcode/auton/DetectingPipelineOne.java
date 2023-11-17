package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utilities.Claw;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;
import org.firstinspires.ftc.teamcode.utilities.Lift;
import org.firstinspires.ftc.teamcode.utilities.MecanumDrive;

//blue Right Side
@Autonomous(name="VisionRedMaybe", group="Linear OpMode")
@Config
public class DetectingPipelineOne extends LinearOpMode {
    //constant assumptions for power and time for movement
    public static final double POWER = 0.6;
    public static final long FOOT = 500;
    public static final long TURN_45 = 300;

    MecanumDrive drivetrain;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized: ", "Hopefully");
        telemetry.update();

        InitialVision vision = new InitialVision(hardwareMap, telemetry, "blue");
        ElapsedTime runtime = new ElapsedTime();
        Lift lift = new Lift(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        claw.close();
        //drivetrain.move() y is forward x is left
        drivetrain = new MecanumDrive(hardwareMap);

        waitForStart();
        runtime.reset();

        // raise the lift and scan the spikes for the prop
        telemetry.addLine("Scanning...");
        telemetry.update();
        lift.toScan();
        sleep(FOOT/2);
        int place = vision.getPosition();
        telemetry.addData("Position: ", place);
        telemetry.update();

        // move to the correct spike, place the prop, and park
        switch (place) {
            case 1: // left spike
                drivetrain.move(0, POWER, 0);
                sleep(FOOT*2);
                stopDrive();
                drivetrain.move(0, 0, POWER);
                sleep(TURN_45*2);
                stopDrive();
                claw.open();
                sleep(300);
                drivetrain.move(-POWER, 0, 0);
                sleep((long) (FOOT*1.5));
                stopDrive();
                drivetrain.move(0, POWER, 0);
                sleep((long) (FOOT*3.5));
                stopDrive();
                break;
            case 2: //middle spike
                drivetrain.move(0, POWER, 0);
                sleep(FOOT*2);
                stopDrive();
                claw.open();
                sleep(300);
                drivetrain.move(0, POWER, 0);
                sleep((long) (FOOT*1.5));
                stopDrive();
                drivetrain.move(0, 0, POWER);
                sleep(TURN_45*2);
                stopDrive();
            default: // right spike (or we somehow dont know)

        }


        stopDrive();
        telemetry.addData("Run Time:", runtime);
        telemetry.update();
    }

    public void stopDrive() {
        drivetrain.move(0, 0, 0);
        sleep(100);
    }

}