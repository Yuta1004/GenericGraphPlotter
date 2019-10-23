import java.awt.*;
import java.applet.*;

/* <applet code="Main.class" width="1400" height="800"></applet> */

public class Main extends Applet {

    // Applet用
    private int width, height;
    private Color white, black;
    private Font font, boldFont;

    // グラフ描画用
    private int originX, originY;
    private double scaleX, scaleY;

    /* Applet初期化 */
    public void init() {
        // Applet
        width = 1400;
        height = 800;
        white = new Color(255, 255, 255);
        black = new Color(0, 0, 0);
        font = new Font("TimesRoman", Font.PLAIN, 30);
        boldFont = new Font("TimesRomas", Font.BOLD, 30);

        // グラフ
        originX = 100;
        originY = 670;
        scaleX = 60.0;
        scaleY = 40.0;
    }

    /* Applet描画 */
    public void paint(Graphics g) {
        // background
        g.setColor(white);
        g.fillRect(0, 0, width, height);
        drawGraphBase((Graphics2D)g);
    }

    /* グラフの軸など、基礎となる部分を描画  */
    private void drawGraphBase(Graphics2D g) {
        // 線の太さ, 色
        g.setFont(boldFont);
        g.setColor(black);
        g.setStroke(new BasicStroke(3));

        // 軸(x) 700
        g.drawLine(originX-50, originY, originX+900, originY);
        g.drawLine(originX+880, originY-10, originX+900, originY);
        g.drawLine(originX+880, originY+10, originX+900, originY);
        g.drawString("x", originX+450, originY+70);

        // 軸(y) 500
        g.drawLine(originX, originY+50, originX, originY-600);
        g.drawLine(originX-10, originY-580, originX, originY-600);
        g.drawLine(originX+10, originY-580, originX, originY-600);
        g.drawString("y", originX-70, originY-300);
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
