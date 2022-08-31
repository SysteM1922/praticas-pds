import startypes.StarType;
import java.awt.*;

public class Star {
    private int x;
    private int y;
    private StarType type;

    public Star(int x, int y, StarType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        g.setColor(this.type.getColor());
        g.fillOval(this.x, this.y, this.type.getSize(), this.type.getSize());
    }
}
