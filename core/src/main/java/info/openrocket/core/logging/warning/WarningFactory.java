package info.openrocket.core.logging.warning;

/**
 * An Interface for factories responsible for the creation of {@linkplain WarningWrapper Warning Wrappers}.
 */
public interface WarningFactory {
    /**
     * Gets the unique type of the warnings created using this wrapper, used at runtime to specify what type of
     * warning to create (ensuring only the warning type needs to be recorded in the .ork).
     * @return The warning's unique id.
     */
    WarningType getWarningType();

    /**
     * Generates the final warning string (based on the preferences of the user) via a given set of params.
     * @throws IllegalArgumentException Thrown when the params do not contain the expected items in the expected order.
     * @return The generated warning.
     */
    WarningWrapper generateWarning(Object[] params) throws IllegalArgumentException;
}
