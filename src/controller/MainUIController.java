package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollBar;
import javafx.scene.text.Text;

import parser.ScriptParser;
import graph.GraphDrawer;

public class MainUIController implements Initializable {

    public final int WIDTH = 1400;
    public final int HEIGHT = 800;

    private GraphicsContext g;
    private int originX, originY, min, max;
    private double scaleX, scaleY, dx;
    private String script;

    @FXML
    private Canvas canvas;
    @FXML
    private TextArea surfaceView;
    @FXML
    private ScrollBar minSc, maxSc, dxSc;
    @FXML
    private Text minText, maxText, dxText;

    public MainUIController() {
        originX = 100;
        originY = 700;
        scaleX = 85; // 8.5 ~ 100
        scaleY = 80.0;
        min = 0;
        max = 10;  // 10 ~ 100
        dx = 0.5;
        script = "var a, b, c, d\na = 1.2\nb = -1.2\nc = 1.2\nd = 0.1\n\nplotd << sin(a*x + b) + cos(c*x + d) + 2";
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        minSc.valueProperty().addListener((ov, old_val, new_val) -> {
            min = Math.min(new_val.intValue(), max-1);
            minSc.setValue(min);
            minText.setText("Min: "+min);
            draw();
        });

        maxSc.valueProperty().addListener((ov, old_val, new_val) -> {
            max = Math.max(new_val.intValue(), min+1);
            scaleX = 850/max;
            maxSc.setValue(max);
            draw();
        });

        g = canvas.getGraphicsContext2D();
        draw();
    }

    public void draw() {
        // 背景
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // スクリプト解析
        ScriptParser sp = new ScriptParser(script);
        try{
            sp.parse();
        } catch(Exception e) {
            script = "";
            draw();
            return;
        }

        // グラフ & 数値積分
        int graphNum = sp.getGraphNum();
        double sList[] = new double[graphNum];
        double xArray[] = makeXArray(min, max, dx), yArray[];
        GraphDrawer gd = new GraphDrawer(originX, originY, scaleX, scaleY);
        for(int idx = 0; idx < graphNum; ++ idx) {
            yArray = sp.calcGraph(idx, xArray);
            sList[idx] = Arrays.stream(yArray).sum() * dx;
            gd.addGraph(idx, xArray, yArray, sp.getVDSetting(idx));
        }
        gd.draw(g);

        // 面積表示
        String viewSMsg[] = new String[sList.length];
        for(int idx = 0; idx < sList.length; ++ idx)
            viewSMsg[idx] =  String.format("%c : %.4f", 'A'+idx, sList[idx]);
        surfaceView.setText(String.join("\n", viewSMsg));
    }

    private double[] makeXArray(double min, double max, double diff) {
        double xArray[] = new double[(int)((max - min) / diff)+1];
        for(int idx = 0; idx < xArray.length; ++ idx) {
            xArray[idx] = idx * diff + min;
        }
        return xArray;
    }
}