package info.openrocket.core.logging.warning.exceptions;

/**
 * Exception Indicating when a {@linkplain info.openrocket.core.logging.warning.WarningType Warning Type} does not have
 * a corresponding {@linkplain info.openrocket.core.logging.warning.factories.WarningFactory Warning Factory}
 * registered to handle it.
 */
public class NoWarningFactoryRegisteredException extends RuntimeException {
    public NoWarningFactoryRegisteredException(String message) {
        super(message);
    }

}
