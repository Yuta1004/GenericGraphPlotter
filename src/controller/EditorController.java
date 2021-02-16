package controller;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class EditorController implements Initializable {

    public String script;
    public HashMap<String, String> presets;

    @FXML
    private TextArea scriptTArea;
    @FXML
    private ChoiceBox<String> presetChoice;
    @FXML
    private Button loadBtn, doneBtn;

    public EditorController() {
        presets = new HashMap<String, String>();
        presets.put("Default", "var a, b, c, d\na = 1.2\nb = -1.2\nc = 1.2\nd = 0.1\n\nplotd << sin(a*x + b) + cos(c*x + d) + 2");
        presets.put("Constant1", "plot << 2 ");
        presets.put("Constant2", "plotd << 2");
        presets.put("SinWave1", "var theta, bias\ntheta = 1\nbias = 1\n\nplot << sin(theta*x) + bias");
        presets.put("SinWave2", "var theta, bias\ntheta = 1\nbias = 1\n\nplotd << sin(theta*x) + bias");
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        loadBtn.setOnAction(event -> {
            URL scriptPath = getFilePath();
            if(scriptPath == null) return;

            script += "12044021 ";
            try {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(scriptPath.openStream()));
                while((line = br.readLine()) != null) {
                    script += line+"\n";
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
            script = script.split("12044021")[1];
            scriptTArea.setText(script);
        });

        doneBtn.setOnAction(event -> doneBtn.getScene().getWindow().hide() );

        presetChoice.getItems().addAll(presets.keySet());
        presetChoice.valueProperty().addListener((ov, old_val, new_val) -> {
            script = presets.get(new_val);
            scriptTArea.setText(script);
        });

        scriptTArea.setText(script);
        scriptTArea.textProperty().addListener((ov, old_val, new_val) -> script = new_val );
    }

    private URL getFilePath() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select script file (*.es or *.txt)");
        chooser.getExtensionFilters().add(new ExtensionFilter("DataFile", "*.es", "*.txt"));

        File file = chooser.showOpenDialog((Stage)loadBtn.getScene().getWindow());
        try {
            return file == null ? new URL("file:///null") : file.toURI().toURL();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}