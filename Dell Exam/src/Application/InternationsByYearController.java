package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InternationsByYearController {

    int[] neededColumns = { 7, 15 };
    private CasesTable cases = new CasesTable( neededColumns );

    @FXML
    TextField intByYear_input_municipio;
    @FXML
    Text result_2018;
    @FXML
    Text result_2019;
    @FXML
    Text result_2020;
    @FXML
    Text result_2021;

    private ArrayList<Integer> getLinesThatHaveCity() {
        ArrayList<Integer> linesThatHaveCity = new ArrayList<>();
        for( int i = 0 ; i < cases.getAllCases().size(); i++ ) {

            if (cases.getAllCases().get(i).get(0).equals(intByYear_input_municipio.getText().toUpperCase())) {
                linesThatHaveCity.add(i);
            }
        }
        return linesThatHaveCity.isEmpty() ? null : linesThatHaveCity;
    }

    private ArrayList<Integer> getInternationsByYear( ArrayList<Integer> lines ) {

        ArrayList<Integer> internatonsByYear = new ArrayList<>(Arrays.asList( 0, 0, 0, 0));
        int year;

        for( int i = 0; i < lines.size(); i++ ) {
            year = Integer.parseInt(cases.getAllCases().get( lines.get(i) ).get( 1 ).substring( 0, 4 ));
            switch (year) {
                case 2018: internatonsByYear.set( 0, internatonsByYear.get(0)+1 ); break;
                case 2019: internatonsByYear.set( 1, internatonsByYear.get(1)+1 ); break;
                case 2020: internatonsByYear.set( 2, internatonsByYear.get(2)+1 ); break;
                case 2021: internatonsByYear.set( 3, internatonsByYear.get(3)+1 ); break;
            }
        }
        return internatonsByYear;
    }

    public void IntByYear_searchByCity() throws Exception {
        ArrayList<Integer> indexes = getLinesThatHaveCity();
       if( indexes != null ) {
            ArrayList<Integer> InternationByYears = getInternationsByYear( indexes );
            result_2018.setText( InternationByYears.get(0).toString() );
            result_2019.setText( InternationByYears.get(1).toString() );
            result_2020.setText( InternationByYears.get(2).toString() );
            result_2021.setText( InternationByYears.get(3).toString() );
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
