package info.openrocket.core.logging.warning;

import info.openrocket.core.logging.Warning;

import java.util.UUID;

/**
 * Interface for Warning Message Wrappers. Generates the warnings at runtime based on user preferences.
 */
public interface WarningWrapper {
    //TODO need to look into deserializing for the params of the warning
    String generateWarningString();
    Warning generateWarning();

    UUID getWarningId();

    WarningType getWarningType();

}
