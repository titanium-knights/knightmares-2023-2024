package org.firstinspires.ftc.teamcode.auton.cycler;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auton.AutonMethods;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;

@Autonomous(name="RedRight-DropScorePark", group="Linear OpMode")
@Config
public class RedRight extends AutonMethods {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        telemetry.addData("Initialized: ", "Hopefully");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();

        // setting up
        plane.reset();
        stick.lock();
        intake.setUpUp();
        arm.drivingPos();
        bay.setPick();

        InitialVision vis = new InitialVision(hardwareMap, telemetry, "blue");
        waitForStart();
        runtime.reset();
        int pos = vis.getPosition();

        switch (pos) {
            case 1:
                caseOne();
                turnClockwise();
                turnClockwise();
                backTwo();
                leftOneEighth();
                paintPixel();
                returnInit();
                break;
            case 3:
                caseThree();
                rightOne();
                backTwo();
                leftOne();
                leftHalf();
                paintPixel();
                returnInit();
                break;
            case 2:
            default:
                caseTwo();
                turnClockwise();
                backTwo();
                paintPixel();
                returnInit();
                break;
        }

        telemetry.addData("Run Time:", runtime);
        telemetry.update();
    }
}
