package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.warning.WarningContext;
import info.openrocket.core.logging.warning.WarningFactory;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.WarningWrapper;
import info.openrocket.core.rocketcomponent.RocketComponent;

import java.util.Optional;

public class HighAoaWarningFactory implements WarningFactory<HighAoaWarningContext> {
    @Override
    public WarningType getWarningType() {
        return null;
    }

    @Override
    public WarningWrapper generateWarning(RocketComponent... components) throws IllegalArgumentException {
        return null;
    }

    @Override
    public WarningWrapper generateWarning(HighAoaWarningContext context) throws IllegalArgumentException {
        return new HighAoaWarningWrapper(context.getAngleOfAttack(),  context.getWarningId());
    }

    @Override
    public Optional<HighAoaWarningContext> returnCorrectContext(WarningContext context) {
        if(context instanceof HighAoaWarningContext) {
            return Optional.of((HighAoaWarningContext) context);
        }
        return Optional.empty();
    }
}
