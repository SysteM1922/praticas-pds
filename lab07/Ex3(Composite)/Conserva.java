package Ex3;
public class Conserva extends Products {

    private String name;
    private float pesoProd;

    public Conserva(String name, float pesoProd) {
        this.name = name;
        this.pesoProd = pesoProd;
    }

    @Override
    public float getWeight() {
        return this.pesoProd;
    }

    @Override
    public void draw() {
        String content = "Conserva '" + this.name + "' - Weight: " + this.pesoProd;
        System.out.println(indent.toString() + content);
    }

}
