package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class AverageController {

    int[] neededColumns = { 7, 5, 6 };
    private CasesTable cases = new CasesTable( neededColumns );

    @FXML
    TextField input_municipio;

    @FXML
    Text totalPacientes;

    @FXML
    CategoryAxis x_average = new CategoryAxis();
    @FXML
    NumberAxis y_average = new NumberAxis();
    @FXML
    BarChart<String, Number> chart_average = new BarChart<String, Number>(x_average, y_average);

    XYChart.Series<String,Number> show = new XYChart.Series<String,Number>();

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

    private ArrayList<Integer> getLinesThatHaveCity() {
        ArrayList<Integer> linesThatHaveCity = new ArrayList<>();
            for (int i = 0; i < cases.getAllCases().size(); i++) {
                if (cases.getAllCases().get(i).get(0).equals(input_municipio.getText().toUpperCase())) {
                    linesThatHaveCity.add(i);
                }
            }
        return linesThatHaveCity.isEmpty() ? null : linesThatHaveCity;
    }

    private double AverageFemale( ArrayList<Integer> lines ) {
        ArrayList<Integer> femaleAges = new ArrayList<>();

        for( int i = 0; i < lines.size(); i++ ) {
            if( cases.getAllCases().get(lines.get(i)).get(1).equals("FEMININO") ) {
                femaleAges.add( (int)Double.parseDouble( cases.getAllCases().get(lines.get(i)).get(2) ) );
            }
        }
        return femaleAges.stream().mapToInt( Integer::intValue ).average().getAsDouble();
    }

    private double AverageMale( ArrayList<Integer> lines ) {
        ArrayList<Integer> maleAges = new ArrayList<>();
        for( int i = 0; i < lines.size(); i++ ) {
            if( cases.getAllCases().get(lines.get(i)).get(1).equals("MASCULINO") ) {
                maleAges.add( (int)Double.parseDouble( cases.getAllCases().get(lines.get(i)).get(2) ) );
            }
        }
        return maleAges.stream().mapToInt( Integer::intValue ).average().getAsDouble();
    }

    private double AverageGlobal( ArrayList<Integer> lines ) {
        ArrayList<Integer> allAges = new ArrayList<>();
        for( int i = 0; i < lines.size(); i++ ) {
            if( cases.getAllCases().get(lines.get(i)).get(1).equals("MASCULINO")
            || cases.getAllCases().get(lines.get(i)).get(1).equals("FEMININO") ) {
                allAges.add( (int)Double.parseDouble( cases.getAllCases().get(lines.get(i)).get(2) ) );
            }
        }
        return allAges.stream().mapToInt( Integer::intValue ).average().getAsDouble();
    }

    public void searchByCity() throws Exception {
        totalPacientes.setText( "Não Definido" );
        show.getData().clear();
        chart_average.getData().clear();
        ArrayList<Integer> indexes = getLinesThatHaveCity();
        if( indexes != null ) {
            double female = AverageFemale( indexes );
            double male = AverageMale( indexes );
            double total = AverageGlobal( indexes );

            totalPacientes.setText( Integer.toString( indexes.size()) );
            show.getData().add(new XYChart.Data( "Média Masculino (" +  Integer.toString((int)male) + ")" , (int)male ));
            show.getData().add(new XYChart.Data( "Média Feminino (" +  Integer.toString((int)female) + ")" , (int)female));
            show.getData().add(new XYChart.Data( "Média Geral (" +  Integer.toString((int)total) + ")", (int)total ));

            chart_average.getData().add( show );
            chart_average.setLegendVisible(false);
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


}