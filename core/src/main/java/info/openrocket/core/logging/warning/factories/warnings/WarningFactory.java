package info.openrocket.core.logging.warning.factories.warnings;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.exceptions.InvalidWarningCreationException;
import info.openrocket.core.logging.warning.exceptions.NoWarningFactoryRegisteredException;

/**
 * <p>Interface representing factories that create {@linkplain Warning Warning Instances} based on a given Warning
 * Context.</p>
 *
 * The intention of this is to allow for factories to be broken down into the types of Warning (i.e. Warnings for
 * simulations, warnings for general rocket design etc.)
 */
public interface WarningFactory {
    //TODO might be worth having a general "InvalidWarningCreationException" of some sort?
    /**
     * Creates a warning from a given Warning Context.
     * @param context The context of the Warning to create.
     * @return The Created Warning
     */
    Warning createWarning(WarningContext context) throws InvalidWarningCreationException;
}
