package Ex1;
import java.util.Date;

public class Employee implements EmployeeInterface {

    private String name;
    private Date startDate, endDate;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void start(Date date) {
        this.startDate = date;
        System.out.println("Employee named " + name + " started working on " + startDate.toString());
    }

    @Override
    public void terminate(Date date) {
        this.endDate = date;
        System.out.println("Employee named " + name + " stopped working on " + endDate.toString());
    }

    @Override
    public void work() {
        System.out.println(name + " is working on the project");
    }

}
