package Application;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class errorControler {

    public void closeError (ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }

}
