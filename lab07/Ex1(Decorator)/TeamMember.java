package Ex1;
import java.util.Date;

public class TeamMember extends Decorator {

    public TeamMember(EmployeeInterface source) {
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

    public void colaborate() {
        System.out.println(super.getName() + " is a TeamMember and colaborates in the project");
    }

}
