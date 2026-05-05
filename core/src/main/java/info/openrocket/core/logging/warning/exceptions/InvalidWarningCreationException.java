package info.openrocket.core.logging.warning.exceptions;

import info.openrocket.core.logging.warning.context.WarningContext;

public class InvalidWarningCreationException extends RuntimeException {
    private WarningContext context;

    public InvalidWarningCreationException(WarningContext context) {
        super("Creation of Warning via context of type: " + context.getWarningType() + " was not successful!");
    }

    public InvalidWarningCreationException(String message, WarningContext context) {
        super(message);
    }
}
