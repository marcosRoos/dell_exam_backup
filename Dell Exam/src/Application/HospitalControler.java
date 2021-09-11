package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HospitalControler implements Initializable {

    int[] neededColumns = { 17, 1, 6, 7, 9, 14, 15, 16 };
    private CasesTable cases = new CasesTable( neededColumns );

    @FXML
    TextField input_executante;
    @FXML
    TableView<HospitalLineModel> hospital_table = new TableView<>();
    @FXML
    TableColumn<HospitalLineModel, String> Column1 = new TableColumn<>("ID Usuário");
    @FXML
    TableColumn<HospitalLineModel, String> Column2  = new TableColumn<>("Idade");
    @FXML
    TableColumn<HospitalLineModel, String> Column3 = new TableColumn<>("Município Paciente");
    @FXML
    TableColumn<HospitalLineModel, String> Column4 = new TableColumn<>("Nome MunicípioSolicitante");
    @FXML
    TableColumn<HospitalLineModel, String> Column5 = new TableColumn<>("Data hora autorização");
    @FXML
    TableColumn<HospitalLineModel, String> Column6 = new TableColumn<>("Data internação");
    @FXML
    TableColumn<HospitalLineModel, String> Column7 = new TableColumn<>("Data alta");
    @FXML
    TableColumn<HospitalLineModel, String> Column8 = new TableColumn<>("Descrição CNES Executante");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Column1.setCellValueFactory( new PropertyValueFactory<>("Column1") );
        Column2.setCellValueFactory( new PropertyValueFactory<>("Column2") );
        Column3.setCellValueFactory( new PropertyValueFactory<>("Column3") );
        Column4.setCellValueFactory( new PropertyValueFactory<>("Column4") );
        Column5.setCellValueFactory( new PropertyValueFactory<>("Column5") );
        Column6.setCellValueFactory( new PropertyValueFactory<>("Column6") );
        Column7.setCellValueFactory( new PropertyValueFactory<>("Column7") );
        Column8.setCellValueFactory( new PropertyValueFactory<>("Column8") );
    }

    private void addInfos( ArrayList<Integer> list ) {
        ObservableList<HospitalLineModel> observableList = FXCollections.observableArrayList();
        for( int i = 0; i < list.size(); i++ ) {
            String col1 = cases.getAllCases().get(list.get(i)).get(1) ;
            int age = (int)Double.parseDouble(cases.getAllCases().get(list.get(i)).get(2));
            String col2 = Integer.toString(age);
            String col3 = cases.getAllCases().get(list.get(i)).get(3);
            String col4 = cases.getAllCases().get(list.get(i)).get(4);
            String col5 = cases.getAllCases().get(list.get(i)).get(5);
            String col6 = cases.getAllCases().get(list.get(i)).get(6);
            String col7 = cases.getAllCases().get(list.get(i)).get(7);
            String col8 = cases.getAllCases().get(list.get(i)).get(0);

            HospitalLineModel info = new HospitalLineModel( col1, col2, col3, col4, col5, col6, col7, col8 );
            observableList.add(info);
        }
        hospital_table.setItems(observableList);
    }

    private ArrayList<Integer> getLinesContainsHospital() {
        ArrayList<Integer> linesThatHasHospital = new ArrayList<>();
        for( int i = 0 ; i < cases.getAllCases().size(); i++ ) {
            if (cases.getAllCases().get(i).get(0).equals( input_executante.getText().toUpperCase() ) ) {
                linesThatHasHospital.add(i);
            }
        }
        return linesThatHasHospital.isEmpty() ? null : linesThatHasHospital;
    }

    public void searchByHospital() throws Exception {
        ArrayList<Integer> indexes = getLinesContainsHospital();
        if( indexes != null ) {
            addInfos( indexes );
        } else {
            Stage stage = new Stage();
            FXMLLoader fxmloader = new FXMLLoader();
            Parent root = fxmloader.load(getClass().getResource("erroDigitacao.fxml"));
            stage.setTitle("404");
            Scene home = new Scene(root, 500, 300);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(home);
            stage.show();
        }
    }

    // Abrir Página incial
    public void open_Home(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader();
        Parent root = fxmloader.load(getClass().getResource("home.fxml"));
        stage.setTitle("DELL - Internações");
        Scene home = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene(home);
        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }


}
