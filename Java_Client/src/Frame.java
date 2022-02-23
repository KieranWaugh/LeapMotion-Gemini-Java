import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int Id;
    private long Timestamp;
    private double CurrentFramesPerSecond;
    private List<Hand> Hands = new ArrayList<Hand>();


    /**
     * A unique ID for this Frame.
     *
     * Consecutive frames processed by the Leap Motion software have consecutive increasing values. You can use the frame ID to avoid processing the same Frame object twice:
     * @return The frame ID.
     */
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * The frame capture time in microseconds elapsed since an arbitrary point in time in the past.
     * @return The timestamp in microseconds.
     */
    public long getTimestamp() {
        return Timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.Timestamp = timestamp;
    }

    /**
     * The instantaneous framerate.
     *
     * The rate at which the Leap Motion software is providing frames of data (in frames per second). The framerate can fluctuate depending on available computing resources, activity within the device field of view, software tracking settings, and other factors.
     * @return An estimate of frames per second of the Leap Motion
     */
    public double getCurrentFramesPerSecond() {
        return CurrentFramesPerSecond;
    }
    public void setCurrentFramesPerSecond(double currentFramesPerSecond) {
        this.CurrentFramesPerSecond = currentFramesPerSecond;
    }

    /**
     * The list of Hand objects detected in this frame, given in arbitrary order.
     *
     * The list can be empty if no hands are detected.
     * @return The List<Hand> containing all Hand objects detected in this frame.
     */
    public List<Hand> getHands() {
        return Hands;
    }
    public void setHands(List<Hand> hands) {
        this.Hands = hands;
    }

    /**
     * The member of the list that is farthest to the front within the standard Leap Motion frame of reference (i.e has the smallest Z coordinate).
     * @return The frontmost hand, or invalid if list is empty.
     */
    public Hand getFrontmost(){

        if(Hands.size() == 1){
            return Hands.get(0);
        }

        if((Hands.get(0).getPalmPosition().getZ()) <= (Hands.get(1).getPalmPosition().getZ())){
            return Hands.get(0);
        }else{
            return Hands.get(1);
        }
    }
}
