import java.util.List;

public class RegistosAdapter implements AdapterInterface {

    private Registos registos;

    public RegistosAdapter(Registos registos) {
        this.registos = registos;
    }

    @Override
    public void addEmpregado(Empregado empregado) {
        registos.insere(empregado);
    }

    @Override
    public void removeEmpregado(int codigo) {
        registos.remove(codigo);
    }

    @Override
    public boolean isEmpregado(int codigo) {
        boolean flag = registos.isEmpregado(codigo);
        return flag;
    }

    @Override
    public void printAll() {
        List<Empregado> empregados = registos.listaDeEmpregados();

        for (int i = 0; i < empregados.size(); i++) {
            System.out.println(empregados.get(i).nome() + " -> " + empregados.get(i).apelido() + " -> "
                    + empregados.get(i).codigo() + " -> " + empregados.get(i).salario());
        }

    }

}
