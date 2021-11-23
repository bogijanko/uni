package sortalgorithmanimation.ui;

import javafx.beans.NamedArg;
import javafx.scene.control.Alert;

/**
 * Prikazivanje poruke u aplikaciji
 */
public class DisplayMessage extends Alert
{
    /**
     * Prikaz originalnog konstruktora
     * @param alertType
     */
    public DisplayMessage(@NamedArg("alertType") AlertType alertType){
        super(alertType);
    }

    /**
     * DisplayMessage konstruktor za pokretanje poruka
     * @param alertType
     * @param title
     * @param message
     */
    public DisplayMessage(@NamedArg("alertType") AlertType alertType, String title, String message){
        super(alertType);

        this.setHeaderText(""); // Unset-ovanje header texta
        this.setTitle(title);
        this.setContentText(message);

        this.showAndWait();
    }
}
