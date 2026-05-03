package info.openrocket.core.logging.warning;

import info.openrocket.core.logging.warning.context.WarningContext;

/**
 * Enum that represents the type of {@linkplain info.openrocket.core.logging.Warning Warning} is being represented within
 * a {@linkplain WarningContext Warning Context}.
 */
public enum WarningType {
    /**
     * Warning representing when the rocket is at an abnormally large angle of attack.
     */
    LARGE_AOA
}
