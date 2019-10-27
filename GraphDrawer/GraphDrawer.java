package GraphDrawer;

import java.awt.*;
import java.applet.*;
import java.util.ArrayList;

public class GraphDrawer {

    // Applet用
    private Color white, black, gray;
    private Color colors[];
    private Font font, boldFont;

    // グラフ用
    private int originX, originY;
    private double scaleX, scaleY, dx, dy;
    private ArrayList<GraphPlotter> graph;

    /* コンストラクタ */
    public GraphDrawer(int oX, int oY, double sX, double sY, double dx, double dy) {
        // Applet(Color)
        colors = new Color[5];
        colors[0] = new Color(255, 93, 93);
        colors[1] = new Color(207, 95, 255);
        colors[2] = new Color(93, 126, 255);
        colors[3] = new Color(59, 173, 77);
        colors[4] = new Color(163, 185, 0);
        white = new Color(255, 255, 255);
        black = new Color(0, 0, 0);
        gray = new Color(100, 100, 100);

        // Applet(Font)
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

    /* addGraph : 描画グラフ追加 */
    public void addGraph(double xArray[], double yArray[], boolean viewDetail) {
        GraphPlotter gp = new GraphPlotter(originX, originY, scaleX, scaleY);
        gp.setGraph(xArray, yArray);
        gp.setViewDetail(viewDetail);
        graph.add(gp);
    }

    /* draw : 描画 */
    public void draw(Graphics2D g) {
        drawBase(g);
        int idx = 0;
        for(GraphPlotter gp: graph) {
            gp.setColor(colors[(idx++)%5]);
            gp.setStroke(5);
            gp.plot(g);
        }
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
        g.setStroke(new BasicStroke(2));
        g.setFont(font);

        // 補助線(x)
        for(int x = 1; x*scaleX <= 870; ++ x) {
            int drawX = (int)(x*scaleX) + originX;
            int size = helperLineSize(x);
            g.drawLine(drawX, originY-size, drawX, originY+size);
            if(x % 10 == 0)
                g.drawString(String.valueOf(x), drawX-10, originY+50);
        }

        // 補助線(y)
        for(int y = 1; y*scaleY <= 570; ++ y) {
            int drawY = originY - (int)(y*scaleY);
            int size = helperLineSize(y);
            g.setColor(black);
            g.setStroke(new BasicStroke(2));
            g.drawLine(originX-size, drawY, originX+size, drawY);
            g.setColor(gray);
            g.setStroke(new BasicStroke(1));
            g.drawLine(originX, drawY, 900, drawY);
            g.drawString(String.valueOf(y), originX-50, drawY+12);
        }
    }

    /* helperLineSize : 目盛りの値に応じて補助線の長さを返す */
    private int helperLineSize(int x) {
        if(x % 10 == 0) {
            return 20;
        } else if(x % 5 == 0) {
            return 15;
        }
        return 10;
    }
}