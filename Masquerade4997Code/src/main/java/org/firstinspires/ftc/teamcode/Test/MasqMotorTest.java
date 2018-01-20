package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Autonomus.Constants;
import Library4997.MasqExternal.Direction;
import Library4997.MasqWrappers.MasqLinearOpMode;

/**
 * Created by Archish on 11/24/17.
 */
@TeleOp(name = "MasqMotorTest", group = "Autonomus")
public class MasqMotorTest extends MasqLinearOpMode implements Constants {
    public void runLinearOpMode() throws InterruptedException {
        robot.mapHardware(hardwareMap);
        while (!opModeIsActive()) {
            dash.create("Distance: ", Double.toString(robot.ultra.getDistance()));
            dash.update();
        }
        waitForStart();
        robot.stop(robot.ultra, POWER_LOW, Direction.FORWARD);
    }
}