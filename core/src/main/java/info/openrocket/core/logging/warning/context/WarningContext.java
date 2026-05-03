package info.openrocket.core.logging.warning.context;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;

import java.util.UUID;

/**
 * <p>Interface representing the Context of a {@linkplain info.openrocket.core.logging.Warning Rocket's Warning} in terms
 * of its Unique ID and Warning type.</p>
 *
 * This acts as a foundation on which more complex Context Instances can be implemented, allowing for multiple unique
 * params to be saved per warning, whilst still ensuring user preferences (such as units) are still respected.
 */
public interface WarningContext {
    /**
     * Gets the unique identifier of the Warning (used for easier identification throughout the primary Warning Logic)
     * @return The Warning's unique id.
     */
    UUID getWarningId();

    /**
     * Gets the warning type that this context represents.
     * @return The context's warning type.
     */
    WarningType getWarningType();

    /**
     * Gets the message priority of the warning message.
     * @return The warning message's priority.
     */
    MessagePriority  getPriority();

}
