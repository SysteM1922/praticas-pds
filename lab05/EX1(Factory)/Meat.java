public class Meat implements Portion {
    private String meatName;
    private State meatState = State.Solid;
    private Temperature meatTemp;

    public Meat(String meatName, Temperature meatTemp) {
        this.meatName = meatName;
        this.meatTemp = meatTemp;
    }

    public Temperature getTemperature() {
        return meatTemp;
    }

    public State getState() {
        return meatState;
    }

    public String toString() {
        String str = meatName + ": Temperature " + meatTemp + ", State " + meatState;
        return str;
    }

}
