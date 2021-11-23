package scenes;
import crud.CrudFarmaceut;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pojo.Farmaceut;
import util.HibernateUtil;
import util.MessageAlerts;

public class Login extends Application {

    private TextField f1 = new TextField();
    private PasswordField pass = new PasswordField();
    private Label login = new Label("LOGIN");
    private Button btn = new Button("Login");
    private Button buttonKupac = new Button("~PRODAJA LEKOVA~");
    private Font f = new Font("Verdana", 14);
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("https://www.mindtecstore.com/media/image/product/1388/lg/bodycap-e-celsius-performance-pill~4.jpg"));
        f1.setPromptText("Username");
        pass.setPromptText("Password");

        Image image = new Image("https://s3.eu-central-1.amazonaws.com/apartmani-u-beogradu/uploads/firms/562/en/thumb/benu-apoteka-belgrade-pharmacies-centar.jpg");

        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(250);
        iv1.setFitHeight(200);
        iv1.setSmooth(true);

        HBox hbox1 = new HBox(buttonKupac);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(10));
        hbox1.setSpacing(10);
        
        login.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 18));
        login.setUnderline(true);
        buttonKupac.setFont(f);
        btn.setFont(f);
        f1.setFont(f);
        pass.setFont(f);
        buttonKupac.setStyle("-fx-background-color:lightgreen;");

        VBox vbox = new VBox(iv1, login, f1, pass, btn, hbox1);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(30));
        vbox.setId("pane");
        
        new Thread(() -> {
            HibernateUtil.getSessionFactory();
        }).start();
        
        btn.setOnAction((e)->{
            String username = f1.getText().trim();
            String password = pass.getText().trim();
            if (CrudFarmaceut.check(username)) {
                Farmaceut temp = CrudFarmaceut.get(username);
            if (!password.equals(temp.getPassword())) {
                    MessageAlerts.showErrorMessage("Pogrešan password");
                } else {
                    if (CrudFarmaceut.checkLogin(username, password)) {
                        Preferences userPref = Preferences.userRoot();
                        userPref.put("user", username);
                        primaryStage.setScene(FarmaceutPrikaz.getAdminView());
                        primaryStage.setTitle("Administracija");
                    }
                }
            } else {
                Alert greska = new Alert(Alert.AlertType.ERROR);
                greska.setHeaderText("Logovanje nije uspelo! ");
                greska.setContentText("Ne postoji farmaceut sa unetim username-om ");
                greska.showAndWait();
            }
        });
        buttonKupac.setOnAction((e) -> {
            primaryStage.setScene(KupacPrikaz.KupacView());
            primaryStage.setTitle("Prodaja Lekova");    
        });
        Scene scene = new Scene(vbox, 350, 400);

        primaryStage.setTitle("Apoteka - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        btn.requestFocus();
        primaryStage.setResizable(false);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
