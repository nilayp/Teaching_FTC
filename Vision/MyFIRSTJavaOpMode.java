import java.util.List;

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

    VisionPortal.Builder myVisionPortalBuilder;
    List myAprilTagDetections;
    VisionPortal myVisionPortal;
    AprilTagDetection myAprilTagDetection;
    AprilTagProcessor myApriltagProcessor;
    AprilTagProcessor.Builder myAprilTagProcessorBuilder;

    public void initializeVisionPortal(){
        myVisionPortalBuilder = new VisionPortal.Builder();
        myVisionPortal = (myVisionPortalBuilder.build());
        myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "webcam"));
        myAprilTagProcessorBuilder = new AprilTagProcessor.Builder();
        myApriltagProcessor = (myAprilTagProcessorBuilder.build());
        myVisionPortalBuilder.addProcessor(myApriltagProcessor);
    }

    public void displayVisionPortalData(){
        myAprilTagDetections = (myApriltagProcessor.getDetections());
        for (Object myAprilTagDetection2 : myAprilTagDetections) {
          myAprilTagDetection = (AprilTagDetection) myAprilTagDetection2;
          telemetry.addData("ID", (myAprilTagDetection.id));
          telemetry.addData("X", (myAprilTagDetection.ftcPose.x));
          telemetry.addData("Y", (myAprilTagDetection.ftcPose.y));
          telemetry.addData("Z", (myAprilTagDetection.ftcPose.z));
          telemetry.addData("Range", (myAprilTagDetection.ftcPose.range));
        }
        telemetry.update();
    }

      
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

            initializeVisionPortal();
            displayVisionPortalData();
        }
    }
}
