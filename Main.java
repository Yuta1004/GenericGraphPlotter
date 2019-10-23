import java.awt.*;
import java.applet.*;

/* <applet code="Main.class" width="1400" height="800"></applet> */

public class Main extends Applet {

    // Applet用
    private int width, height;
    private Color white, black, gray;
    private Font font, boldFont;

    // グラフ描画用
    private int originX, originY;
    private double scaleX, scaleY, dx, dy;

    /* Applet初期化 */
    public void init() {
        // Applet
        width = 1400;
        height = 800;
        white = new Color(255, 255, 255);
        black = new Color(0, 0, 0);
        gray = new Color(100, 100, 100);
        font = new Font("TimesRoman", Font.PLAIN, 30);
        boldFont = new Font("TimesRomas", Font.BOLD, 30);

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

        // 原点
        g.drawString("o", originX-50, originY+50);

        // 軸(x) 700
        g.drawLine(originX-50, originY, originX+900, originY);
        g.drawLine(originX+880, originY-10, originX+900, originY);
        g.drawLine(originX+880, originY+10, originX+900, originY);
        g.drawString("x", originX+900, originY+70);

        // 軸(y) 500
        g.drawLine(originX, originY+50, originX, originY-600);
        g.drawLine(originX-10, originY-580, originX, originY-600);
        g.drawLine(originX+10, originY-580, originX, originY-600);
        g.drawString("y", originX-70, originY-600);

        // 補助線
        int x, y, size;
        g.setStroke(new BasicStroke(2));
        g.setFont(font);

        // 補助線(x)
        for(int idx = 1; idx*dx*scaleX <= 870; ++ idx) {
            // 線
            x = (int)(idx*dx*scaleX) + originX;
            size = (idx % 5) == 0 ? 15 : 10;
            size = (idx % 10) == 0 ? 20 : size;
            g.drawLine(x, originY-size, x, originY+size);

            // 数
            if(size == 20) {
                g.drawString(String.valueOf((int)(idx*dx)), x-10, originY+50);
            }
        }

        // 補助線(y)
        for(int idx = 1; idx*dy*scaleY <= 570; ++ idx) {
            // 短線
            y = originY - (int)(idx*dy*scaleY);
            size = (idx % 5) == 0 ? 15 : 10;
            size = (idx % 10) == 0 ? 20 : size;
            g.setColor(black);
            g.setStroke(new BasicStroke(2));
            g.drawLine(originX-size, y, originX+size, y);
            g.drawString(String.valueOf((int)(idx*dy)), originX-50, y+12);

            // 長線
            g.setColor(gray);
            g.setStroke(new BasicStroke(1));
            g.drawLine(originX, y, originX+900, y);
        }
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
