package Ex3;
public class Doce extends Products {

    private String name;
    private float pesoProd;

    public Doce(String name, float pesoProd) {
        this.name = name;
        this.pesoProd = pesoProd;
    }

    @Override
    public float getWeight() {
        return this.pesoProd;
    }

    @Override
    public void draw() {
        String content = "Doce '" + this.name + "' - Weight: " + this.pesoProd;
        System.out.println(indent.toString() + content);
    }

}
