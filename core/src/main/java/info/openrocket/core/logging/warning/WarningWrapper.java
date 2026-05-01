package info.openrocket.core.logging.warning;

import java.util.UUID;

/**
 * Interface for Warning Message Wrappers. Generates the warnings at runtime based on user preferences.
 */
public interface WarningWrapper {
    //TODO need to look into deserializing for the params of the warning
    String generateWarning();

    UUID getWarningId();

    WarningType getWarningType();

}
