package GraphDrawer;

import java.awt.*;
import java.applet.*;
import java.util.ArrayList;

public class GraphDrawer {

    // Applet用
    private Color white, black, gray;
    private Font font, boldFont;

    // グラフ用
    private int originX, originY;
    private double scaleX, scaleY, dx, dy;
    private ArrayList<GraphPlotter> graph;

    /* コンストラクタ */
    public GraphDrawer(int oX, int oY, double sX, double sY, double dx, double dy) {
        // Applet
        white = new Color(255, 255, 255);
        black = new Color(0, 0, 0);
        gray = new Color(100, 100, 100);
        font = new Font("TimesRoman", Font.PLAIN, 30);
        boldFont = new Font("TimesRomas", Font.BOLD, 30);

        // グラフ
        graph = new ArrayList<GraphPlotter>();
        originX = oX;
        originY = oY;
        scaleX = sX;
        scaleY = sY;
        this.dx = dx;
        this.dy = dy;
    }

    /* draw : 描画 */
    public void draw(Graphics2D g) {
        drawBase(g);
        for(GraphPlotter gp: graph) {
            gp.plot(g);
        }
    }

    /* addGraph : 描画グラフ追加 */
    public void addGraph(double xArray[], double yArray[]) {
        GraphPlotter gp = new GraphPlotter(originX, originY, scaleX, scaleY);
        gp.setGraph(xArray, yArray);
        graph.add(gp);
    }

    /* drawBase : 描画(ベース) */
    private void drawBase(Graphics2D g) {
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
            size = helperLineSize(idx, dx);
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
            size = helperLineSize(idx, dy);
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

    /* helperLineSize : 目盛りの値に応じて補助線の長さを返す */
    private int helperLineSize(int idx, double diff) {
        int judgeS = Math.max((int)(0.1 / diff), 1);
        int judgeM = Math.max((int)(0.5 / diff), 1);
        int judgeL = (int)(1.0 / diff);

        if(idx % judgeL == 0) {
            return 20;
        } else if(idx % judgeM == 0) {
            return 15;
        } else if(idx % judgeS == 0) {
            return 10;
        }
        return 0;
    }
}