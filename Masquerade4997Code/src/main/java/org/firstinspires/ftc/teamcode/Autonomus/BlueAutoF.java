package org.firstinspires.ftc.teamcode.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import Library4997.MasqUtilities.Direction;
import Library4997.MasqUtilities.MasqUtils;
import Library4997.MasqWrappers.MasqLinearOpMode;
import SubSystems4997.SubSystems.Flipper;

/**
 * Created by Archish on 1/27/18.
 */
@Autonomous(name = "BlueAutoF", group = "B")
public class BlueAutoF extends MasqLinearOpMode implements Constants {
    public void runLinearOpMode() throws InterruptedException {
        robot.mapHardware(hardwareMap);
        robot.vuforia.initVuforia(hardwareMap);
        robot.initializeAutonomous();
        robot.initializeServos();
        robot.flipper.setPosition(Flipper.Position.MID);
        while (!opModeIsActive()) {
            dash.create(robot.imu);
            dash.update();
        }
        waitForStart();
        robot.sleep(robot.getDelay());
        robot.vuforia.activateVuMark();
        String vuMark = readVuMark();
        runJewel();
        robot.driveTrain.setClosedLoop(false);
        runVuMark(vuMark);
    }
    public void runJewel() {
        robot.jewelArmRed.setPosition(JEWEL_RED_OUT);
        robot.sleep(2000);
        if (robot.jewelColorRed.isRed()) robot.redRotator.setPosition(ROTATOR_RED_NOT_SEEN);
        else robot.redRotator.setPosition(ROTATOR_RED_SEEN);
        robot.sleep(250);
        robot.jewelArmRed.setPosition(JEWEL_RED_IN);
        robot.sleep(100);
    }
    public String readVuMark () {
        robot.waitForVuMark();
        return robot.vuforia.getVuMark();
    }
    public void runVuMark (String vuMark) {
        robot.drive(25, POWER_LOW, Direction.FORWARD);
        robot.drive(23, POWER_OPTIMAL, Direction.BACKWARD);
        robot.drive(5, POWER_OPTIMAL, Direction.FORWARD);
        if (MasqUtils.VuMark.isRight(vuMark)) robot.turn(140, Direction.LEFT);
        else if (MasqUtils.VuMark.isCenter(vuMark)) robot.turn(150, Direction.LEFT);
        else if (MasqUtils.VuMark.isLeft(vuMark)) robot.turn(165, Direction.LEFT);
        else robot.turn(150, Direction.LEFT);
        robot.drive(40, POWER_OPTIMAL, Direction.BACKWARD);
        robot.flipper.setPosition(Flipper.Position.OUT);
        robot.drive(4, POWER_OPTIMAL, Direction.BACKWARD);
        robot.drive(5, POWER_LOW, Direction.FORWARD);
        robot.drive(5, POWER_OPTIMAL, Direction.BACKWARD);
        robot.flipper.setPosition(Flipper.Position.IN);
    }
}