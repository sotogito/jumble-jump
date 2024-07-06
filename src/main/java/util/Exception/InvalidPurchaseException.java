package util.Exception;

public class InvalidPurchaseException extends IllegalArgumentException {

    public InvalidPurchaseException(String message) {
        super(message);
    }

}
