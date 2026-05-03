package info.openrocket.core.logging.warning.exceptions;

/**
 * Exception indicating when the required parameters of a {@linkplain
 * info.openrocket.core.logging.warning.context.WarningContext} have not been initialized during construction.
 */
public class RequiredWarningContextParamsMissingException extends RuntimeException {
    public RequiredWarningContextParamsMissingException(String message) {
        super(message);
    }
}
