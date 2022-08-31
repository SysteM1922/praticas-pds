import java.util.Vector;

class Database { // Data Elements
    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public void deleteEmployee(long emp_num) {
        // Code to delete employee
        for (Employee employee : employees) {
            if (employee.getEmpNum() == emp_num) {
                employees.remove(employee);
                break;
            }
        }
    }

    public Employee[] getAllEmployees() {
        // Code to retrieve collection
        Employee[] allEmployees = employees.toArray(new Employee[0]);
        return allEmployees;
    }

}