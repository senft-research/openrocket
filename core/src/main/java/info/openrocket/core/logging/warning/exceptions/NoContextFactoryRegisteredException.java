package info.openrocket.core.logging.warning.exceptions;

//TODO needs populating with actual Exception Logic

//TODO might be worth having a parent "FactoryRegistration Exception" of some sort to cut down on repeated logic with the
//     NoWarningFactoryRegisteredException and potentially others in the future?

import info.openrocket.core.logging.warning.factories.contexts.WarningContextFactory;

/**
 * Exception indicating when a {@linkplain info.openrocket.core.logging.warning.WarningType Warning Type} does not have
 * a corresponding {@linkplain WarningContextFactory Context Factory}
 * registered to handle it.
 */
public class NoContextFactoryRegisteredException extends RuntimeException {
    public NoContextFactoryRegisteredException(String message) {
        super(message);
    }
}
