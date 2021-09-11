package Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class HospitalLineModel {

    @FXML
    private SimpleStringProperty Column1, Column2, Column3, Column4, Column5, Column6, Column7, Column8;

    public HospitalLineModel(String paciente, String idade, String municipio_paciente, String municipio_solicitante
            , String data_auto, String data_internacao, String data_alta, String hospital ) {
        this.Column1 = new SimpleStringProperty(paciente);
        this.Column2 = new SimpleStringProperty(idade);
        this.Column3 = new SimpleStringProperty(municipio_paciente);
        this.Column4 = new SimpleStringProperty(municipio_solicitante);
        this.Column5 = new SimpleStringProperty(data_auto);
        this.Column6 = new SimpleStringProperty(data_internacao);
        this.Column7 = new SimpleStringProperty(data_alta);
        this.Column8 = new SimpleStringProperty(hospital);
    }

    public String getColumn1() {
        return Column1.get();
    }

    public SimpleStringProperty column1Property() {
        return Column1;
    }

    public void setColumn1(String column1) {
        this.Column1.set(column1);
    }

    public String getColumn2() {
        return Column2.get();
    }

    public SimpleStringProperty column2Property() {
        return Column2;
    }

    public void setColumn2(String column2) {
        this.Column2.set(column2);
    }

    public String getColumn3() {
        return Column3.get();
    }

    public SimpleStringProperty column3Property() {
        return Column3;
    }

    public void setColumn3(String column3) {
        this.Column3.set(column3);
    }

    public String getColumn4() {
        return Column4.get();
    }

    public SimpleStringProperty column4Property() {
        return Column4;
    }

    public void setColumn4(String column4) {
        this.Column4.set(column4);
    }

    public String getColumn5() {
        return Column5.get();
    }

    public SimpleStringProperty column5Property() {
        return Column5;
    }

    public void setColumn5(String column5) {
        this.Column5.set(column5);
    }

    public String getColumn6() {
        return Column6.get();
    }

    public SimpleStringProperty column6Property() {
        return Column6;
    }

    public void setColumn6(String column6) {
        this.Column6.set(column6);
    }

    public String getColumn7() {
        return Column7.get();
    }

    public SimpleStringProperty column7Property() {
        return Column7;
    }

    public void setColumn7(String column7) {
        this.Column7.set(column7);
    }

    public String getColumn8() {
        return Column8.get();
    }

    public SimpleStringProperty column8Property() {
        return Column8;
    }

    public void setColumn8(String column8) {
        this.Column8.set(column8);
    }
}
