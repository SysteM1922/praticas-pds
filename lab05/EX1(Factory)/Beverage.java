public class Beverage implements Portion {
    private String bevName;
    private State bevState = State.Liquid;
    private Temperature bevTemp;
    private String bevFruit = null; // only used when beverage is FruitJuice

    public Beverage(String bevName, Temperature bevTemp) {
        this.bevName = bevName;
        this.bevTemp = bevTemp;
    }

    public Beverage(String bevName, Temperature bevTemp, String bevFruit) {
        this.bevName = bevName;
        this.bevTemp = bevTemp;
        this.bevFruit = bevFruit;
    }

    public Temperature getTemperature() {

        return bevTemp;
    }

    public State getState() {

        return bevState;
    }

    public String toString() {
        String str;
        if (bevFruit == null) {
            str = bevName + ": Temperature " + bevTemp + ", State " + bevState;
            return str;
        } else {
            str = bevName + ": " + bevFruit + ", Temperature " + bevTemp + ", State " + bevState;
            return str;
        }
    }
}
