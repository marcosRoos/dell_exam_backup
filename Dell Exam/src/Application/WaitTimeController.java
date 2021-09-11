package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitTimeController implements Initializable {

    int[] neededColumns = { 18 };
    private CasesTable cases = new CasesTable( neededColumns );

    @FXML
    CategoryAxis x_waitingTime = new CategoryAxis();
    @FXML
    NumberAxis y_waitingTime = new NumberAxis();
    @FXML
    BarChart<String, Number> chart_waitingTime = new BarChart<String, Number>(x_waitingTime, y_waitingTime);

    XYChart.Series<String,Number> show = new XYChart.Series<String,Number>();


    private ArrayList<Integer> getFiveGreatestTimes () {

        ArrayList<Integer> temposHora = new ArrayList<>();

        for( ArrayList<String> tempo : cases.getAllCases() ) {
            temposHora.add( Integer.parseInt(tempo.get(0)) );
        }

        for( int i = 0; i < 5; i++ ) {
            for( int j = i+1; j < temposHora.size(); j++ ) {
                if( temposHora.get(i) < temposHora.get(j) ) {
                    int temp = temposHora.get(i);
                    temposHora.set( i, temposHora.get(j) );
                    temposHora.set( j, temp);
                }
            }
        }

        System.out.println( temposHora );

        return temposHora;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<Integer> cases = getFiveGreatestTimes();

        show.getData().add(new XYChart.Data( Integer.toString(cases.get(0)), cases.get(0)));
        show.getData().add(new XYChart.Data( Integer.toString(cases.get(1)), cases.get(1)));
        show.getData().add(new XYChart.Data( Integer.toString(cases.get(2)), cases.get(2)));
        show.getData().add(new XYChart.Data( Integer.toString(cases.get(3)), cases.get(3)));
        show.getData().add(new XYChart.Data( Integer.toString(cases.get(4)), cases.get(4)));

        chart_waitingTime.getData().add( show );
        chart_waitingTime.setLegendVisible(false);
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
