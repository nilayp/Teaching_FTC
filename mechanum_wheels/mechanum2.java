public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    double forward, maxDrivePower;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        maxDrivePower = 1;

        waitForStart();

        while (opModeIsActive()) {
            forward = gamepad1.left_stick_y;
            backLeftDrive.setPower(forward);
            backRightDrive.setPower(forward);
            displayDriveData();

            /* turn = turn * maxDrivePower;
            forward = forward * maxDrivePower;
            strafe = strafe * maxDrivePower;
            // Combine inputs to create drive and turn (or both!)
            frontLeftDrive.setPower(forward + turn + strafe);
            frontRightDrive.setPower(forward - turn - strafe);
            backLeftDrive.setPower(forward + turn - strafe);
            backRightDrive.setPower(forward - turn + strafe);
            */
        }
    }
    public void displayDriveData() {
        telemetry.addData("Forward", forward);
        telemetry.update();
    }
}
