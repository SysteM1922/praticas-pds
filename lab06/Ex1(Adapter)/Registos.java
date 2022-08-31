import java.util.ArrayList;
import java.util.List;

class Registos {
    private ArrayList<Empregado> empregados; // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }

    public void insere(Empregado emp) {
        // Code to insert emplyee
        empregados.add(emp);
    }

    public void remove(int codigo) {
        // Code to remove employee
        for (Empregado empregado : empregados) {
            if (empregado.codigo() == codigo) {
                empregados.remove(empregado);
                break;
            }
        }
    }

    public boolean isEmpregado(int codigo) {
        // Code to find people
        for (Empregado empregado : empregados) {
            if (empregado.codigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public List<Empregado> listaDeEmpregados() {
        // Code to retrieve collection
        return empregados;
    }
}
