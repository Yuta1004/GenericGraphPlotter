import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import GraphDrawer.GraphDrawer;
import ESParser.ScriptParser;

/* <applet code="Main.class" width="1400" height="800"></applet> */

public class Main extends Applet implements AdjustmentListener {

    // グラフ描画用
    private int originX, originY, min, max;
    private double scaleX, scaleY, dx, dy;
    String script;

    // GUI部品
    private Scrollbar minScBar, maxScBar, scaleXScBar, dxScBar;

    /* コンストラクタ */
    public Main() {
        originX = 100;
        originY = 670;
        scaleX = 80; // 8.5 ~ 80
        scaleY = 80.0;
        min = 0;
        max = 10;  // 10 ~ 100
        dx = 0.05;
        dy = 1.0;
        script = "var a, b, c, d\na = 1.2\nb = -1.2\nc = 1.2\nd = 0.1\nplot sin(a*x + b) + cos(c*x + d) + 2";
    }

    /* init : Applet初期化 */
    public void init() {
        // GUI初期化(min変更バー)
        setLayout(null);
        minScBar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 99);
        minScBar.setBounds(1100, 150, 250, 20);
        minScBar.addAdjustmentListener(this);
        add(minScBar);

        // GUI初期化(max変更バー)
        maxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 1, 100);
        maxScBar.setBounds(1100, 250, 250, 20);
        maxScBar.addAdjustmentListener(this);
        add(maxScBar);

        // GUI初期化(scaleX変更バー)
        scaleXScBar = new Scrollbar(Scrollbar.HORIZONTAL, 72, 1, 1, 72);
        scaleXScBar.setBounds(1100, 350, 250, 20);
        scaleXScBar.addAdjustmentListener(this);
        add(scaleXScBar);

        // GUI初期化(dx変更バー)
        dxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1, 1, 100);
        dxScBar.setBounds(1100, 450, 250, 20);
        dxScBar.addAdjustmentListener(this);
        add(dxScBar);
    }

    /* paint : Applet描画 */
    public void paint(Graphics g) {
        // 背景
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, getSize().width, getSize().height);

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

    /* adjustmentValueChanged : GUIイベント受け取り */
    public void adjustmentValueChanged(AdjustmentEvent e) {
        min = minScBar.getValue();
        max = maxScBar.getValue();
        scaleX = scaleXScBar.getValue() + 7.5;
        dx = dxScBar.getValue() / 100.0;
        System.out.println(min + " " + max + " " + scaleX + " " + dx);
        repaint();
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