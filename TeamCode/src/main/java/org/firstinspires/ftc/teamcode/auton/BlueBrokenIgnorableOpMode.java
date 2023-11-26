package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.utilities.MecanumDrive;

//Blue Left Side
@Autonomous(name="MudasirCantCodeBlueLeft", group="Linear OpMode")
@Config
public class BlueBrokenIgnorableOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initialized: ", "Hopefully");
        telemetry.update();

        final double POWER = 0.85;
        ElapsedTime runtime = new ElapsedTime();
        MecanumDrive drivetrain = new MecanumDrive(hardwareMap);

        waitForStart();
        runtime.reset();

        drivetrain.move(-POWER, -POWER, 0);

        telemetry.addLine("I think I should have moved");
        telemetry.update();

        sleep(1200);

        drivetrain.move(POWER, -POWER, 0);

        sleep(300);
        telemetry.addLine("moved more");
        telemetry.update();


        drivetrain.move(0, 0, 0);
        telemetry.addData("Status", "Run Time: " + runtime);
        telemetry.update();

    }
}
