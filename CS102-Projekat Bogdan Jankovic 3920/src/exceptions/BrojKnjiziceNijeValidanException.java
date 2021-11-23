
package exceptions;

 public class BrojKnjiziceNijeValidanException extends Exception {

    public BrojKnjiziceNijeValidanException() {
    }

    public BrojKnjiziceNijeValidanException(String message) {
        super(message);
    }

    public BrojKnjiziceNijeValidanException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrojKnjiziceNijeValidanException(Throwable cause) {
        super(cause);
    }
    
}
