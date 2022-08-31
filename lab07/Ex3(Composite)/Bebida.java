package Ex3;
public class Bebida extends Products {

    private String name;
    private float pesoProd;

    public Bebida(String name, float pesoProd) {
        this.name = name;
        this.pesoProd = pesoProd;
    }

    @Override
    public float getWeight() {
        return this.pesoProd;
    }

    @Override
    public void draw() {
        String content = "Bebida '" + this.name + "' - Weight: " + this.pesoProd;
        System.out.println(indent.toString() + content);
    }

}
