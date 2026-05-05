package info.openrocket.core.logging.warning.factories.contexts;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.AbstractWarningContext;
import info.openrocket.core.logging.warning.context.WarningContext;

import java.util.Map;
import java.util.UUID;

public abstract class AbstractWarningContextFactory<T extends AbstractWarningContext.AbstractWarningContextBuilder<?>> implements WarningContextFactory {
    @Override
    public WarningContext create(MessagePriority priority, UUID warningId, WarningType warningType, Map<String, String> elements) {
        return initContext(elements)
                .withWarningId(warningId)
                .withWarningType(warningType)
                .withPriority(priority).build();
    }

    protected abstract T initContext(Map<String,String> elements);
}
