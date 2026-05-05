package info.openrocket.core.logging.warning.factories.contexts;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;

import java.util.Map;
import java.util.UUID;

/**
 * Interface representing a factory capable of constructing {@linkplain WarningContext Warning Context} Instances via
 * its warning type and corresponding warning context data (typically retried from the flight data stored in a
 * .ork file·
 */
public interface WarningContextFactory {
    //TODO this method needs to have the XML info sent to it so it can figure out what builder to send the elements to.
    /**
     * Creates the appropriate Warning Context instance based on the specified warning type and warning data.
     * @param warningType The warning type of the specified warning data.
     * @return The constructed Warning Context.
     */
    WarningContext create(MessagePriority priority, UUID warningId, WarningType warningType, Map<String, String> elements);
}
