package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneLauncher {
    Servo launcher;


    public PlaneLauncher(HardwareMap hmap) {
        this.launcher = hmap.servo.get(CONFIG.clawServo);
    }

    public void release(){
        launcher.setPosition(0.2);
    }

    public void reset(){
        launcher.setPosition(0);
    }
    //servo that goes from one position to another posiiotn (0 to 0.2)
}
