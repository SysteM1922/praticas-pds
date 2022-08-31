package Ex1;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------TodosFazem----------------------------");

        // Create employee
        EmployeeInterface employee1 = new Employee("Gonçalo Silva");
        EmployeeInterface employee2 = new Employee("Guilherme Antunes");

        // test Employee class
        // start()
        employee1.start(new Date());
        employee2.start(new Date());
        // work()
        employee1.work();
        employee2.work();

        // terminate()
        employee2.terminate(new Date());

        System.out.println();
        // test TeamMember class
        TeamMember teamMember = new TeamMember(employee1);
        teamMember.work();
        teamMember.colaborate();

        System.out.println();
        // test TeamLeader class
        TeamLeader teamLeader = new TeamLeader(new Employee("Boss"));
        teamLeader.start(new Date());
        teamLeader.work();
        teamLeader.plan();

        System.out.println();
        // test Manager class
        Manager manager = new Manager(new Employee("BIG BOSS"));
        manager.start(new Date());
        manager.work();
        manager.manage();

        System.out.println();
        // Complex test
        Manager managerTest = new Manager(new TeamLeader(new TeamMember(new Employee("BOSS dos BOSSES"))));
        managerTest.start(new Date());
        managerTest.work();
        managerTest.manage();
        managerTest.terminate(new Date());
    }
}
