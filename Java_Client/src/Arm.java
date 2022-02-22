public class Arm {
    private PrevJoint PrevJoint;
    private NextJoint NextJoint;
    private Center Center;
    private Direction Direction;
    private double Length;
    private double Width;
    private int Type;
    private Rotation Rotation;

    public PrevJoint getPrevJoint() {
        return PrevJoint;
    }
    public void setPrevJoint(PrevJoint prevJoint) {
        this.PrevJoint = prevJoint;
    }

    public NextJoint getNextJoint() {
        return NextJoint;
    }
    public void setNextJoint(NextJoint nextJoint) {
        this.NextJoint = nextJoint;
    }

    public Center getCenter() {
        return Center;
    }
    public void setCenter(Center center) {
        this.Center = center;
    }

    public Direction getDirection() {
        return Direction;
    }
    public void setDirection(Direction direction) {
        this.Direction = direction;
    }

    public double getLength() {
        return Length;
    }
    public void setLength(double length) {
        this.Length = length;
    }

    public double getWidth() {
        return Width;
    }
    public void setWidth(double width) {
        this.Width = width;
    }

    public int getType() {
        return Type;
    }
    public void setType(int type) {
        this.Type = type;
    }

    public Rotation getRotation() {
        return Rotation;
    }
    public void setRotation(Rotation rotation) {
        this.Rotation = rotation;
    }
}