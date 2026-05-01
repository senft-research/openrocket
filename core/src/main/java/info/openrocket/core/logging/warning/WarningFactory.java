package info.openrocket.core.logging.warning;

import info.openrocket.core.rocketcomponent.RocketComponent;

import java.util.Optional;

/**
 * An Interface for factories responsible for the creation of {@linkplain WarningWrapper Warning Wrappers}.
 */
public interface WarningFactory<T extends WarningContext> {
    /**
     * Gets the unique type of the warnings created using this wrapper, used at runtime to specify what type of
     * warning to create (ensuring only the warning type needs to be recorded in the .ork).
     *
     * @return The warning's unique id.
     */
    WarningType getWarningType();

    /**
     * Generates the final warning string (based on the preferences of the user) via a given set of params.
     *
     * @param components The components related to the warning.
     * @return The generated warning.
     * @throws IllegalArgumentException Thrown when the params do not contain the expected items in the expected order.
     */
    WarningWrapper generateWarning(RocketComponent... components) throws IllegalArgumentException;

    WarningWrapper generateWarning(T context) throws IllegalArgumentException;

    Optional<T> returnCorrectContext(WarningContext context);
}
