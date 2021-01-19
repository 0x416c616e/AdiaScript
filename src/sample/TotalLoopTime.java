package sample;

//using a class for this is just a workaround for the fact that lambdas don't allow you to use non-final stuff
public class TotalLoopTime {
    private int totalLoopTime;
    public TotalLoopTime() {
        setTotalLoopTime(-1); //-1 means default, and it needs to be changed
    }
    public TotalLoopTime(int totalLoopTime) {
        setTotalLoopTime(totalLoopTime);
    }

    public void setTotalLoopTime(int totalLoopTime) {
        this.totalLoopTime = totalLoopTime;
    }

    public int getTotalLoopTime() {
        return totalLoopTime;
    }
}
