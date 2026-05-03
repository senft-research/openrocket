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
    UUID getWarningId();
    WarningType getWarningType();
    MessagePriority  getPriority();

}
