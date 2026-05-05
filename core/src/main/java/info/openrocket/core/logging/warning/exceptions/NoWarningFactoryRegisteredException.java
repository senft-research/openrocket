package info.openrocket.core.logging.warning.exceptions;

//TODO needs populating with actual Exception Logic

import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.factories.warnings.WarningFactory;

/**
 * Exception indicating when a {@linkplain info.openrocket.core.logging.warning.WarningType Warning Type} does not have
 * a corresponding {@linkplain WarningFactory Warning Factory}
 * registered to handle it.
 */
public class NoWarningFactoryRegisteredException extends InvalidWarningCreationException {
    public NoWarningFactoryRegisteredException(WarningContext warningContext) {
        super(warningContext);
    }

}
