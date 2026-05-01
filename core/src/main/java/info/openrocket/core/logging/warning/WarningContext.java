package info.openrocket.core.logging.warning;

import java.util.UUID;

public interface WarningContext {
    WarningType getWarningType();
    UUID getWarningId();

}
