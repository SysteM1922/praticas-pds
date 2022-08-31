package Ex1;
import java.util.Date;

public class Manager extends Decorator {

    public Manager(EmployeeInterface source) {
        super(source);
    }

    @Override
    public void start(Date date) {
        super.start(date);
    }

    @Override
    public void terminate(Date date) {
        super.terminate(date);
    }

    @Override
    public void work() {
        super.work();
    }

    public void manage() {
        System.out.println(super.getName() + " is a Manager and is the boss of the project");
    }

}
