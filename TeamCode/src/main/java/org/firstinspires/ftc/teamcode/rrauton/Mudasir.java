package org.firstinspires.ftc.teamcode.rrauton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.utilities.Bay;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;
import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.PlaneLauncher;
import org.firstinspires.ftc.teamcode.utilities.SimpleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Stick;
import org.firstinspires.ftc.teamcode.utilities.Arm;

@Autonomous(name="MudasirBlue", group="Autonomous")
public class Mudasir extends LinearOpMode {

    public SampleMecanumDrive drive;
    public Stick stick;
    public InitialVision vision;
    public Arm arm;
    public Intake intake;
    public Bay bay;
    //TODO etc. etc., and add to the createHardware method

    public void createHardware(HardwareMap hmap) {
        drive = new SampleMecanumDrive(hmap);
        stick = new Stick(hmap);
        vision = new InitialVision(hmap, telemetry, "blue"); //TODO: remember to change to blue for blue side
        arm = new Arm(hmap);
        stick = new Stick(hardwareMap);
        intake = new Intake(hardwareMap);
        bay = new Bay(hardwareMap);
    }

    @Override
    public void runOpMode() {
        createHardware(hardwareMap);
        waitForStart();

        //TODO check this value
        Pose2d startPose = new Pose2d(-35.5, 60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        Trajectory toDrop_1 = drive.trajectoryBuilder(new Pose2d())
                .back(10)
                .splineTo(new Vector2d(-38, 24), Math.toRadians(-10))
                .build();


        Trajectory toPaint = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(-35, 5), Math.toRadians(0))
                .back(70)
                .splineTo(new Vector2d(50, 38), Math.toRadians(0))
                .build();

        Trajectory toImaginaryPoint = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(10)
                .forward(5)
                .build();

        Trajectory doSomethingElse = drive.trajectoryBuilder(toImaginaryPoint.end())
                .splineTo(new Vector2d(20, 9), Math.toRadians(45))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        InitialVision vis = new InitialVision(hardwareMap, telemetry, "blue");
        waitForStart();
        int pos = vis.getPosition();

        switch (pos) {
            case 1:
                drive.followTrajectory(toDrop_1);
                dropPixel();
                drive.followTrajectory(toPaint);
                paintPixel();
                returnInit();
                break;
            case 3:

                break;
            case 2:
            default:

                break;
        }

    }
    public void dropPixel() {
        stick.unlock();
        sleep(300);
    }
    public void paintPixel() {
        intake.setZero();
        sleep(200);
        arm.toDrop();
        sleep(2000);
        bay.setDrop();
        sleep(2000);
        bay.open();
        sleep(500);
    }
    public void returnInit() {
        bay.close();
        sleep(200);
        bay.setPosition(1.0);
        sleep(200);
        arm.drivingPos();
        sleep(2000);
        arm.toPickUp();
        sleep(500);
        bay.setPick();
        sleep(200);
    }

}
