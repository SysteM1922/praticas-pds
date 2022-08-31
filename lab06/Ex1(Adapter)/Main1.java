import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("\n--------------------------------alínea 1--------------------------------");
        // Sweets
        System.out.println("\n--------------------------------Sweets--------------------------------");
        // Create Database
        Database db = new Database();
        // Create Employee object
        Employee employee1 = new Employee("Gonçalo Silva", 103668, 2500);
        Employee employee2 = new Employee("Guilherme Antunes", 103600, 3500);
        Employee employee3 = new Employee("Daniel Ferreira", 102442, 5000);
        Employee employee4 = new Employee("Pedro Rasinhas", 103541, 3000);

        // add employee
        db.addEmployee(employee1);
        db.addEmployee(employee2);
        db.addEmployee(employee3);
        db.addEmployee(employee4);

        Employee[] list = db.getAllEmployees();

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getName() + " -> " + list[i].getEmpNum() + " -> " + list[i].getSalary());
        }

        db.deleteEmployee(102442);
        db.deleteEmployee(103541);

        list = db.getAllEmployees();
        System.out.println("\nDepois da remoção de Employees\n");

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getName() + " -> " + list[i].getEmpNum() + " -> " + list[i].getSalary());
        }

        // Petiscos
        System.out.println("\n--------------------------------Petiscos--------------------------------");
        Empregado empregado1 = new Empregado("António", "Silva", 1, 1500);
        Empregado empregado2 = new Empregado("Paulo", "Antunes", 2, 1500);
        Empregado empregado3 = new Empregado("João", "Ferreira", 3, 4000);
        Empregado empregado4 = new Empregado("Pedro", "Cavacas", 4, 3000);

        // Create Registos
        Registos registo = new Registos();
        registo.insere(empregado1);
        registo.insere(empregado2);
        registo.insere(empregado3);
        registo.insere(empregado4);

        if (registo.isEmpregado(1))
            System.out.println("Empregado com codigo 1 pertence ao Registo");
        else
            System.out.println("Empregado com codigo 1 não pertence ao Registo");
        if (registo.isEmpregado(3))
            System.out.println("Empregado com codigo 3 pertence ao Registo");
        else
            System.out.println("Empregado com codigo 3 não pertence ao Registo");

        registo.remove(1);

        if (registo.isEmpregado(1))
            System.out.println("Empregado com codigo 1 pertence ao Registo");
        else
            System.out.println("Empregado com codigo 1 não pertence ao Registo");

        List<Empregado> empregados = registo.listaDeEmpregados();

        for (int i = 0; i < empregados.size(); i++) {
            System.out.println(empregados.get(i).nome() + " -> " + empregados.get(i).apelido() + " -> "
                    + empregados.get(i).codigo() + " -> " + empregados.get(i).salario());
        }

        // Adapters and JointBD=Database + Registos
        AdapterInterface dbAdapter = new DatabaseAdapter(db);
        AdapterInterface regAdapter = new RegistosAdapter(registo);

        JointBD jointBd = new JointBD(dbAdapter, regAdapter);

        System.out.println("\n--------------------------------alínea 2--------------------------------");

        System.out.println("\n--------------------------------JointBD--------------------------------");
        System.out.println("Dados da base de dados conjunta:\n");
        jointBd.printAll();
        System.out.println();

        jointBd.addEmpregado(new Empregado("Leo", "DiCaprio", 5, 10000));
        jointBd.addEmpregado(new Empregado("Quentin", "Tarantino", 6, 95000));
        jointBd.addEmpregado(new Empregado("Christopher", "Nolan", 70, 250000));

        jointBd.removeEmpregado(1);
        jointBd.removeEmpregado(14);
        jointBd.removeEmpregado(70);
        System.out.println();

        if (jointBd.isEmpregado(2)) {
            System.out.println("O Empregado com código 2 pertence à jointBd");
        } else
            System.out.println("Oempregado com código 2 não pertence à jointBd");
        if (jointBd.isEmpregado(15)) {
            System.out.println("O Empregado com código 15 pertence à jointBd");
        } else
            System.out.println("O Empregado com código 15 não pertence à jointBd");
        if (jointBd.isEmpregado(70)) {
            System.out.println("O Empregado com código 70 pertence à jointBd");
        } else
            System.out.println("O Empregado com código 70 não pertence à jointBd");

        System.out.println();

        System.out.println("Dados da base de dados conjunta dps de testes:\n");
        jointBd.printAll();

    }
}
