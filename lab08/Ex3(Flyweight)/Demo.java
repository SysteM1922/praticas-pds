import java.awt.Color;

import startypes.*;

public class Demo {
    static int CANVAS_SIZE = 1200;
    static int STARS_TO_DRAW = 1000000;

    public static void main(String[] args) {
        Sky sky = new Sky();

        StarType AStar = new AStar();
        StarType BStar = new BStar();
        StarType FStar = new FStar();
        StarType GStar = new GStar();
        StarType KStar = new KStar();
        StarType MStar = new MStar();
        StarType OStar = new OStar();

        // https://astrobackyard.com/wp-content/uploads/2020/03/types-of-stars.jpg
        StarType[] starTypes = { AStar, BStar, FStar, GStar, KStar, MStar, OStar };
        StarType type;

        Runtime runtime = Runtime.getRuntime();
        long before = runtime.totalMemory() - runtime.freeMemory();

        for (int i = 0; i < STARS_TO_DRAW; i++) {
            type = starTypes[random(0, starTypes.length - 1)];
            sky.placeStar(createStar(type));
        }
        sky.setSize(CANVAS_SIZE, CANVAS_SIZE);
        sky.setBackground(Color.BLACK);
        sky.setVisible(true);

        long after = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory: " + (after - before) / 1024 / 1024 + " MB");

    }

    private static Star createStar(StarType type) {
        int x = random(0, CANVAS_SIZE);
        int y = random(0, CANVAS_SIZE);
        Star star = new Star(x, y, type);

        return star;
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}