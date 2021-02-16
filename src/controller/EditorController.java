package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;

public class EditorController implements Initializable {

    public String script;
    public HashMap<String, String> presets;

    @FXML
    private TextArea scriptTArea;
    @FXML
    private ChoiceBox<String> presetChoice;

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
        presetChoice.getItems().addAll(presets.keySet());
        presetChoice.valueProperty().addListener((ov, old_val, new_val) -> {
            script = presets.get(new_val);
            scriptTArea.setText(script);
        });

        scriptTArea.setText(script);
        scriptTArea.textProperty().addListener((ov, old_val, new_val) -> script = new_val );
    }

}