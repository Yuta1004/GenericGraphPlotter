import java.awt.*;
import java.applet.*;

/* <applet code="Main.class" width="1200" height="800"></applet> */

public class Main extends Applet {

    private int width, height;
    private Color white;

    public void init() {
        // for applet
        width = 1200;
        height = 800;
        white = new Color(255, 255, 255);
    }

    public void paint(Graphics g) {
        // background
        g.setColor(white);
        g.drawRect(0, 0, width, height);
    }

}
