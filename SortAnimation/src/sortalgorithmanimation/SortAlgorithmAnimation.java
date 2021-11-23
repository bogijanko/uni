
package sortalgorithmanimation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sortalgorithmanimation.core.Elements;
import sortalgorithmanimation.ui.MainWindowController;


public class SortAlgorithmAnimation extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Učitavanje kontrolera i njegove konfiguracije */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/MainWindowController.fxml"));
        Parent root = loader.load();

        Elements elements = new Elements();
        MainWindowController controller = loader.getController();
        controller.setupLayout(elements);

        /* Kreiranje i postavljanje scene */
        Scene scene = new Scene(root);
        controller.setScene(scene);

        /* Konfiguracija i pokretanje stage-a */
        primaryStage.setTitle("Sort Algoritmi-Animacija");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
