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
    double current_x;
    double current_y; 
    double current_z;
    double current_yaw;
    double current_range;

    VisionPortal.Builder myVisionPortalBuilder;
    var myAprilTagDetections;
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

    public void processAndDisplayVisionPortalData(){
        myAprilTagDetections = (myApriltagProcessor.getDetections());
        for (Object myAprilTagDetection2 : myAprilTagDetections) {
          myAprilTagDetection = (AprilTagDetection) myAprilTagDetection2;
          
          current_x = myAprilTagDetection.ftcPose.x;
          current_y = myAprilTagDetection.ftcPose.y;
          current_z = myAprilTagDetection.ftcPose.z;
          current_yaw = myAprilTagDetection.ftcPose.yaw;
          current_range = myAprilTagDetection.ftcPose.range;
          
          telemetry.addData("ID", (myAprilTagDetection.id));
          telemetry.addData("X", current_x);
          telemetry.addData("Y", current_y);
          telemetry.addData("Z", current_z);
          telemetry.addData("Range", current_range);
        }
        telemetry.update();
    }
    
    private void driveToTarget(double targetRange) {
        double rangeError = targetRange - current_range; // Using current_range as the range
        double forwardPower = rangeError;

        telemetry.addData("Current Range", current_range);
        telemetry.addData("Target Range", targetRange);
        telemetry.addData("Range Error", rangeError);
        telemetry.addData("Forward Power", forwardPower);
        telemetry.update();

        if (Math.abs(rangeError) < 3) { // Stop if within threshold
            frontLeftDrive.setPower(0);
            frontRightDrive.setPower(0);
            backLeftDrive.setPower(0);
            backRightDrive.setPower(0);
            return; // Exit the method
        }

        frontLeftDrive.setPower(forwardPower);
        frontRightDrive.setPower(forwardPower);
        backLeftDrive.setPower(forwardPower);
        backRightDrive.setPower(forwardPower);
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
            processAndDisplayVisionPortalData();
            
            if (gamepad1.x) {
                driveToTarget(50);
            }
        }
    }
}