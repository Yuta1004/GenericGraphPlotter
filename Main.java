import java.awt.*;
import java.applet.*;

/* <applet code="Main.class" width="1200" height="800"></applet> */

public class Main extends Applet {

    // Applet用
    private int width, height;
    private Color white, black;

    // グラフ描画用
    private int originX, originY;
    private double scaleX, scaleY;

    /* Applet初期化 */
    public void init() {
        // Applet
        width = 1200;
        height = 800;
        white = new Color(255, 255, 255);
        black = new Color(0, 0, 0);

        // グラフ
        originX = 100;
        originY = 600;
        scaleX = 60.0;
        scaleY = 40.0;
    }

    /* Applet描画 */
    public void paint(Graphics g) {
        // background
        g.setColor(white);
        g.drawRect(0, 0, width, height);
        drawGraphBase(g);
    }

    /* グラフの軸など、基礎となる部分を描画  */
    private void drawGraphBase(Graphics g) {
        g.setColor(black);
    }

    /* グラフ上の座標を描画用の座標に変換する(x) */
    private int g2aX(double x) {
        return (int)(originX + x * scaleX);
    }

    /* グラフ上の座標を描画用の座標に変換する(y) */
    private int g2aY(double y) {
        return (int)(originY - y  * scaleY);
    }
}
