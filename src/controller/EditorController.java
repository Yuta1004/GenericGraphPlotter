package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class EditorController implements Initializable {

    public String script;

    @FXML
    private TextArea scriptTArea;

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        scriptTArea.setText(script);
        scriptTArea.textProperty().addListener((ov, old_val, new_val) -> script = new_val );
    }

}