/**
 * @author Kieran Waugh
 */

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private int FrameId;
    private int Id;
    private List<Finger> Fingers = new ArrayList<Finger>();
    private PalmPosition PalmPosition;
    private PalmVelocity PalmVelocity;
    private PalmNormal PalmNormal;
    private Direction Direction;
    private Rotation Rotation;
    private double GrabStrength;
    private double GrabAngle;
    private double PinchStrength;
    private double PinchDistance;
    private double PalmWidth;
    private StabilizedPalmPosition StabilizedPalmPosition;
    private WristPosition WristPosition;
    private double TimeVisible;
    private double Confidence;
    private Boolean IsLeft;
    private Arm Arm;

    /**
     * Identifiers for hand type (that is, Left or Right)
     */
    private enum TYPE{
        Right,
        Left
    }


    public int getFrameId() {
        return FrameId;
    }
    public void setFrameId(int frameId) {
        this.FrameId = frameId;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * The list of Finger objects detected in this frame that are attached to this hand, given in order from thumb to pinky.
     * The list cannot be empty.
     * @return The List<Finger> containing all Finger objects attached to this hand.
     */
    public List<Finger> getFingers() {
        return Fingers;
    }
    public void setFingers(List<Finger> fingers) {
        this.Fingers = fingers;
    }

    /**
     * The center position of the palm in millimeters from the Leap Motion Controller origin.
     * @return The Vector representing the coordinates of the palm position.
     */
    public PalmPosition getPalmPosition() {
        return PalmPosition;
    }
    public void setPalmPosition(PalmPosition palmPosition) {
        this.PalmPosition = palmPosition;
    }

    /**
     * The rate of change of the palm position in millimeters/second.
     * @return The Vector representing the coordinates of the palm velocity.
     */
    public PalmVelocity getPalmVelocity() {
        return PalmVelocity;
    }
    public void setPalmVelocity(PalmVelocity palmVelocity) {
        this.PalmVelocity = palmVelocity;
    }

    /**
     * The normal vector to the palm.
     * If your hand is flat, this vector will point downward, or “out” of the front surface of your palm.
     * The direction is expressed as a unit vector pointing in the same direction as the palm normal (that is, a vector orthogonal to the palm).
     * You can use the palm normal vector to compute the roll angle of the palm with respect to the horizontal plane:
     * @return The Vector normal to the plane formed by the palm.
     */
    public PalmNormal getPalmNormal() {
        return PalmNormal;
    }
    public void setPalmNormal(PalmNormal palmNormal) {
        this.PalmNormal = palmNormal;
    }

    /**
     * The direction from the palm position toward the fingers.
     *
     * The direction is expressed as a unit vector pointing in the same direction as the directed line from the palm position to the fingers.
     *
     * You can use the palm direction vector to compute the pitch and yaw angles of the palm with respect to the horizontal plane:
     * @return The Vector pointing from the palm position toward the fingers.
     */
    public Direction getDirection() {
        return Direction;
    }
    public void setDirection(Direction direction) {
        this.Direction = direction;
    }

    /**
     * @return The rotation of the hand as a quaternion.
     */
    public Rotation getRotation() {
        return Rotation;
    }
    public void setRotation(Rotation rotation) {
        this.Rotation = rotation;
    }

    /**
     * The strength of a grab hand pose.
     *
     * The strength is zero for an open hand, and blends to 1.0 when a grabbing hand pose is recognized
     * @return A float value in the [0..1] range representing the holding strength of the pose.
     * @deprecated Use getGrabAngle() instead
     */
    public double getGrabStrength() {
        return GrabStrength;
    }
    public void setGrabStrength(double grabStrength) {
        this.GrabStrength = grabStrength;
    }

    /**
     * The angle between the fingers and the hand of a grab hand pose.
     *
     * The angle is computed by looking at the angle between the direction of the 4 fingers and the direction of the hand. Thumb is not considered when computing the angle. The angle is 0 radian for an open hand, and reaches pi radians when the pose is a tight fist.
     * @return The angle of a grab hand pose between 0 and pi radians (0 and 180 degrees).
     */
    public double getGrabAngle() {
        return GrabAngle;
    }
    public void setGrabAngle(double grabAngle) {
        this.GrabAngle = grabAngle;
    }

    /**
     * The holding strength of a pinch hand pose.
     *
     * The strength is zero for an open hand, and blends to 1.0 when a pinching hand pose is recognized. Pinching can be done between the thumb and any other finger of the same hand.
     * @return A float value in the [0..1] range representing the holding strength of the pinch pose.
     * @deprecated Use getPinchDistance() instead.
     * @since 2.0
     */
    public double getPinchStrength() {
        return PinchStrength;
    }
    public void setPinchStrength(double pinchStrength) {
        this.PinchStrength = pinchStrength;
    }

    /**
     *The distance between the thumb and index finger of a pinch hand pose.
     *
     * The distance is computed by looking at the shortest distance between the last 2 phalanges of the thumb and those of the index finger. This pinch measurement only takes thumb and index finger into account.
     * @return The distance between the thumb and index finger of a pinch hand pose in millimeters.
     */
    public double getPinchDistance() {
        return PinchDistance;
    }
    public void setPinchDistance(double pinchDistance) {
        this.PinchDistance = pinchDistance;
    }

    /**
     * The estimated width of the palm when the hand is in a flat position.
     * @return The width of the palm in millimeters
     */
    public double getPalmWidth() {
        return PalmWidth;
    }
    public void setPalmWidth(double palmWidth) {
        this.PalmWidth = palmWidth;
    }

    /**
     * The stabilized palm position of this Hand.
     *
     * Smoothing and stabilization is performed in order to make this value more suitable for interaction with 2D content. The stabilized position lags behind the palm position by a variable amount, depending primarily on the speed of movement.
     * @return A modified palm position of this Hand object with some additional smoothing and stabilization applied.
     */
    public StabilizedPalmPosition getStabilizedPalmPosition() {
        return StabilizedPalmPosition;
    }
    public void setStabilizedPalmPosition(StabilizedPalmPosition stabilizedPalmPosition) {
        this.StabilizedPalmPosition = stabilizedPalmPosition;
    }

    /**
     * The position of the wrist of this hand.
     * @return A vector containing the coordinates of the wrist position in millimeters.
     */
    public WristPosition getWristPosition() {
        return WristPosition;
    }
    public void setWristPosition(WristPosition wristPosition) {
        this.WristPosition = wristPosition;
    }

    /**
     * The duration of time this Hand has been visible to the Leap Motion Controller.
     * @return The duration (in seconds) that this Hand has been tracked.
     */
    public double getTimeVisible() {
        return TimeVisible;
    }
    public void setTimeVisible(double timeVisible) {
        this.TimeVisible = timeVisible;
    }

    /**
     * How confident we are with a given hand pose.
     * @return confidence level ranges between 0.0 and 1.0 inclusive.
     */
    public double getConfidence() {
        return Confidence;
    }
    public void setConfidence(double confidence) {
        this.Confidence = confidence;
    }

    /**
     * Identifies whether this Hand is a left hand.
     * @return True if the hand is identified as a left hand.
     */
    public Boolean getIsLeft() {
        return IsLeft;
    }
    public void setIsLeft(Boolean isLeft) {
        this.IsLeft = isLeft;
    }

    /**
     * The arm to which this hand is attached.
     *
     * If the arm is not completely in view, Arm attributes are estimated based on the attributes of entities that are in view combined with typical human anatomy.
     * @return The Arm object for this hand.
     */
    public Arm getArm() {
        return Arm;
    }
    public void setArm(Arm arm) {
        this.Arm = arm;
    }

    /**
     * Determines whether this hand is Left or Right
     * @return A Left or Right Type
     */
    public TYPE getType(){
        if(getIsLeft()){
            return TYPE.Left;
        }else{
            return TYPE.Right;
        }
    }
}
