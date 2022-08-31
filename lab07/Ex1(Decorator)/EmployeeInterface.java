package Ex1;
import java.util.Date;

public interface EmployeeInterface {
    public void start(Date date);

    public void terminate(Date date);

    public void work();

    // added by me
    public String getName();
}