package GraphDrawer;

import java.awt.*;
import java.applet.*;

class GraphPlotter {

    private int originX, originY, stroke;
    private double scaleX, scaleY;
    private double xArray[], yArray[];
    private Color color;

    /* コンストラクタ */
    public GraphPlotter(int oX, int oY, double sX, double sY) {
        originX = oX;
        originY = oY;
        scaleX = sX;
        scaleY = sY;
        color = new Color(255, 100, 100);
    }

    /* setColor(Color c) : 色指定(Colorインスタンス) */
    public void setColor(Color c) {
        color = c;
    }

    /* setColor(int r, int g, int b) : 色指定(RGB) */
    public void setColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    /* setStroke : 線の太さ指定 */
    public void setStroke(int s) {
        stroke = s;
    }

    /* setGraph : グラフ設定 */
    public void setGraph(double xArray[], double yArray[]) {
        this.xArray = xArray;
        this.yArray = yArray;
    }

    /* plot : グラフ描画 */
    public void plot(Graphics2D g) {
        int x0, y0, x1, y1;
        int size = Math.min(xArray.length, yArray.length);
        g.setStroke(new BasicStroke(stroke));
        g.setColor(color);

        for(int idx = 0; idx < size-1; ++ idx) {
            x0 = g2aX(xArray[idx]);
            y0 = g2aY(yArray[idx]);
            x1 = g2aX(xArray[idx+1]);
            y1 = g2aY(yArray[idx+1]);
            g.drawLine(x0, y0, x1, y1);
        }
    }

    /* グラフ上の座標を描画用の座標に変換する(x) */
    private int g2aX(double x) {
        return originX + (int)(x*scaleX);
    }

    /* グラフ上の座標を描画用の座標に変換する(y) */
    private int g2aY(double y) {
        return originY - (int)(y*scaleY);
    }
}