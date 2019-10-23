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
        drawGraphBase((Graphics2D)g);
    }

    /* グラフの軸など、基礎となる部分を描画  */
    private void drawGraphBase(Graphics2D g) {
        // 線の太さ, 色
        g.setColor(black);
        g.setStroke(new BasicStroke(3));

        // 軸(x) 700
        g.drawLine(originX-50, originY, originX+700, originY);
        g.drawLine(originX+680, originY-10, originX+700, originY);
        g.drawLine(originX+680, originY+10, originX+700, originY);

        // 軸(y) 500
        g.drawLine(originX, originY+50, originX, originY-500);
        g.drawLine(originX-10, originY-480, originX, originY-500);
        g.drawLine(originX+10, originY-480, originX, originY-500);
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
