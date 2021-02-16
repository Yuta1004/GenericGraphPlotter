package graph;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphDrawer {

    private Color colors[];
    private int originX, originY;
    private double scaleX, scaleY;
    private HashMap<Integer, GraphPlotter> graph;

    /* コンストラクタ */
    public GraphDrawer(int oX, int oY, double sX, double sY) {
        colors = new Color[5];
        colors[0] = Color.rgb(255, 93, 93);
        colors[1] = Color.rgb(207, 95, 255);
        colors[2] = Color.rgb(93, 126, 255);
        colors[3] = Color.rgb(59, 173, 77);
        colors[4] = Color.rgb(163, 185, 0);

        // グラフ
        graph = new HashMap<Integer, GraphPlotter>();
        originX = oX;
        originY = oY;
        scaleX = sX;
        scaleY = sY;
    }

    /* addGraph : 描画グラフ追加 */
    public void addGraph(int gID, double xArray[], double yArray[], boolean viewDetail) {
        GraphPlotter gp = new GraphPlotter(originX, originY, scaleX, scaleY);
        gp.setGraph(xArray, yArray);
        gp.setViewDetail(viewDetail);
        graph.put(gID, gp);
    }

    /* draw : 描画 */
    public void draw(GraphicsContext g) {
        drawBase(g);
        int idx = 0;
        for(int gID: graph.keySet()) {
            GraphPlotter gp = graph.get(gID);
            gp.setColor(colors[(idx++)%5]);
            gp.setStroke(5);
            gp.plot(g);
        }
    }

    /* drawBase : 描画(ベース) */
    private void drawBase(GraphicsContext g) {
        // 線の太さ, 色
        g.setStroke(Color.BLACK);
        g.setLineWidth(3.0);

        // 原点
        g.strokeText("0", originX-50, originY+50);

        // 軸(x) 700
        g.strokeLine(originX-50, originY, originX+900, originY);
        g.strokeLine(originX+880, originY-10, originX+900, originY);
        g.strokeLine(originX+880, originY+10, originX+900, originY);
        g.strokeText("x", originX+900, originY+70);

        // 軸(y) 500
        g.strokeLine(originX, originY+50, originX, originY-600);
        g.strokeLine(originX-10, originY-580, originX, originY-600);
        g.strokeLine(originX+10, originY-580, originX, originY-600);
        g.strokeText("y", originX-70, originY-600);

        // 補助線(x)
        g.setLineWidth(2);
        for(int x = 1; x*scaleX <= 870; ++ x) {
            int drawX = (int)(x*scaleX) + originX;
            int size = helperLineSize(x);
            g.strokeLine(drawX, originY-size, drawX, originY+size);
            if(x % 10 == 0)
                g.strokeText(String.valueOf(x), drawX-10, originY+50);
        }

        // 補助線(y)
        for(int y = 1; y*scaleY <= 570; ++ y) {
            int drawY = originY - (int)(y*scaleY);
            int size = helperLineSize(y);
            g.setStroke(Color.BLACK);
            g.setLineWidth(2);
            g.strokeLine(originX-size, drawY, originX+size, drawY);
            g.setStroke(Color.GRAY);
            g.setLineWidth(1);
            g.strokeLine(originX, drawY, originX+900, drawY);
            g.strokeText(String.valueOf(y), originX-50, drawY+12);
        }

        // 凡例(2段組)
        int idx = 0;
        for(int gID: graph.keySet()) {
            int x = (originX+650) + idx%2*140;
            int y = (originY-520) + idx/2*50;
            g.setStroke(colors[idx%5]);
            g.setLineWidth(5);
            g.strokeLine(x, y, x+50, y);
            g.strokeText(String.valueOf((char)('A'+gID)), x+70, y+10);
            ++ idx;
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