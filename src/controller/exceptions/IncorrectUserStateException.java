package controller.exceptions;

/**
 *
 * @author Ingrid Castro 2020341
 */
public class IncorrectUserStateException extends Exception{
    public IncorrectUserStateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
