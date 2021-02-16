import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

// import graph.GraphDrawer;
// import parser.ScriptParser;
import controller.MainUIController;


public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        loader.setController(new MainUIController());
        Scene scene = new Scene(loader.load());

        // Stage
        stage.setTitle("Generic Graph Plotter");
        stage.setScene(scene);
        stage.show();
    }

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