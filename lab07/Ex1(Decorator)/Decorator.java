package Ex1;

import java.util.Date;

public abstract class Decorator implements EmployeeInterface {
    private EmployeeInterface wrappee;

    public Decorator(EmployeeInterface source) {
        this.wrappee = source;
    }

    @Override
    public String getName() {
        return this.wrappee.getName();
    }

    @Override
    public void start(Date date) {
        this.wrappee.start(date);
    }

    @Override
    public void terminate(Date date) {
        this.wrappee.terminate(date);
    }

    @Override
    public void work() {
        this.wrappee.work();
    }

}
