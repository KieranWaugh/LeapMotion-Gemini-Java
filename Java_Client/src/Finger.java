import java.util.ArrayList;
import java.util.List;

public class Finger {
    private List<Bone> bones = new ArrayList<Bone>();
    private int Type;
    private int Id;
    private int HandId;
    private TipPosition TipPosition;
    private Direction Direction;
    private double Width;
    private double Length;
    private Boolean IsExtended;
    private double TimeVisible;
    public List<Bone> getBones() {
        return bones;
    }
    public void setBones(List<Bone> bones) {
        this.bones = bones;
    }
    public int getType() {
        return Type;
    }
    public void setType(int type) {
        this.Type = type;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public int getHandId() {
        return HandId;
    }
    public void setHandId(int handId) {
        this.HandId = handId;
    }
    public TipPosition getTipPosition() {
        return TipPosition;
    }
    public void setTipPosition(TipPosition tipPosition) {
        this.TipPosition = tipPosition;
    }
    public Direction getDirection() {
        return Direction;
    }
    public void setDirection(Direction direction) {
        this.Direction = direction;
    }
    public double getWidth() {
        return Width;
    }
    public void setWidth(double width) {
        this.Width = width;
    }
    public double getLength() {
        return Length;
    }
    public void setLength(double length) {
        this.Length = length;
    }
    public Boolean getIsExtended() {
        return IsExtended;
    }
    public void setIsExtended(Boolean isExtended) {
        this.IsExtended = isExtended;
    }
    public double getTimeVisible() {
        return TimeVisible;
    }
    public void setTimeVisible(double timeVisible) {
        this.TimeVisible = timeVisible;
    }
}
