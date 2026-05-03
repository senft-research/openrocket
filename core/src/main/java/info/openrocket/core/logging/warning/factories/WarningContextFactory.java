package info.openrocket.core.logging.warning.factories;

import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;

public interface WarningContextFactory {
    //TODO this method needs to have the XML info sent to it so it can figure out what builder to send the elements to.
    WarningContext create(WarningType warningType);
}
