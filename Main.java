import java.awt.*;
import java.applet.*;
import GraphDrawer.GraphDrawer;

/* <applet code="Main.class" width="1400" height="800"></applet> */

public class Main extends Applet {

    // Applet
    private int width, height;

    // グラフ描画用
    private int originX, originY;
    private double scaleX, scaleY, dx, dy;

    /* 初期化 */
    public void init() {
        // Applet
        width = 1400;
        height = 800;

        // グラフ
        originX = 100;
        originY = 670;
        scaleX = 80.0;
        scaleY = 80.0;
        dx = 0.1;
        dy = 1.0;
    }

    /* Applet描画 */
    public void paint(Graphics g) {
        // 背景
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);

        // グラフ
        GraphDrawer gd = new GraphDrawer(originX, originY, scaleX, scaleY, dx, dy);
        gd.draw((Graphics2D)g);
    }

}