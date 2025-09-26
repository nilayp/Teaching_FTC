public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    double forward;
    double turn;
    double strafe;
    double maxDrivePower;
    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;

    @Override
    public void runOpMode() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        maxDrivePower = 1;

        waitForStart();

        while (opModeIsActive()) {
            forward = -gamepad1.left_stick_y;
            turn = -gamepad1.right_stick_x;
            strafe = -gamepad1.left_stick_x;

            forward = forward * maxDrivePower;
            turn = turn * maxDrivePower;
            strafe = strafe * maxDrivePower;

            frontLeftPower = (forward + turn + strafe);
            frontRightPower = (forward - turn - strafe);
            backLeftPower = (forward + turn - strafe);
            backRightPower = (forward - turn + strafe);

            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);
            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);

            telemetry.addData("frontLeftDrive", frontLeftPower);
            telemetry.addData("frontRightDrive", frontRightPower);
            telemetry.addData("backLeftDrive", backLeftPower);
            telemetry.addData("backRightDrive", backRightPower);
            telemetry.addData("forward", forward);
            telemetry.addData("turn", turn);
            telemetry.addData("Strafe", strafe);
            telemetry.update();
        }
    }
}
