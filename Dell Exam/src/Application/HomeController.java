package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    // Abrir Consultar média de idade dos pacientes
    public void open_Average(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader();
        Parent root = fxmloader.load( getClass().getResource("average.fxml") );
        stage.setTitle("DELL - Internações : Consultar média de idade dos pacientes");
        Scene average = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( average );
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    // Abrir Consultar internações por ano
    public void open_InternationsByYear(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader();
        Parent root = fxmloader.load( getClass().getResource("internationsByYear.fxml") );
        stage.setTitle("DELL - Internações : Consultar internações por ano");
        Scene internationsByYear = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( internationsByYear );
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    // Abrir Consultar hospitais
    public void open_Hospital(ActionEvent e) throws Exception {
            Stage stage = new Stage();
            FXMLLoader fxmloader = new FXMLLoader();
            Parent root = fxmloader.load(getClass().getResource("hospital.fxml"));
            stage.setTitle("DELL - Internações : Consultar hospitais");
            Scene hospital = new Scene(root, 1200, 800);
        stage.setResizable(false);
            stage.setScene(hospital);
            stage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
    }

    //Abrir Calcular tempo de internação
    public void open_InternationTime(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader();
        Parent root = fxmloader.load( getClass().getResource("internationTime.fxml") );
        stage.setTitle("DELL - Internações : Calcular tempo de internação");
        Scene internationTime = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( internationTime );
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    // Abrir Determinar tempos de espera na fila
    public void open_WaitTime(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmloader = new FXMLLoader();
        Parent root = fxmloader.load( getClass().getResource("waitTime.fxml") );
        stage.setTitle("DELL - Internações : Determinar tempos de espera na fila");
        Scene waitTime = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( waitTime );
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    // Terminar o programa
    public void quitApp(ActionEvent e) throws Exception {
        System.exit(0);
    }


}
