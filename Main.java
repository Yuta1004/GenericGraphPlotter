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
        script = "var i, t\ni = 0\nloop: i < 5\nt = 4*PI*(i/10)\nplot 2*sin(x+t)+2\ni = i+1\nend";
    }

    /* init : Applet初期化 */
    public void init() {
        // GUI初期化(min変更バー)
        setLayout(null);
        minScBar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 99);
        minScBar.setBounds(1050, 150, 310, 20);
        minScBar.addAdjustmentListener(this);
        add(minScBar);

        // GUI初期化(max変更バー)
        maxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 1, 100);
        maxScBar.setBounds(1050, 250, 310, 20);
        maxScBar.addAdjustmentListener(this);
        add(maxScBar);

        // GUI初期化(scaleX変更バー)
        scaleXScBar = new Scrollbar(Scrollbar.HORIZONTAL, 72, 1, 8, 80);
        scaleXScBar.setBounds(1050, 350, 310, 20);
        scaleXScBar.addAdjustmentListener(this);
        add(scaleXScBar);

        // GUI初期化(dx変更バー)
        dxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1, 1, 100);
        dxScBar.setBounds(1050, 450, 310, 20);
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

        // GUI部品の説明
        g.setColor(new Color(0, 0, 0));
        g.drawString("Min : " + min, 1050, 130);
        g.drawString("Max : " + max, 1050, 230);
        g.drawString("Scale : " + scaleX, 1050, 330);
        g.drawString("dx : " + dx, 1050, 430);
    }

    /* adjustmentValueChanged : GUIイベント受け取り */
    public void adjustmentValueChanged(AdjustmentEvent e) {
        // 値取得
        int oldMax = max;
        min = Math.min(minScBar.getValue(), max-1);
        max = Math.max(maxScBar.getValue(), min+1);
        dx = dxScBar.getValue() / 100.0;
        scaleX = Math.min(scaleXScBar.getValue(), 850/max);
        if(oldMax != max) {
            scaleX = 850/max;
        }

        // 値補正
        minScBar.setValue(min);
        maxScBar.setValue(max);
        scaleXScBar.setValue((int)scaleX);
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