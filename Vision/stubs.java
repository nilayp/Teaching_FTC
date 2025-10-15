// ftcsim_stubs.java
// Minimal FTC-like stubs so VS Code/Java LSP stops erroring on ftcsim.org code.
// Keep this in the SAME folder as your OpMode file, with no package declaration.

class LinearOpMode {
    public final HardwareMap hardwareMap = new HardwareMap();
    public final Telemetry telemetry = new Telemetry();
    public final Gamepad gamepad1 = new Gamepad();
    public final Gamepad gamepad2 = new Gamepad();

    public void waitForStart() { /* no-op for editor */ }
    public boolean opModeIsActive() { return true; } // return true so loops don't error
    public void sleep(long ms) { /* no-op */ }

    // Signature matches FTC so @Override compiles
    public void runOpMode() throws InterruptedException { /* to be overridden */ }
}

class HardwareMap {
    // Simple generic "get" that returns a new instance of the requested class if possible.
    public <T> T get(Class<T> cls, String name) {
        try {
            return cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null; // good enough for the editor
        }
    }
}

class DcMotor {
    public enum Direction { FORWARD, REVERSE }
    private double power = 0.0;
    private Direction dir = Direction.FORWARD;

    public void setPower(double p) { this.power = p; }
    public double getPower() { return power; }
    public void setDirection(Direction d) { this.dir = d; }
    public Direction getDirection() { return dir; }
}

class Gamepad {
    // Common sticks/buttons referenced by FTC code
    public double left_stick_x = 0.0;
    public double left_stick_y = 0.0;
    public double right_stick_x = 0.0;
    public double right_stick_y = 0.0;

    // Buttons if you ever reference them
    public boolean a, b, x, y;
    public boolean left_bumper, right_bumper;
    public double left_trigger, right_trigger;
    public boolean square, triangle, circle, cross;
}

class Telemetry {
    public Telemetry addData(String caption, Object value) { return this; }
    public Telemetry addData(String caption, double value) { return this; }
    public void update() { /* no-op */ }
}

// --- Additional stubs for ftcsim.org compatibility ---

class Servo {
    public void setPosition(double pos) {}
}

class ColorSensor {}

class DistanceSensor {}

class BNO055IMU {}

class WebcamName {}

// VisionPortal and builder stubs
class VisionPortal {
    public static class Builder {
        public Builder setCamera(Object cam) { return this; }
        public Builder addProcessor(Object proc) { return this; }
        public VisionPortal build() { return new VisionPortal(); }
    }
}

// AprilTagProcessor and builder stubs
class AprilTagProcessor {
    public static class Builder {
        public AprilTagProcessor build() { return new AprilTagProcessor(); }
    }
    public java.util.List<AprilTagDetection> getDetections() { return new java.util.ArrayList<>(); }
}

class AprilTagDetection {
    public int id = 0;
    public FtcPose ftcPose = new FtcPose();
    public static class FtcPose {
        public double range = 0.0;
        public double yaw = 0.0;
    }
}

// Keyboard stub for ftcsim.org
class Keyboard {
    public boolean isPressed(int keyCode) { return false; }
}

// Minimal List stub for ftcsim.org compatibility
class List extends java.util.ArrayList {}