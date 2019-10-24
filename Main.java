import java.awt.*;
import java.applet.*;
import GraphDrawer.GraphDrawer;
import ESParser.ScriptParser;

/* <applet code="Main.class" width="1400" height="800"></applet> */

public class Main extends Applet {

    // Applet
    private int width, height;

    // グラフ描画用
    private int originX, originY, min, max;
    private double scaleX, scaleY, dx, dy;
    String script;

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
        min = 0;
        max = 10;
        dx = 0.05;
        dy = 1.0;
        script = "var a, b, c, d\na = 1.2\nb = -1.2\nc = 1.2\nd = 0.1\nplot sin(a*x + b) + cos(c*x + d) + 2";
        // script = "var i, t\ni = 0\nloop: i < 10\nt = 2*PI*(i/10)\nplot 2*sin(x+t)+2\ni = i+1\nend";
    }

    /* Applet描画 */
    public void paint(Graphics g) {
        // 背景
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);

        // スクリプト解析
        ScriptParser sp = new ScriptParser(script);
        sp.parse();

        // グラフ
        double xArray[] = makeXArray(min, max, dx);
        GraphDrawer gd = new GraphDrawer(originX, originY, scaleX, scaleY, dx, dy);
        for(int idx = 0; idx < sp.getGraphNum(); ++ idx) {
            gd.addGraph(xArray, sp.calcGraph(idx, xArray));
        }
        gd.draw((Graphics2D)g);
    }

    /* makeXArray : xの値をとる配列を生成する */
    private double[] makeXArray(double min, double max, double diff) {
        double xArray[] = new double[(int)((max - min) / diff)+1];
        for(int idx = 0; idx < xArray.length; ++ idx) {
            xArray[idx] = idx * diff + min;
        }
        return xArray;
    }

}