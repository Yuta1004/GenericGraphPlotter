package graph;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

class GraphPlotter {

    private int originX, originY;
    private double scaleX, scaleY, stroke;
    private double xArray[], yArray[];
    private Color color;
    private boolean viewDetail;

    /* コンストラクタ */
    public GraphPlotter(int oX, int oY, double sX, double sY) {
        originX = oX;
        originY = oY;
        scaleX = sX;
        scaleY = sY;
        stroke = 1.0;
        color = Color.rgb(255, 100, 100);
        viewDetail = false;
    }

    /* setColor(Color c) : 色指定(Colorインスタンス) */
    public void setColor(Color c) {
        color = c;
    }

    /* setColor(int r, int g, int b) : 色指定(RGB) */
    public void setColor(int r, int g, int b) {
        color = Color.rgb(r, g, b);
    }

    /* setStroke : 線の太さ指定 */
    public void setStroke(double s) {
        stroke = s;
    }

    /* setViewDetail : 数値積分の可視化を行うかどうか指定する */
    public void setViewDetail(boolean b) {
        viewDetail = b;
    }

    /* setGraph : グラフ設定 */
    public void setGraph(double xArray[], double yArray[]) {
        this.xArray = xArray;
        this.yArray = yArray;
    }

    /* plot : グラフ描画 */
    public void plot(GraphicsContext g) {
        int x0, y0, x1, y1;
        int size = Math.min(xArray.length, yArray.length);

        for(int idx = 0; idx < size-1; ++ idx) {
            // グラフ
            x0 = g2aX(xArray[idx]);
            y0 = g2aY(yArray[idx]);
            x1 = g2aX(xArray[idx+1]);
            y1 = g2aY(yArray[idx+1]);
            g.setLineWidth(stroke);
            g.setFill(color);
            g.strokeLine(x0, y0, x1, y1);

            // 数値積分可視化
            if(viewDetail) {
                g.setLineWidth(2);
                g.setStroke(Color.BLACK);
                g.rect(x0, y0, x1-x0, originY-y0);
                g.setLineWidth(1);
                g.setFill(Color.GRAY);
                g.fillRect(x0, y0, x1-x0, originY-y0);
            }
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