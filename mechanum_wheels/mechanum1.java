public class MyFIRSTJavaOpMode extends LinearOpMode {
    double forward

    @Override
    public void runOpMode() {
        waitForStart();

        while (opModeIsActive()) {
        }
    }
    public void displayDriveData() {
        telemetry.addData("Forward", forward);
        telemetry.update();
    }
}
