public class JointBD {
    private AdapterInterface registos;
    private AdapterInterface database;

    public JointBD(AdapterInterface registos, AdapterInterface database) {
        this.registos = registos;
        this.database = database;
    }

    public void addEmpregado(Empregado empregado) {
        boolean exists = isEmpregado(empregado.codigo());

        if (exists) {
            System.out.println("codigo:" + empregado.codigo() + " já existe");
        } else {
            registos.addEmpregado(empregado);
        }

    }

    public void removeEmpregado(int codigo) {
        boolean exists = isEmpregado(codigo);

        if (exists) {
            if (registos.isEmpregado(codigo)) {
                registos.removeEmpregado(codigo);
            } else {
                database.removeEmpregado(codigo);
            }
        } else {
            System.out.println("codigo:" + codigo + " não existe");
        }
    }

    public void printAll() {
        registos.printAll();
        database.printAll();
    }

    public boolean isEmpregado(int codigo) {
        boolean existsreg = registos.isEmpregado(codigo);

        boolean existsdb = database.isEmpregado(codigo);
        return existsreg || existsdb;
    }
}
