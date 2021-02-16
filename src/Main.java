import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// import GraphDrawer.GraphDrawer;
// import ESParser.ScriptParser;


public class Main extends Application {

    private GraphicsContext g;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();

        Canvas canvas = new Canvas(1400, 800);
        root.getChildren().add(canvas);
        g = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, 1400, 800, Color.WHITE);
        stage.setTitle("Generic Graph Plotter");
        stage.setScene(scene);
        stage.show();
    }

    // グラフ描画用
    /*
    private int originX, originY, min, max;
    private double scaleX, scaleY, dx, dy;
    String script;
    */

    // GUI部品
    /*
    private Scrollbar minScBar, maxScBar, scaleXScBar, dxScBar;
    private TextArea scriptArea, viewSArea, helpVArea;
    private Button editBtn, helpBtn;
    */

    /* コンストラクタ */
    /*
    public Main() {
        originX = 100;
        originY = 700;
        scaleX = 80; // 8.5 ~ 80
        scaleY = 80.0;
        min = 0;
        max = 10;  // 10 ~ 100
        dx = 0.5;
        dy = 1.0;
        script = "var a, b, c, d\na = 1.2\nb = -1.2\nc = 1.2\nd = 0.1\n\nplotd << sin(a*x + b) + cos(c*x + d) + 2";
    }
    */

    /* init : Applet初期化 */
    /*
    public void init() {
        // GUI初期化(min変更バー)
        setLayout(null);
        minScBar = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 100);
        minScBar.setBounds(1050, 70, 310, 20);
        minScBar.addAdjustmentListener(this);
        add(minScBar);

        // GUI初期化(max変更バー)
        maxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 10, 1, 1, 101);
        maxScBar.setBounds(1050, 170, 310, 20);
        maxScBar.addAdjustmentListener(this);
        add(maxScBar);

        // GUI初期化(scaleX変更バー)
        scaleXScBar = new Scrollbar(Scrollbar.HORIZONTAL, 72, 1, 8, 81);
        scaleXScBar.setBounds(1050, 270, 310, 20);
        scaleXScBar.addAdjustmentListener(this);
        add(scaleXScBar);

        // GUI初期化(dx変更バー)
        dxScBar = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1, 1, 101);
        dxScBar.setBounds(1050, 370, 310, 20);
        dxScBar.addAdjustmentListener(this);
        add(dxScBar);

        // スクリプト用TextArea
        scriptArea = new TextArea(script);
        scriptArea.setBounds(200, 170, 650, 500);
        scriptArea.setFont(new Font("Monaco", Font.PLAIN, 30));
        scriptArea.setVisible(false);
        add(scriptArea);

        // 面積表示用TextArea
        viewSArea = new TextArea("");
        viewSArea.setBounds(1050, 470, 310, 220);
        viewSArea.setFont(new Font("Hannotate", Font.PLAIN, 33));
        viewSArea.setEditable(false);
        add(viewSArea);

        // エディタ起動ボタン
        editBtn = new Button("Edit Script");
        editBtn.setBounds(1050, 720, 310, 70);
        editBtn.setFont(new Font("Hannotate", Font.PLAIN, 25));
        editBtn.addActionListener(this);
        add(editBtn);

        // ヘルプボタン
        helpBtn = new Button("?");
        helpBtn.setBounds(740, 30, 30, 30);
        helpBtn.setFont(new Font("Hannotate", Font.PLAIN, 20));
        helpBtn.addActionListener(this);
        add(helpBtn);

        // ヘルプ表示エリア
        helpVArea = new TextArea(getHelp());
        helpVArea.setBounds(130, 100, 800, 650);
        helpVArea.setFont(new Font("Hannotate", Font.PLAIN, 25));
        helpVArea.setEditable(false);
        helpVArea.setVisible(false);
        add(helpVArea);
    }
    */

    /* paint : Applet描画 */
    /*
    public void paint(Graphics g) {
        // 背景
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, getSize().width, getSize().height);

        // スクリプト解析
        ScriptParser sp = new ScriptParser(script);
        try{
            sp.parse();
        } catch(Exception e) {
            script = "";
            repaint();
        }

        // グラフ & 数値積分
        int graphNum = sp.getGraphNum();
        double sList[] = new double[graphNum];
        double xArray[] = makeXArray(min, max, dx), yArray[];
        GraphDrawer gd = new GraphDrawer(originX, originY, scaleX, scaleY, dx, dy);
        for(int idx = 0; idx < graphNum; ++ idx) {
            yArray = sp.calcGraph(idx, xArray);
            sList[idx] = numIntegration(dx, yArray);
            gd.addGraph(idx, xArray, yArray, sp.getVDSetting(idx));
        }
        gd.draw((Graphics2D)g);

        // 面積表示
        String viewSMsg[] = new String[sList.length];
        for(int idx = 0; idx < sList.length; ++ idx)
            viewSMsg[idx] =  String.format("%c : %.4f", 'A'+idx, sList[idx]);
        viewSArea.setText(String.join("\n", viewSMsg));

        // GUI部品の説明
        g.setColor(new Color(0, 0, 0));
        g.drawString("Min : " + min, 1050, 50);
        g.drawString("Max : " + max, 1050, 150);
        g.drawString("Scale : " + scaleX, 1050, 250);
        g.drawString("dx : " + dx, 1050, 350);
        g.drawString("Surface Area", 1050, 450);

        // タイトル
        g.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g.drawString("Generic Graph Plotter", 340, 50);

        // TextAreaの枠
        if(scriptArea.isVisible()) {
            g.setColor(new Color(50, 100, 255));
            g.fillRect(190, 160, 670, 520);
        }
        if(helpVArea.isVisible()) {
            g.setColor(new Color(100, 255, 50));
            g.fillRect(120, 90, 820, 670);
        }
    }
    */

    /* adjustmentValueChanged : GUIイベント受け取り */
    /*
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
        scaleX = Math.min(80.0, scaleX);

        // 値補正
        minScBar.setValue(min);
        maxScBar.setValue(max);
        scaleXScBar.setValue((int)scaleX);
        repaint();
    }
    */

    /* actionPerformed : GUIイベント受取 */
    /*
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
        case "Exec Script":
            editBtn.setLabel("Edit Script");
            scriptArea.setVisible(false);
            script = scriptArea.getText();
            break;

        case "Edit Script":
            editBtn.setLabel("Exec Script");
            scriptArea.setVisible(true);
            break;

        case "?":
            helpVArea.setVisible(!helpVArea.isVisible());
            break;
        }
        repaint();
    }
    */

    /* makeXArray : xの値をとる配列を生成する */
    /*
    private double[] makeXArray(double min, double max, double diff) {
        double xArray[] = new double[(int)((max - min) / diff)+1];
        for(int idx = 0; idx < xArray.length; ++ idx) {
            xArray[idx] = idx * diff + min;
        }
        return xArray;
    }
    */

    /* numIntegration : 数値積分を行いその結果を返す */
    /*
    private double numIntegration(double dx, double yArray[]) {
        double sum = 0;
        for(int idx = 0; idx < yArray.length-1; ++ idx) {
            sum += yArray[idx];
        }
        return sum * dx;
    }
    */

    /* getHelp : ヘルプを返す */
    /*
    private String getHelp() {
        String readme;
        try {
            File f = new File("README.md");
            BufferedReader br = new BufferedReader(new FileReader(f));
            readme = br.lines().collect(Collectors.joining("\n"));
            br.close();
        } catch (IOException e) {
            readme = "";
        }
        return readme;
    }
    */

}