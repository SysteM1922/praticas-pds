package Ex3;

import java.util.*;

public class Caixa extends Products {
    private List<Products> products = new ArrayList<>();
    private String name;
    private float pesoCaixa, pesoTotalCaixa;

    public Caixa(String name, int pesoCaixa) {
        this.name = name;
        this.pesoCaixa = pesoCaixa;
    }

    public void add(Products p) {
        products.add(p);
    }

    public float getWeight() {
        this.pesoTotalCaixa = this.pesoCaixa;

        for (Products prod : products) {
            this.pesoTotalCaixa += prod.getWeight();
        }
        return this.pesoTotalCaixa;
    }

    public void draw() {
        String content = "* Caixa '" + this.name + "' [ Weight: " + this.pesoCaixa + " ; Total: " + getWeight()
                + "]";

        System.out.println(indent.toString() + content);
        indent.append("   ");// three spaces

        for (Products prod : products) {
            prod.draw();
        }

        indent.setLength(indent.length() - 3);
    }

}
