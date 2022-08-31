package lab03;
import java.lang.Comparable;
public class voo implements Comparable{
    private String codigo;
    private voo(String codigo)
    {
        this.codigo = codigo;
    }
    public static voo criarVoo(String codigo)
    {
        return new voo(codigo);
    }
    @Override
    // metodo para comparar dois voo
    public int compareTo(Object a)
    {
        int va = 0;
        int vb = 0;
        String codigoA = ((voo)a).getCode();
        for (int i = 0;i < codigo.length();i++){
            va += codigoA.charAt(i);
        }
        for (int i = 0;i < this.getCode().length();i++){
            vb += this.getCode().charAt(i);
        }
        return vb - va;
    }
    public String getCode() {
        return this.codigo;
    }

    
    
}
