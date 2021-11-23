
package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MessageAlerts {
    
    public static void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }
    
    public static void showInfoMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.show();
    }
}
