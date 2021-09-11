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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class InternationTimeController implements Initializable {

    int[] neededColumns = { 8, 1, 17, 4, 16 };
    private CasesTable cases = new CasesTable( neededColumns );

    @FXML
    TextField input_solicitante;
    @FXML
    TableView<SolicitanteLineModel> solicitante_table = new TableView<>();
    @FXML
    TableColumn<SolicitanteLineModel, String> Col1 = new TableColumn<>("ID Usuário");
    @FXML
    TableColumn<SolicitanteLineModel, String> Col2  = new TableColumn<>("Descrição CNESExecutante");
    @FXML
    TableColumn<SolicitanteLineModel, String> Col3 = new TableColumn<>("Tempo que passou internado em dias");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Col1.setCellValueFactory( new PropertyValueFactory<>("Col1") );
        Col2.setCellValueFactory( new PropertyValueFactory<>("Col2") );
        Col3.setCellValueFactory( new PropertyValueFactory<>("Col3") );
    }


    private String getDaysBetweenStrings( String start, String end ) {
        LocalDate entrada = LocalDate.parse(start);
        LocalDate saida = LocalDate.parse(end);
        return  Long.toString(ChronoUnit.DAYS.between( entrada, saida ));
    }

    private void addInfos( ArrayList<Integer> list ) {
        ObservableList<SolicitanteLineModel> observableList = FXCollections.observableArrayList();
        for( int i = 0; i < list.size(); i++ ) {
            String col1 = cases.getAllCases().get(list.get(i)).get(1);
            String col2 = cases.getAllCases().get(list.get(i)).get(2);

            String tempoTotal = "Não Teve Alta até o presente momento";

            if( cases.getAllCases().get(list.get(i)).get(4).length() >= 11 ) {
                String sEntrada = cases.getAllCases().get(list.get(i)).get(3);
                String sSaida = cases.getAllCases().get(list.get(i)).get(4).substring( 0, 10);
                tempoTotal =  getDaysBetweenStrings( sEntrada, sSaida ) + " dias entre " + sEntrada + " e " + sSaida ;
            }

            String col3 = tempoTotal;
            SolicitanteLineModel info = new SolicitanteLineModel( col1, col2, col3 );
            observableList.add(info);
        }
        solicitante_table.setItems(observableList);
    }

    private ArrayList<Integer> getLinesContainsHospital() {
        ArrayList<Integer> linesThatHasHospital = new ArrayList<>();
        for( int i = 0 ; i < cases.getAllCases().size(); i++ ) {
            if (cases.getAllCases().get(i).get(0).equals(input_solicitante.getText().toUpperCase())) {
                linesThatHasHospital.add(i);
            }
        }
        return linesThatHasHospital.isEmpty() ? null : linesThatHasHospital;
    }

    public void searchBySolicitante() throws Exception {
        ArrayList<Integer> indexes = getLinesContainsHospital();
        if( indexes != null ) {
            addInfos(indexes);
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
