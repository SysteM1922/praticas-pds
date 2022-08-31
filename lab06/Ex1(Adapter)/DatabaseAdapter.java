public class DatabaseAdapter implements AdapterInterface {

    private Database database;

    public DatabaseAdapter(Database database) {
        this.database = database;
    }

    @Override
    public void addEmpregado(Empregado empregado) {
        Employee emp = new Employee(empregado.nome() + " " + empregado.apelido(), (long) empregado.codigo(),
                empregado.salario());

        database.addEmployee(emp);
    }

    @Override
    public void removeEmpregado(int codigo) {
        database.deleteEmployee(codigo);

    }

    @Override
    public boolean isEmpregado(int codigo) {
        Employee[] list = database.getAllEmployees();
        for (Employee e : list) {
            if (e.getEmpNum() == codigo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printAll() {

        Employee[] employees = database.getAllEmployees();

        for (int i = 0; i < employees.length; i++) {
            System.out.println(
                    employees[i].getName() + " -> " + employees[i].getEmpNum() + " -> " + employees[i].getSalary());
        }
    }

}
