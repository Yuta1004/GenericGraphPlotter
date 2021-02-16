package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Button;
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
    @FXML
    private Button editorUpBtn;

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
            maxText.setText("Max: "+max);
            draw();
        });

        dxSc.valueProperty().addListener((ov, old_val, new_val) -> {
            dx = ((int)(new_val.doubleValue()*100))/100.0;
            dxText.setText("dx: "+dx);
            draw();
        });

        editorUpBtn.setOnAction(event -> {
            EditorController controller = new EditorController();
            controller.script = script;
            genStage("Script Editor", "/fxml/Editor.fxml", controller).showAndWait();
            script = controller.script;
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
            sList[idx] = integrate(xArray, yArray, dx);
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
        int length = (int)((max-min)/diff)+1;
        length += (length-1)*diff+min == max ? 0 : 1;

        double xArray[] = new double[length];
        for(int idx = 0; idx < length-1; ++ idx) {
            xArray[idx] = idx * diff + min;
        }
        xArray[length-1] = max;

        return xArray;
    }

    private double integrate(double[] xArray, double[] yArray, double diff) {
        int length = xArray.length;
        double result = 0;
        for(int idx = 0; idx < length-2; ++ idx) {
            result += yArray[idx] * diff;
        }
        result += yArray[yArray.length-2] * (xArray[length-1]-xArray[length-2]);
        return result;
    }

    private <T> Stage genStage(String title, String fxmlPath, T controller) {
        // FXML読み込み
        Scene scene = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if(controller != null)
                loader.setController(controller);
            scene = new Scene(loader.load());
        } catch (Exception e){ e.printStackTrace(); return null; }

        // ダイアログ立ち上げ
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }
}