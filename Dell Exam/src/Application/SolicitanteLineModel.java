package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class SolicitanteLineModel {

    @FXML
    SimpleStringProperty Col1, Col2, Col3;

    public SolicitanteLineModel(String paciente, String idade, String municipio_paciente) {
        this.Col1 = new SimpleStringProperty(paciente);
        this.Col2 = new SimpleStringProperty(idade);
        this.Col3 = new SimpleStringProperty(municipio_paciente);
    }

    public String getCol1() {
        return Col1.get();
    }

    public SimpleStringProperty col1Property() {
        return Col1;
    }

    public void setCol1(String col1) {
        this.Col1.set(col1);
    }

    public String getCol2() {
        return Col2.get();
    }

    public SimpleStringProperty col2Property() {
        return Col2;
    }

    public void setCol2(String col2) {
        this.Col2.set(col2);
    }

    public String getCol3() {
        return Col3.get();
    }

    public SimpleStringProperty col3Property() {
        return Col3;
    }

    public void setCol3(String col3) {
        this.Col3.set(col3);
    }
}
