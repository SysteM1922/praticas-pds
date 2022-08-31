public class ContainerType extends Container {
    String type;
    State state;
    Temperature temperature;
    Portion menu;

    public ContainerType(String type, State state, Temperature temperature, Portion menu) {
        this.type = type;
        this.state = state;
        this.temperature = temperature;
        this.menu = menu;
    }

    public String toString() {
        String str = type + " with portion = " + menu.toString();
        return str;
    }
}
