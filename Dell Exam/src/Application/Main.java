package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public CasesTable allCases;
    private CSV_Admin file;

    @Override
    public void start(Stage stage) throws Exception{

        file = new CSV_Admin();

        Parent root = FXMLLoader.load( getClass().getResource("home.fxml") );
        Image icon = new Image("img/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("DELL - Internações");
        Scene home = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( home );
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
