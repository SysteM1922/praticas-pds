public class Container {
    public static ContainerType create(Portion menu) {
        State state = menu.getState();
        Temperature temperature = menu.getTemperature();

        if (state.equals(State.Solid)) {
            if (temperature.equals(Temperature.WARM)) {
                return new ContainerType("Tupperware", state, temperature, menu);
            } else if (temperature.equals(Temperature.COLD)) {
                return new ContainerType("PlasticBag", state, temperature, menu);
            }
        } else if (state.equals(State.Liquid)) {
            if (temperature.equals(Temperature.WARM)) {
                return new ContainerType("TermicBottle", state, temperature, menu);
            } else if (temperature.equals(Temperature.COLD)) {
                return new ContainerType("PlasticBottle", state, temperature, menu);
            }
        }
        return null;
    }
}
